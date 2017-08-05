package cowbird.flink.common.messages.sensor;

import cowbird.flink.common.abs.Message;
import cowbird.flink.common.abs.MessageInterface;
import cowbird.flink.common.config.MessageConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class SensorMessage extends Message implements MessageInterface {

    private long ingestionTime;

    private long eventTime;

    private Object value;

    public SensorMessage() {
        super();
    }

    /**
     * Instantiates a new sensor message to ingest to Flink.
     *
     * @param expressionId
     *            the SWAN-song expression id
     */
    public SensorMessage(String expressionId) {
        super(expressionId);
    }

    /**
     * Instantiates a new sensor message to ingest to Flink.
     *
     * @param expressionId
     *            the SWAN-song expression identifier
     * @param value
     *            the sensor value
     * @param ingestionTime
     *           the ingestion time
     * @param eventTime
     *           the event time
     */
    public SensorMessage(String expressionId, Object value, long ingestionTime, long eventTime) {
        this(expressionId);

        this.value = value;

        this.ingestionTime = ingestionTime;
        this.eventTime = eventTime;
    }


    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public long getEventTime() {
        return eventTime;
    }

    public long getIngestionTime() {
        return ingestionTime;
    }


    @Override
    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        String jsonString = null;
        try {
            jsonObject.put(MessageConfig.ID_CONFIG_KEY, expressionId);
            jsonObject.put(MessageConfig.VALUE_CONFIG_KEY, value);
            jsonObject.put(MessageConfig.INGESTION_TIME_CONFIG_KEY, ingestionTime);
            jsonObject.put(MessageConfig.EVENT_TIME_CONFIG_KEY, eventTime);

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
            value = jsonObject.getDouble(MessageConfig.VALUE_CONFIG_KEY);
            ingestionTime = jsonObject.getLong(MessageConfig.INGESTION_TIME_CONFIG_KEY);
            eventTime = jsonObject.getLong(MessageConfig.EVENT_TIME_CONFIG_KEY);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
