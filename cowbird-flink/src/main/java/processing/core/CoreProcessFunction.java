package processing.core;

import cowbird.flink.common.messages.control.ControlMessage;
import cowbird.flink.common.messages.sensor.SensorMessage;
import cowbird.flink.common.messages.result.ResultMessage;

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


public class CoreProcessFunction extends CoProcessFunction <Tuple2<String, ControlMessage>, Tuple2<String, SensorMessage>, ResultMessage> {

    static final String SENSORS_VALUES_LIST_STATE_DESCRIPTOR = "SENSORS_VALUES_LIST_STATE_DESCRIPTOR";

    static final String CONTROL_MESSAGE_VALUE_STATE_DESCRIPTOR_NAME = "CONTROL_MESSAGE_VALUE_STATE_DESCRIPTOR_NAME";

    private transient ListState<TimestampedValue> sensorValuesListState;

    private transient ValueState<ControlMessage> controlMessageValueState;


    @Override
    public void open(Configuration parameters) throws Exception {
        // super.open(parameters);
        ListStateDescriptor<TimestampedValue> sensorsValuesListStateDescriptor = new ListStateDescriptor<TimestampedValue>(SENSORS_VALUES_LIST_STATE_DESCRIPTOR, TimestampedValue.class);
        sensorValuesListState = getRuntimeContext().getListState(sensorsValuesListStateDescriptor);

        ValueStateDescriptor<ControlMessage> controlMessageValueStateDescriptor = new ValueStateDescriptor<ControlMessage>(CONTROL_MESSAGE_VALUE_STATE_DESCRIPTOR_NAME, ControlMessage.class);
        controlMessageValueState = getRuntimeContext().getState(controlMessageValueStateDescriptor);
    }


    @Override
    public void processElement1(Tuple2<String, ControlMessage> value, Context ctx, Collector<ResultMessage> out) throws Exception {

        ControlMessage controlMessage = controlMessageValueState.value();
        if(controlMessage == null) {
            /*  Initalizing SWAN Sensor Value on Flink. */
            controlMessageValueState.update(value.f1);
        } else {
            /*  Unregister SWAN sensor value expression on Flink.   */
            controlMessageValueState.clear();
            sensorValuesListState.clear();
        }
    }


    @Override
    public void processElement2(Tuple2<String, SensorMessage> value, Context ctx, Collector<ResultMessage> out) throws Exception {

        ControlMessage controlMessage = controlMessageValueState.value();

        if(controlMessage == null) {
            return;
        }

        TimestampedValue timestampedValue = new TimestampedValue(value.f1.getValue(), value.f1.getEventTime());
        sensorValuesListState.add(timestampedValue);

        // ctx.timerService().registerEventTimeTimer(timestampedValue.getTimestamp() + controlMessage.getHistoryLength());
        ctx.timerService().registerProcessingTimeTimer(System.currentTimeMillis() + controlMessage.getHistoryLength());
    }


    @Override
    public void onTimer(long timestamp, OnTimerContext ctx, Collector<ResultMessage> out) throws Exception {
        super.onTimer(timestamp, ctx, out);

        ControlMessage controlMessage = controlMessageValueState.value();
        if(controlMessage == null) {
            return;
        }

        ArrayList<TimestampedValue> values = new ArrayList<>();

        Iterator<TimestampedValue> iterator = sensorValuesListState.get().iterator();

        sensorValuesListState.clear();

        long oldestTimestamp = Long.MAX_VALUE;
        long latestTimestamp = Long.MIN_VALUE;

        while(iterator.hasNext()) {
            TimestampedValue timestampedValue = iterator.next();
             /*  Same SWAN assertion.    */
            if(timestampedValue.getTimestamp() >= (timestamp - controlMessage.getHistoryLength()))  {
                long currentTimestamp = timestampedValue.getTimestamp();
                if(timestamp >= currentTimestamp) {

                    if(currentTimestamp < oldestTimestamp)
                        oldestTimestamp = currentTimestamp;
                    if(currentTimestamp > latestTimestamp)
                        latestTimestamp = currentTimestamp;

                    values.add(timestampedValue);

                    sensorValuesListState.add(timestampedValue);
                }
            }
        }

        if(values.size() == 0) {
            return;
        }

        Object resultValue = TimestampedValue.applyModeForRemoteEvaluation(values, HistoryReductionMode.convert(controlMessage.getHistoryReductionMode()));

        if(resultValue != null) {
            ResultMessage resultMessage = new ResultMessage(controlMessage.getExpressionId());

            resultMessage.setValue(resultValue);

            resultMessage.setLeftLatestTimestamp(latestTimestamp);
            resultMessage.setRightLatestTimestamp(latestTimestamp);

            resultMessage.setLeftOldestTimestamp(oldestTimestamp);
            resultMessage.setRightOldestTimestamp(oldestTimestamp);

            out.collect(resultMessage);
        }
    }
}
