package stream;

import cowbird.flink.common.messages.sensor.SensorMessage;

import interdroid.swancore.swansong.HistoryReductionMode;

public class StreamState {

    private long firtElementTimeStamp;

    private long lastElementTimeStamp;

    private int size;

    private Object min;

    private Object max;

    private double sum ;

    private String expressionId;


    public StreamState(String expressionId) {
        this.expressionId = expressionId;

        toDefaultState();
    }

    public void add(SensorMessage message) {
        if(size == 0) {
            firtElementTimeStamp = message.getEventTime();
        }

        Object messageValue = message.getValue();

        if(((Comparable) min).compareTo((Comparable) messageValue) < 0) {
            min = message.getValue();
        }
        if(((Comparable) messageValue).compareTo((Comparable) min) < 0) {
            min = messageValue;
        }


        if(((Comparable) messageValue).compareTo((Comparable) max) > 0) {
            max = messageValue;
        }

        sum += Double.valueOf(messageValue.toString());
        lastElementTimeStamp = message.getEventTime();

        size++;
    }


    public double getSum() {
        return sum;
    }


    public Object getMax() {
        return max;
    }


    public Object getMin() {
        return min;
    }


    public int getSize() {
        return size;
    }


    public String getExpressionId() {
        return expressionId;
    }


    public long getFirtElementTimeStamp() {
        return firtElementTimeStamp;
    }


    public long getLastElementTimeStamp() {
        return lastElementTimeStamp;
    }


    public void toDefaultState() {
        min = Double.MAX_VALUE;
        max = Double.MIN_VALUE;

        sum = 0;
        size = 0;
    }

    public Object applyReduction(HistoryReductionMode mode) {
        switch (mode) {
            case MAX:
                return max;
            case MIN:
                return min;
            case MEAN:
                return sum/size;
            case MEDIAN:
            case ALL:
            case ANY:
            default:
                return null;
        }
    }



}
