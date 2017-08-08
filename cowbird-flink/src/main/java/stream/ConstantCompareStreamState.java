package stream;

import cowbird.flink.common.messages.control.ConstantCompareControlMessage;

import interdroid.swancore.swansong.Comparator;
import interdroid.swancore.swansong.TriState;

import static cowbird.flink.common.abs.Message.INVALID_TIMESTAMP;

public class ConstantCompareStreamState {

    private int state;

    private int comparator;

    private long firstTimestamp;

    private long lastTimestamp;

    private Object constantValue;

    private long historyLength;

    public boolean isExpressionLeft;

    public boolean isEvaluated;

    private int historyReductionMode;

    public ConstantCompareStreamState() {
        isExpressionLeft = false;

        toDefaultConfiguration();
    }

    public void toDefaultConfiguration() {

        state = TriState.UNDEFINED.toCode();


        firstTimestamp = INVALID_TIMESTAMP;
        lastTimestamp = INVALID_TIMESTAMP;

        isEvaluated = false;
    }

    public static ConstantCompareStreamState streamFromControlMessage(ConstantCompareControlMessage controlMessage) {
        ConstantCompareStreamState state = new ConstantCompareStreamState();

        state.historyLength = controlMessage.getHistoryLength();
        state.constantValue = controlMessage.getConstantValue();
        state.isExpressionLeft = controlMessage.isExpressionLeft;
        state.comparator = controlMessage.getComparator();
        state.historyReductionMode = controlMessage.getHistoryReductionMode();

        return state;
    }


    public void setHistoryReductionMode(int historyReductionMode) {
        this.historyReductionMode = historyReductionMode;
    }

    public int getHistoryReductionMode() {
        return historyReductionMode;
    }

    public void setState(TriState state) {
        this.state = state.toCode();
    }


    public TriState getState() {
        return TriState.fromCode(state);
    }


    public void setFirstTimestamp(long firstTimestamp) {
        this.firstTimestamp = firstTimestamp;
    }


    public long getFirstTimestamp() {
        return firstTimestamp;
    }


    public void setLastTimestamp(long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }


    public long getLastTimestamp() {
        return lastTimestamp;
    }

    public void setComparator(int comparator) {
        this.comparator = comparator;
    }

    public Comparator getComparator() {
        return Comparator.convert(comparator);
    }


    public void setConstantValue(Object constantValue) {
        this.constantValue = constantValue;
    }


    public Object getConstantValue() {
        return constantValue;
    }


    public void setHistoryLength(long historyLength) {
        this.historyLength = historyLength;
    }


    public long getHistoryLength() {
        return historyLength;
    }

    public boolean hasElements() {
        return firstTimestamp != INVALID_TIMESTAMP;
    }
}
