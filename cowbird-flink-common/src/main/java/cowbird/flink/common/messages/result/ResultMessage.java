package cowbird.flink.common.messages.result;

import cowbird.flink.common.abs.Message;
import cowbird.flink.common.abs.MessageInterface;
import cowbird.flink.common.config.MessageConfig;
import org.json.JSONException;
import org.json.JSONObject;


public class ResultMessage extends Message implements MessageInterface {

    private long leftOldestTimestamp;
    private long leftLatestTimestamp;

    private long rightOldestTimestamp;
    private long rightLatestTimestamp;


    private Object value;

    public ResultMessage() {
        super();
    }


    public ResultMessage(String expressionId) {
        super(expressionId);
    }


    public ResultMessage(String expressionId, Object value, long timestamp) {
        this(expressionId);

        this.value = value;

        this.timestamp = timestamp;
    }


    public Object getValue() {
        return value;
    }


    public void setValue(Object value) {
        this.value = value;
    }


    public long getRightOldestTimestamp() {
        return rightOldestTimestamp;
    }

    public void setRightOldestTimestamp(long rightOldestTimestamp) {
        this.rightOldestTimestamp = rightOldestTimestamp;
    }

    public long getRightLatestTimestamp() {
        return rightLatestTimestamp;
    }

    public void setRightLatestTimestamp(long rightLatestTimestamp) {
        this.rightLatestTimestamp = rightLatestTimestamp;
    }

    public long getLeftOldestTimestamp() {
        return leftOldestTimestamp;
    }

    public long getLeftLatestTimestamp() {
        return leftLatestTimestamp;
    }

    public void setLeftOldestTimestamp(long leftOldestTimestamp) {
        this.leftOldestTimestamp = leftOldestTimestamp;
    }

    public void setLeftLatestTimestamp(long leftLatestTimestamp) {
        this.leftLatestTimestamp = leftLatestTimestamp;
    }

    @Override
    public String toJSON() {
        String jsonString = null;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(MessageConfig.ID_CONFIG_KEY, expressionId);
            jsonObject.put(MessageConfig.VALUE_CONFIG_KEY, value);
            jsonObject.put(MessageConfig.EVENT_TIME_CONFIG_KEY, timestamp);

            jsonObject.put(MessageConfig.LEFT_LATEST_TIMESTAMP_CONFIG_KEY, leftLatestTimestamp);
            jsonObject.put(MessageConfig.LEFT_OLDEST_TIMESTAMP_CONFIG_KEY, leftOldestTimestamp);

            jsonObject.put(MessageConfig.RIGHT_LATEST_TIMESTAMP_CONFIG_KEY, rightLatestTimestamp);
            jsonObject.put(MessageConfig.RIGHT_OLDEST_TIMESTAMP_CONFIG_KEY, rightOldestTimestamp);

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
            value =  jsonObject.get(MessageConfig.VALUE_CONFIG_KEY);
            timestamp = jsonObject.getLong(MessageConfig.EVENT_TIME_CONFIG_KEY);

            rightLatestTimestamp = jsonObject.getLong(MessageConfig.RIGHT_LATEST_TIMESTAMP_CONFIG_KEY);
            rightOldestTimestamp = jsonObject.getLong(MessageConfig.RIGHT_OLDEST_TIMESTAMP_CONFIG_KEY);

            leftOldestTimestamp = jsonObject.getLong(MessageConfig.LEFT_OLDEST_TIMESTAMP_CONFIG_KEY);
            leftLatestTimestamp = jsonObject.getLong(MessageConfig.LEFT_LATEST_TIMESTAMP_CONFIG_KEY);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
