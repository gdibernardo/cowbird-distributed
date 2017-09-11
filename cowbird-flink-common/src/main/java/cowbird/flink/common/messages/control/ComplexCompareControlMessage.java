package cowbird.flink.common.messages.control;

import cowbird.flink.common.abs.Message;
import cowbird.flink.common.config.MessageConfig;
import org.json.JSONException;
import org.json.JSONObject;


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
        this.rightHistoryLength = rightHistoryLength;

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
        String jsonString = null;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(MessageConfig.ID_CONFIG_KEY, expressionId);
            jsonObject.put(MessageConfig.LEFT_ID_CONFIG_KEY, leftExpressionId);
            jsonObject.put(MessageConfig.RIGHT_ID_CONFIG_KEY, rightExpressionId);

            jsonObject.put(MessageConfig.LEFT_HISTORY_REDUCTION_MODE_CONFIG_KEY, leftHistoryReductionMode);
            jsonObject.put(MessageConfig.RIGHT_HISTORY_REDUCTION_MODE_CONFIG_KEY, rightHistoryReductionMode);

            jsonObject.put(MessageConfig.LEFT_HISTORY_LENGTH_CONFIG_KEY, leftHistoryLength);
            jsonObject.put(MessageConfig.RIGHT_HISTORY_LENGTH_CONFIG_KEY, rightHistoryLength);

            jsonObject.put(MessageConfig.EVENT_TIME_CONFIG_KEY, timestamp);

            jsonObject.put(MessageConfig.COMPARATOR_CONFIG_KEY, comparator);

            jsonString = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonString;
    }


    @Override
    public void initFromJSON(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            expressionId = jsonObject.getString(MessageConfig.ID_CONFIG_KEY);
            leftExpressionId = jsonObject.getString(MessageConfig.LEFT_ID_CONFIG_KEY);
            rightExpressionId = jsonObject.getString(MessageConfig.RIGHT_ID_CONFIG_KEY);

            leftHistoryReductionMode = jsonObject.getInt(MessageConfig.LEFT_HISTORY_REDUCTION_MODE_CONFIG_KEY);
            rightHistoryReductionMode = jsonObject.getInt(MessageConfig.RIGHT_HISTORY_REDUCTION_MODE_CONFIG_KEY);

            leftHistoryLength = jsonObject.getLong(MessageConfig.LEFT_HISTORY_LENGTH_CONFIG_KEY);
            rightHistoryLength = jsonObject.getLong(MessageConfig.RIGHT_HISTORY_LENGTH_CONFIG_KEY);

            timestamp = jsonObject.getLong(MessageConfig.EVENT_TIME_CONFIG_KEY);

            comparator = jsonObject.getInt(MessageConfig.COMPARATOR_CONFIG_KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
