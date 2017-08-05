package cowbird.flink.common.abs;

/**
 * Created by gdibernardo on 18/07/2017.
 */

public abstract class Message implements MessageInterface {

    public static long INVALID_TIMESTAMP = -1;

    protected Message() {
        this.timestamp = System.currentTimeMillis();
    }

    protected String expressionId;

    protected long timestamp;

    protected Message(String expressionId) {
        this();
        this.expressionId = expressionId;
    }

    public void setExpressionId(String expressionId) {
        this.expressionId = expressionId;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getExpressionId() {
        return expressionId;
    }
}
