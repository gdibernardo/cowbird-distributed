package processing.core;


import com.jcraft.jsch.MAC;
import cowbird.flink.common.messages.control.ComplexCompareControlMessage;
import cowbird.flink.common.messages.result.ResultMessage;
import cowbird.flink.common.messages.sensor.SensorMessage;

import interdroid.swancore.swansong.Comparator;
import interdroid.swancore.swansong.ComparatorResult;
import interdroid.swancore.swansong.HistoryReductionMode;
import interdroid.swancore.swansong.TimestampedValue;

import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;

import org.apache.flink.configuration.Configuration;

import org.apache.flink.streaming.api.functions.co.CoProcessFunction;

import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.Iterator;


public class ComplexCompareProcessFunction extends CoProcessFunction <Tuple2<String, ComplexCompareControlMessage>, Tuple2<String, SensorMessage>, ResultMessage>{

    /*  TODO:  These fields could probably be inherited from the superclass.   */
    static final String CONTROL_MESSAGE_VALUE_STATE_DESCRIPTOR_NAME = "CONTROL_MESSAGE_VALUE_STATE_DESCRIPTOR_NAME";
    private transient ValueState<ComplexCompareControlMessage> controlMessageValueState;

    static final String LEFT_SENSORS_VALUES_LIST_STATE_DESCRIPTOR = "LEFT_SENSORS_VALUES_LIST_STATE_DESCRIPTOR";
    private transient ListState<TimestampedValue> leftValuesListState;

    static final String RIGHT_SENSORS_VALUES_LIST_STATE_DESCRIPTOR = "RIGHT_SENSORS_VALUES_LIST_STATE_DESCRIPTOR";
    private transient ListState<TimestampedValue> rightValuesListState;


    @Override
    public void processElement1(Tuple2<String, ComplexCompareControlMessage> value, Context ctx, Collector<ResultMessage> out) throws Exception {
        ComplexCompareControlMessage controlMessageState = controlMessageValueState.value();
        if (controlMessageState == null) {
            /*  Registering new current SWAN complex compare expression.    */
            controlMessageValueState.update(value.f1);
        } else {
            /*  Clearing the current state. */
            controlMessageValueState.clear();
            leftValuesListState.clear();
            rightValuesListState.clear();
        }
    }


    @Override
    public void processElement2(Tuple2<String, SensorMessage> value, Context ctx, Collector<ResultMessage> out) throws Exception {
        ComplexCompareControlMessage controlMessage = controlMessageValueState.value();

        if (controlMessage == null)
            return;

        SensorMessage sensorMessage = value.f1;

        TimestampedValue timestampedValue = new TimestampedValue(sensorMessage.getValue(), sensorMessage.getEventTime());

        if (sensorMessage.getExpressionId().equals(controlMessage.getLeftExpressionId())) {
            /*  Add to the left list state. */
            leftValuesListState.add(timestampedValue);
        } else {
            /*  Add to the the right list state.    */
            rightValuesListState.add(timestampedValue);
        }


        // ctx.timerService().registerProcessingTimeTimer(System.currentTimeMillis() + Math.max(controlMessage.getLeftHistoryLength(), controlMessage.getRightHistoryLength()));
        ctx.timerService().registerProcessingTimeTimer(sensorMessage.getEventTime() + Math.max(controlMessage.getLeftHistoryLength(), controlMessage.getRightHistoryLength()));

    }


    @Override
    public void open(Configuration parameters) throws Exception {
        // super.open(parameters);
        /*  Open control message value state.   */
        ValueStateDescriptor valueStateDescriptor = new ValueStateDescriptor(CONTROL_MESSAGE_VALUE_STATE_DESCRIPTOR_NAME, ComplexCompareControlMessage.class);
        controlMessageValueState = getRuntimeContext().getState(valueStateDescriptor);

        /*  Open left values list state.    */
        ListStateDescriptor leftListStateDescriptor = new ListStateDescriptor(LEFT_SENSORS_VALUES_LIST_STATE_DESCRIPTOR, TimestampedValue.class);
        leftValuesListState = getRuntimeContext().getListState(leftListStateDescriptor);

        /*  Open right values list state.   */
        ListStateDescriptor rightListStateDescriptor = new ListStateDescriptor(RIGHT_SENSORS_VALUES_LIST_STATE_DESCRIPTOR, TimestampedValue.class);
        rightValuesListState = getRuntimeContext().getListState(rightListStateDescriptor);
    }


