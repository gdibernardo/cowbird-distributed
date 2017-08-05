package cowbird.flink.common.messages.control;

import cowbird.flink.common.abs.Message;
import cowbird.flink.common.config.MessageConfig;

import org.json.JSONException;
import org.json.JSONObject;


public class ControlMessage extends Message {

    protected int historyReductionMode;

    protected long historyLength;


    public ControlMessage() {
        super();
    }


    public ControlMessage(String expressionId) {
        super(expressionId);
    }


    public ControlMessage(String expressionId, int historyReductionMode, long historyLength, long timestamp) {
        this(expressionId);

        this.historyReductionMode = historyReductionMode;
        this.historyLength = historyLength;

        this.timestamp = timestamp;
    }


    public int getHistoryReductionMode() {
        return historyReductionMode;
    }


    public long getHistoryLength() {
        return historyLength;
    }


    public void setHistoryLength(long historyLength) {
        this.historyLength = historyLength;
    }


    public void setHistoryReductionMode(int historyReductionMode) {
        this.historyReductionMode = historyReductionMode;
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
            historyReductionMode = jsonObject.getInt(MessageConfig.HISTORY_REDUCTION_MODE_CONFIG_KEY);
            historyLength = jsonObject.getLong(MessageConfig.HISTORY_LENGTH_CONFIG_KEY);
            timestamp = jsonObject.getLong(MessageConfig.EVENT_TIME_CONFIG_KEY);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
