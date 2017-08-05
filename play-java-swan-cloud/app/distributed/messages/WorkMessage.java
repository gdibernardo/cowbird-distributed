package distributed.messages;

import distributed.Work;

import java.io.Serializable;


/**
 * Created by gdibernardo on 05/07/2017.
 */


public class WorkMessage implements Serializable {

    private Work work;

    public WorkMessage(Work work) {
        this.work = work;
    }


    public Work getWork() {
        return work;
    }
}
