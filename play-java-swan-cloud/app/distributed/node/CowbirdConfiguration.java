package distributed.node;

import akka.actor.ActorRef;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gdibernardo on 05/07/2017.
 */


public class CowbirdConfiguration {

    private final static int DEFAULT_SYSTEM_LOAD = 8;

    public final static int DEFAULT_FOG_PORT = 10000;
    private int fogPort = DEFAULT_FOG_PORT;


    private AtomicInteger currentLoad;

    private int systemLoad;

    private ActorRef cowbirdInstance;

    private static CowbirdConfiguration instance = new CowbirdConfiguration(DEFAULT_SYSTEM_LOAD);

    private CowbirdNodeType nodeType;

    public static int defaultSystemLoad() {
        return DEFAULT_SYSTEM_LOAD;
    }


    public static CowbirdConfiguration nodeConfiguration() {
        return instance;
    }


    public int getFogPort() {
        return fogPort++;
    }

    public CowbirdConfiguration(int systemLoad) {
        this.systemLoad = systemLoad;
        this.currentLoad = new AtomicInteger(0);

        nodeType = CowbirdNodeType.FOG_NODE;
    }

    public void setCowbirdInstance(ActorRef cowbirdInstance) {
        this.cowbirdInstance = cowbirdInstance;
    }


    public void setSystemLoad(int systemLoad) {
        this.systemLoad = systemLoad;
    }


    public CowbirdState state() {
        return new CowbirdState(systemLoad, currentLoad.get(), cowbirdInstance, nodeType);
    }


    public void increaseCurrentLoad(int delta) {
        currentLoad.addAndGet(delta);
    }


    public void decreaseCurrentLoad(int delta) {
        currentLoad.decrementAndGet();
    }


    public int getSystemLoad() {
        return systemLoad;
    }

    public int hashCode() {
        if(cowbirdInstance != null) {
            return cowbirdInstance.hashCode();
        }

        return 0;
    }


    public void setNodeType(CowbirdNodeType nodeType) {
        this.nodeType = nodeType;
    }

    public CowbirdNodeType getNodeType() {
        return nodeType;
    }
}
