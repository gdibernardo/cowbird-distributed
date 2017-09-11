package stream;

import cowbird.flink.common.messages.control.ControlMessage;
import cowbird.flink.common.messages.sensor.SensorMessage;

import interdroid.swancore.swansong.HistoryReductionMode;

public class LightStreamState {

    private long firtElementTimeStamp;
    private long lastElementTimeStamp;

    private int size;

    private Object min;
    private Object max;

    private double sum;

    private String expressionId;

    private RemedianMatrix remedianMatrix;

    private int reductionMode;

    public LightStreamState(ControlMessage controlMessage) {
        this.expressionId = controlMessage.getExpressionId();

        reductionMode = controlMessage.getHistoryReductionMode();

        remedianMatrix = null;

        if (HistoryReductionMode.convert(reductionMode) == HistoryReductionMode.MEDIAN) {
            /*  Init the remedian matrix.   */
            remedianMatrix = RemedianMatrix.defaultRemedianMatrix();
        }

        toDefaultState();
    }


    public void add(SensorMessage message) {
        if (size == 0) {
            firtElementTimeStamp = message.getEventTime();
        }

        Object messageValue = message.getValue();

        if(remedianMatrix != null) {
            remedianMatrix.addValue(messageValue);
        }

        if (((Comparable) messageValue).compareTo((Comparable) min) < 0) {
            min = messageValue;
        }

        if (((Comparable) messageValue).compareTo((Comparable) max) > 0) {
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


    public double getMedian() {
        return (double) remedianMatrix.getMedian();
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

        remedianMatrix.initMatrix();
    }

    public Object applyReduction() {
        HistoryReductionMode mode = HistoryReductionMode.convert(reductionMode);

        switch (mode) {
            case MAX:
                return max;
            case MIN:
                return min;
            case MEAN:
                return sum / size;
            case MEDIAN:
                return remedianMatrix.getMedian();
            case ALL:
            case ANY:
            default:
                return null;
        }
    }
}