    @Override
    public void onTimer(long timestamp, OnTimerContext ctx, Collector<ResultMessage> out) throws Exception {

        ComplexCompareControlMessage controlMessage = controlMessageValueState.value();
        if(controlMessage == null) {
            return;
        }

        ArrayList<TimestampedValue> leftValues = new ArrayList<>();
        ArrayList<TimestampedValue> rightValues = new ArrayList<>();

        Iterator<TimestampedValue> leftIterator = leftValuesListState.get().iterator();
        Iterator<TimestampedValue> rightIterator = rightValuesListState.get().iterator();

        if(!leftIterator.hasNext() && !rightIterator.hasNext()) {
            return;
        }

        leftValuesListState.clear();
        rightValuesListState.clear();

        long leftOldestTimestamp = Long.MAX_VALUE;
        long leftLatestTimestamp = Long.MIN_VALUE;

        while (leftIterator.hasNext()) {
            TimestampedValue timestampedValue = leftIterator.next();
            /*  Same SWAN-framework assertion.    */
            /*  TODO Create a shared function for doing this!.
            /*  TODO Maybe in the superclass?!  */
            if (timestampedValue.getTimestamp() >= (timestamp - controlMessage.getLeftHistoryLength()))  {
                long currentTimestamp = timestampedValue.getTimestamp();
                if(timestamp >= currentTimestamp) {
                    if(currentTimestamp < leftOldestTimestamp)
                        leftOldestTimestamp = currentTimestamp;
                    if(currentTimestamp > leftLatestTimestamp)
                        leftLatestTimestamp = currentTimestamp;

                    leftValues.add(timestampedValue);
                    leftValuesListState.add(timestampedValue);
                }
            }
        }

        long rightOldestTimestamp = Long.MAX_VALUE;
        long rightLatestTimestamp = Long.MIN_VALUE;

        while (rightIterator.hasNext()) {
            TimestampedValue timestampedValue = rightIterator.next();
            /*  Same SWAN-framework assertion.    */
            if (timestampedValue.getTimestamp() >= (timestamp - controlMessage.getRightHistoryLength())) {
                long currentTimestamp = timestampedValue.getTimestamp();
                if (timestamp >= currentTimestamp) {
                    if (currentTimestamp < rightOldestTimestamp)
                        rightOldestTimestamp = currentTimestamp;
                    if (currentTimestamp > rightLatestTimestamp)
                        rightLatestTimestamp = currentTimestamp;

                    rightValues.add(timestampedValue);
                    rightValuesListState.add(timestampedValue);
                }
            }
        }

        if(leftValues.size() > 0 && rightValues.size() > 0) {
             /*  Copied from SWAN framework. */
            /*  TODO evaluate if this could be furthermore optimized.   */
            ComparatorResult comparatorResult = new ComparatorResult(timestamp,
                    HistoryReductionMode.convert(controlMessage.getLeftHistoryReductionMode()),
                    HistoryReductionMode.convert(controlMessage.getRightHistoryReductionMode()));

            // combination ANY, ANY has a tradeoff. We can terminate evaluation as
            // soon as we find a combination that results in true, BUT if we
            // continue we might find a longer deferUntil
            comparatorResult.startOuterLoop();
            int l, r;
            for (l = 0; l < leftValues.size(); l++) {
                comparatorResult.startInnerLoop();
                for (r = 0; r < rightValues.size(); r++) {
                    if (comparatorResult.innerResult(Comparator.comparePair(Comparator.convert(controlMessage.getComparator()),
                            leftValues.get(l).getValue(),
                            rightValues.get(r).getValue()))) {
                        break;
                    }
                }
                if (comparatorResult.outerResult()) {
                    break;
                }
            }

            ResultMessage resultMessage = new ResultMessage(controlMessage.getExpressionId());
            resultMessage.setValue(comparatorResult.getTriState().toCode());

            resultMessage.setLeftLatestTimestamp(leftLatestTimestamp);
            resultMessage.setLeftOldestTimestamp(leftOldestTimestamp);

            resultMessage.setRightLatestTimestamp(rightLatestTimestamp);
            resultMessage.setRightOldestTimestamp(rightOldestTimestamp);

            out.collect(resultMessage);
        }
    }
}
