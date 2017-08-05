package distributed.node;

import akka.actor.ActorRef;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gdibernardo on 05/07/2017.
 */


public class CowbirdConfiguration {

    private final static int DEFAULT_SYSTEM_LOAD = 8;


    private AtomicInteger currentLoad;

    private int systemLoad;

    private ActorRef cowbirdInstance;

    private static CowbirdConfiguration instance = new CowbirdConfiguration(DEFAULT_SYSTEM_LOAD);


    public static int defaultSystemLoad() {
        return DEFAULT_SYSTEM_LOAD;
    }


    public static CowbirdConfiguration nodeConfiguration() {
        return instance;
    }


    public CowbirdConfiguration(int systemLoad) {
        this.systemLoad = systemLoad;
        this.currentLoad = new AtomicInteger(0);
    }


    public void setCowbirdInstance(ActorRef cowbirdInstance) {
        this.cowbirdInstance = cowbirdInstance;
    }


    public void setSystemLoad(int systemLoad) {
        this.systemLoad = systemLoad;
    }


    public CowbirdState state() {
        return new CowbirdState(systemLoad, currentLoad.get(), cowbirdInstance);
    }


    public void increaseCurrentLoad(int delta) {
        currentLoad.addAndGet(delta);
    }


    public void decreaseCurrentLoad(int delta) {
        currentLoad.decrementAndGet();
    }
}
