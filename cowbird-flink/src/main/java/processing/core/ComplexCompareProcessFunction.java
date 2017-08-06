package processing.core;


import cowbird.flink.common.messages.control.ComplexCompareControlMessage;
import cowbird.flink.common.messages.result.ResultMessage;
import cowbird.flink.common.messages.sensor.SensorMessage;

import interdroid.swancore.swansong.TimestampedValue;

import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;

import org.apache.flink.configuration.Configuration;

import org.apache.flink.streaming.api.functions.co.CoProcessFunction;

import org.apache.flink.util.Collector;


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

    }

    @Override
    public void processElement2(Tuple2<String, SensorMessage> value, Context ctx, Collector<ResultMessage> out) throws Exception {

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

    }
}
