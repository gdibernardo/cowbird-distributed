package distributed.messages.result;

import java.io.Serializable;

/**
 * Created by gdibernardo on 06/07/2017.
 */


public abstract class ResultMessage implements Serializable {

    private String identifier;


    private long timestamp;


    protected ResultMessage(String identifier, long timestamp) {
        this.identifier = identifier;
        this.timestamp = timestamp;
    }


    public String getIdentifier() {
        return this.identifier;
    }


    public long getTimestamp() {
        return this.timestamp;
    }


    public void setIdentifier(String id) {
        this.identifier = id;
    }


    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
