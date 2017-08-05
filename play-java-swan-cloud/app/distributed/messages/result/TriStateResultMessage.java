package distributed.messages.result;

import interdroid.swancore.swansong.TriState;

/**
 * Created by gdibernardo on 06/07/2017.
 */


public class TriStateResultMessage extends ResultMessage {

    private TriState state;


    public TriStateResultMessage(String identifier, long timestamp, TriState state) {
        super(identifier, timestamp);
        this.state = state;
    }


    public TriState getState() {
        return this.state;
    }


    public void setState(TriState state) {
        this.state = state;
    }
}
