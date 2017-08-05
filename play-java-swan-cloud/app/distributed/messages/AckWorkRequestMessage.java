package distributed.messages;

import java.io.Serializable;

/**
 * Created by gdibernardo on 06/07/2017.
 */


public class AckWorkRequestMessage implements Serializable {

    private String localIdentifier;
    private String remoteIdentifier;


    public AckWorkRequestMessage(String localIdentifier, String remoteIdentifier) {
        this.localIdentifier = localIdentifier;
        this.remoteIdentifier = remoteIdentifier;
    }


    public String getLocalIdentifier() {
        return localIdentifier;
    }


    public String getRemoteIdentifier() {
        return remoteIdentifier;
    }
}
