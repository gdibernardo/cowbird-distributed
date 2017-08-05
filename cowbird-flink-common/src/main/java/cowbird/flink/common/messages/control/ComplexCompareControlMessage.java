package cowbird.flink.common.messages.control;

import cowbird.flink.common.abs.Message;


public class ComplexCompareControlMessage extends Message {

    protected int comparator;

    protected String leftExpressionId;
    protected String rightExpressionId;

    protected int leftHistoryReductionMode;
    protected int rightHistoryReductionMode;

    protected long leftHistoryLength;
    protected long rightHistoryLength;

    public ComplexCompareControlMessage(){
        super();
    }

    public ComplexCompareControlMessage(String expressionId) {
        super(expressionId);
    }


    public ComplexCompareControlMessage(String expressionId,
                                        String leftExpressionId,
                                        String rightExpressionId,
                                        int leftHistoryReductionMode,
                                        long leftHistoryLength,
                                        int rightHistoryReductionMode,
                                        long rightHistoryLength,
                                        int comparator) {
        super(expressionId);

        this.leftExpressionId = leftExpressionId;
        this.rightExpressionId = rightExpressionId;

        this.leftHistoryReductionMode = leftHistoryReductionMode;
        this.leftHistoryLength = leftHistoryLength;

        this.rightHistoryReductionMode = rightHistoryReductionMode;
        this.leftHistoryLength = rightHistoryLength;

        this.comparator = comparator;
    }


    public void setComparator(int comparator) {
        this.comparator = comparator;
    }

    public int getComparator() {
        return comparator;
    }

    public void setLeftExpressionId(String leftExpressionId) {
        this.leftExpressionId = leftExpressionId;
    }

    public String getLeftExpressionId() {
        return leftExpressionId;
    }

    public void setRightExpressionId(String rightExpressionId) {
        this.rightExpressionId = rightExpressionId;
    }

    public String getRightExpressionId() {
        return rightExpressionId;
    }

    public void setLeftHistoryLength(long leftHistoryLength) {
        this.leftHistoryLength = leftHistoryLength;
    }

    public long getLeftHistoryLength() {
        return leftHistoryLength;
    }

    public void setLeftHistoryReductionMode(int leftHistoryReductionMode) {
        this.leftHistoryReductionMode = leftHistoryReductionMode;
    }

    public int getLeftHistoryReductionMode() {
        return leftHistoryReductionMode;
    }

    public void setRightHistoryReductionMode(int rightHistoryReductionMode) {
        this.rightHistoryReductionMode = rightHistoryReductionMode;
    }

    public int getRightHistoryReductionMode() {
        return rightHistoryReductionMode;
    }

    public void setRightHistoryLength(long rightHistoryLength) {
        this.rightHistoryLength = rightHistoryLength;
    }

    public long getRightHistoryLength() {
        return rightHistoryLength;
    }


    @Override
    public String toJSON() {
        return null;
    }

    @Override
    public void initFromJSON(String json) {
    }
}
