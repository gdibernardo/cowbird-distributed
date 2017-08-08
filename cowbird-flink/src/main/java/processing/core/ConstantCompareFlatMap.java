package processing.core;

import cowbird.flink.common.messages.control.ConstantCompareControlMessage;
import cowbird.flink.common.messages.result.ResultMessage;
import cowbird.flink.common.messages.sensor.SensorMessage;

import interdroid.swancore.swansong.Comparator;
import interdroid.swancore.swansong.HistoryReductionMode;
import interdroid.swancore.swansong.TriState;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;

import org.apache.flink.api.java.tuple.Tuple2;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.co.RichCoFlatMapFunction;
import org.apache.flink.util.Collector;

import stream.ConstantCompareStreamState;

public class ConstantCompareFlatMap extends RichCoFlatMapFunction<Tuple2<String, ConstantCompareControlMessage>, Tuple2<String, SensorMessage>, ResultMessage> {

    static final String TRISTATE_STREAM_STATE_DESCRIPTOR = "TRISTATE_STREAM_STATE_DESCRIPTOR";

    transient ValueState<ConstantCompareStreamState> streamValueState;


    @Override
    public void flatMap1(Tuple2<String, ConstantCompareControlMessage> value, Collector<ResultMessage> out) throws Exception {
        ConstantCompareStreamState currentState = streamValueState.value();
        if(currentState == null) {
            /*  Initalizing state.  */
            streamValueState.update(ConstantCompareStreamState.streamFromControlMessage(value.f1));
        } else {
            /*  Clearing state. */
            streamValueState.clear();
        }
    }



    /*  This method can be furthermore optimized. */
    @Override
    public void flatMap2(Tuple2<String, SensorMessage> value, Collector<ResultMessage> out) throws Exception {

        ConstantCompareStreamState currentState = streamValueState.value();

        if (currentState == null) {
            return;
        }

        SensorMessage sensorMessage = value.f1;

        if (currentState.isEvaluated) {
            currentState.setLastTimestamp(sensorMessage.getEventTime());

            if (currentState.getLastTimestamp() - currentState.getFirstTimestamp() > currentState.getHistoryLength()) {
                currentState.toDefaultConfiguration();
            } else
                return;
        }

        Object left = currentState.isExpressionLeft ? sensorMessage.getValue() : currentState.getConstantValue();
        Object right = currentState.isExpressionLeft ? currentState.getConstantValue() : sensorMessage.getValue();

        TriState triState = Comparator.comparePair(currentState.getComparator(), left, right);
        currentState.setState(triState);

        boolean isReadyToEmit = false;

        switch (HistoryReductionMode.convert(currentState.getHistoryReductionMode())) {
            case ALL:
                if (triState == TriState.FALSE) {
                    currentState.isEvaluated = true;
                    /*  We are ready to emit result.    */
                    isReadyToEmit = true;
                }
                break;
            case ANY:
                if (triState == TriState.TRUE) {
                    currentState.isEvaluated = true;
                    /*  We are ready to emit result.    */
                    isReadyToEmit = true;
                }
                break;
            default:break;
        }

        if (!currentState.hasElements())
            currentState.setFirstTimestamp(sensorMessage.getEventTime());

        currentState.setLastTimestamp(sensorMessage.getEventTime());

        if (((currentState.getLastTimestamp() - currentState.getFirstTimestamp()) >= currentState.getHistoryLength()) && !currentState.isEvaluated) {
            /*  Ready to emit result.   */
            isReadyToEmit = true;
        }

        if (isReadyToEmit) {

            ResultMessage resultMessage = new ResultMessage(value.f0);

            resultMessage.setValue(currentState.getState().toCode());

            resultMessage.setLeftOldestTimestamp(currentState.getFirstTimestamp());
            resultMessage.setRightOldestTimestamp(currentState.getFirstTimestamp());


            if (currentState.isExpressionLeft) {
                resultMessage.setLeftLatestTimestamp(currentState.getLastTimestamp());
                resultMessage.setRightLatestTimestamp(currentState.getFirstTimestamp());
            } else {
                resultMessage.setLeftLatestTimestamp(currentState.getFirstTimestamp());
                resultMessage.setRightLatestTimestamp(currentState.getLastTimestamp());
            }

            out.collect(resultMessage);

            if(!currentState.isEvaluated)
                currentState.toDefaultConfiguration();
        }

        streamValueState.update(currentState);
    }


    @Override
    public void open(Configuration parameters) throws Exception {
        // super.open(parameters);
        ValueStateDescriptor valueStateDescriptor = new ValueStateDescriptor(TRISTATE_STREAM_STATE_DESCRIPTOR, ConstantCompareStreamState.class);
        streamValueState = getRuntimeContext().getState(valueStateDescriptor);
    }
}
