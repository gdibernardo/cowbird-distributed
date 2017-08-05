package distributed.node;

import akka.actor.ActorRef;

import java.io.Serializable;

/**
 * Created by gdibernardo on 05/07/2017.
 */


public class CowbirdState implements Serializable {

    private int currentLoad;
    private int systemLoad;

    private ActorRef cowbirdRef;


    public CowbirdState(int systemLoad, int currentLoad, ActorRef cowbirdRef) {
        this(systemLoad, currentLoad);

        this.cowbirdRef = cowbirdRef;
    }


    public CowbirdState(int systemLoad, int currentLoad) {
        this.systemLoad = systemLoad;
        this.currentLoad = currentLoad;
    }

    
    public int getCurrentLoad() {
        return currentLoad;
    }


    public int getSystemLoad() {
        return systemLoad;
    }


    public ActorRef getCowbirdRef() {
        return cowbirdRef;
    }


    protected void setCowbirdRef(ActorRef cowbirdRef) {
        this.cowbirdRef = cowbirdRef;
    }
}
