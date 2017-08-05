package distributed.messages;

import java.io.Serializable;

/**
 * Created by gdibernardo on 06/07/2017.
 */


public class DeregisterWorkMessage implements Serializable {

    private String identifier;


    public DeregisterWorkMessage(String identifier) {
        this.identifier = identifier;
    }


    public String getIdentifier() {
        return identifier;
    }
}
