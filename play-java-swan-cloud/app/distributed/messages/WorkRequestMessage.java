package distributed.messages;

import distributed.Work;

import java.io.Serializable;

/**
 * Created by gdibernardo on 06/07/2017.
 */


public class WorkRequestMessage implements Serializable {

    private String localIdentifier;

    private Work work;


    public Work getWork() {
        return work;
    }


    public String getLocalIdentifier() {
        return localIdentifier;
    }


    public WorkRequestMessage(String localIdentifier, Work work) {
        this.localIdentifier = localIdentifier;
        this.work = work;
    }
}
