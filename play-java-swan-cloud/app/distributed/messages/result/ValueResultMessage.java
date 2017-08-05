package distributed.messages.result;

import interdroid.swancore.swansong.TimestampedValue;

/**
 * Created by gdibernardo on 06/07/2017.
 */


public class ValueResultMessage extends ResultMessage {


    private TimestampedValue[] values;


    public ValueResultMessage(String identifier, long timestamp, TimestampedValue[] values) {
        super(identifier, timestamp);
        this.values = values;
    }


    public TimestampedValue[] getValues() {
        return this.values;
    }
}
