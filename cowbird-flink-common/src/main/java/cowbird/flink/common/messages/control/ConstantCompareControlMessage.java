package cowbird.flink.common.messages.control;

import cowbird.flink.common.config.MessageConfig;
import org.json.JSONException;
import org.json.JSONObject;

public class ConstantCompareControlMessage extends ControlMessage {

    public boolean isExpressionLeft;

    private Object constantValue;

    private int comparator;


    public ConstantCompareControlMessage() {super();}


    public ConstantCompareControlMessage(String expressionId) {
        super(expressionId);
    }


    public ConstantCompareControlMessage(String expressionId,
                                         int historyReductionMode,
                                         long historyLength,
                                         long timestamp,
                                         Object constantValue,
                                         int comparator,
                                         boolean isExpressionLeft) {
        super(expressionId, historyReductionMode, historyLength, timestamp);

        this.constantValue = constantValue;
        this.comparator = comparator;

        this.isExpressionLeft = isExpressionLeft;
    }


    public void setComparator(int comparator) {
        this.comparator = comparator;
    }

    public void setConstantValue(Object constantValue) {
        this.constantValue = constantValue;
    }


    public Object getConstantValue() {
        return constantValue;
    }


    public int getComparator() {
        return comparator;
    }

    @Override
    public void initFromJSON(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            expressionId = jsonObject.getString(MessageConfig.ID_CONFIG_KEY);
            historyReductionMode = jsonObject.getInt(MessageConfig.HISTORY_REDUCTION_MODE_CONFIG_KEY);
            historyLength = jsonObject.getLong(MessageConfig.HISTORY_LENGTH_CONFIG_KEY);
            timestamp = jsonObject.getLong(MessageConfig.EVENT_TIME_CONFIG_KEY);

            comparator = jsonObject.getInt(MessageConfig.COMPARATOR_CONFIG_KEY);
            constantValue = jsonObject.getDouble(MessageConfig.VALUE_CONFIG_KEY);
            isExpressionLeft = jsonObject.getBoolean(MessageConfig.IS_EXPRESSION_LEFT_CONFIG_KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public String toJSON() {
        String jsonString = null;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(MessageConfig.ID_CONFIG_KEY, expressionId);
            jsonObject.put(MessageConfig.HISTORY_REDUCTION_MODE_CONFIG_KEY, historyReductionMode);
            jsonObject.put(MessageConfig.HISTORY_LENGTH_CONFIG_KEY, historyLength);
            jsonObject.put(MessageConfig.EVENT_TIME_CONFIG_KEY, timestamp);

            jsonObject.put(MessageConfig.COMPARATOR_CONFIG_KEY, comparator);
            jsonObject.put(MessageConfig.VALUE_CONFIG_KEY, constantValue);
            jsonObject.put(MessageConfig.IS_EXPRESSION_LEFT_CONFIG_KEY, isExpressionLeft);

            jsonString = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonString;
    }
}
