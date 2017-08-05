package distributed.manager;

import akka.actor.ActorRef;

import distributed.Work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * Created by gdibernardo on 05/07/2017.
 */
public class WorkStateManager {

    private static final String EXPRESSION_PREFIX = "COWBIRD_REMOTE_EXPRESSION_";

    private HashMap <String, ActorRef> workingState = new HashMap<>();

    private HashMap <String, ArrayList<Work>> hostWorkMap = new HashMap<>();

    private int expressionIdentifier = 0;

    private String getExpressionId() {
        return EXPRESSION_PREFIX + expressionIdentifier++;
    }


    private static WorkStateManager instance = new WorkStateManager();

    /*  WorkStateManager singleton. */
    public static WorkStateManager sharedInstance() {
        return instance;
    }

    private ConcurrentLinkedQueue<Work> incomingWorkQueue = new ConcurrentLinkedQueue<>();


    public String appendWork(Work work) {

        String id = getExpressionId();
        work.setIdentifier(id);
        incomingWorkQueue.add(work);

        return id;
    }

//    public Work getWork() {
//        return this.incomingWorkQueue.remove();
//    }


    public boolean hasWork() {
        return !incomingWorkQueue.isEmpty();
    }


    public Work registerCowbirdForWork(ActorRef cowbird) {

        Work work = incomingWorkQueue.remove();
        workingState.put(work.getIdentifier(), cowbird);

        String hostPort = cowbird.path().address().hostPort();

        ArrayList<Work> works;

        if(hostWorkMap.containsKey(hostPort)) {
            works = hostWorkMap.get(hostPort);
        } else {
            works = new ArrayList<>();
        }

        works.add(work);
        hostWorkMap.put(hostPort, works);

        return work;
    }


    public void reassignWorkFromCowbird(ActorRef cowbird) {

        String hostPort = cowbird.path().address().hostPort();
        if(!hostWorkMap.containsKey(hostPort)) {
            return;
        }

        ArrayList<Work> works = hostWorkMap.get(hostPort);

        incomingWorkQueue.addAll(works);

        hostWorkMap.remove(hostPort);
    }


    public ActorRef unregisterCowbirdFromWork(String identifier) {

        ActorRef cowbird = workingState.remove(identifier);

        String hostPort = cowbird.path().address().hostPort();

        ArrayList<Work> works = hostWorkMap.get(hostPort);

        works.removeIf(work -> work.getIdentifier() == identifier);

        if(works.size() == 0) {
            hostWorkMap.remove(hostPort);
        } else {
            hostWorkMap.put(hostPort, works);
        }

        return cowbird;
    }
}
