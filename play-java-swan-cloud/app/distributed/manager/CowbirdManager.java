package distributed.manager;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.cluster.Member;
import akka.cluster.pubsub.DistributedPubSub;
import akka.cluster.pubsub.DistributedPubSubMediator;

import akka.event.Logging;
import akka.event.LoggingAdapter;

import distributed.Roles;
import distributed.Work;

import distributed.messages.*;

import distributed.messages.ping.AckPingMessage;
import distributed.messages.ping.PingMessage;
import distributed.messages.result.ResultMessage;

import distributed.node.CowbirdNodeType;
import distributed.node.CowbirdState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by gdibernardo on 03/07/2017.
 */


public class CowbirdManager extends AbstractActor {


    private static final int COWBIRDS_INITIAL_CAPACITY = 5;

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    /*  This hashmap keeps track of all expressions registered from a certain frontend host.    */
    private HashMap<String, ArrayList<String>> frontendHostsMap = new HashMap<>() ;
    /*  This hashmap keeps track of which frontend actor is responsible of a certain expression.    */
    private HashMap<String, ActorRef> frontendActorMap = new HashMap();
    /*  Cowbirds heap.  */
    private PriorityQueue<CowbirdState> fogCowbirds = new PriorityQueue<>(COWBIRDS_INITIAL_CAPACITY, new CowbirdStateComparator());
    private PriorityQueue<CowbirdState> cloudCowbirds = new PriorityQueue<>(COWBIRDS_INITIAL_CAPACITY, new CowbirdStateComparator());

    private ActorRef mediator;
    /*  This hashmap maps a Cowbird node instance to its host. */
    private HashMap<String, CowbirdState> hostCowbirdMap = new HashMap<>();

    /*  Frontend(s) list.   */
    private List<ActorRef> frontendsList = new ArrayList<>();

    private Cluster cluster = Cluster.get(getContext().system());

    @Override
    public void preStart() throws Exception {
        // super.preStart();
        cluster.subscribe(self(),
                ClusterEvent.initialStateAsEvents(),
                ClusterEvent.MemberEvent.class,
                ClusterEvent.UnreachableMember.class);
    }


    @Override
    public void postStop() throws Exception {
        // super.postStop();
        cluster.unsubscribe(self());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PingMessage.class, pingMessage -> {
                    getSender().tell(new AckPingMessage(), getSelf());
                })
                .match(WorkRequestMessage.class, workRequestMessage -> {
                    String localIdentifier = workRequestMessage.getLocalIdentifier();
                    String remoteIdentifier = WorkStateManager.sharedInstance().appendWork(workRequestMessage.getWork());

                    registerFrontend(remoteIdentifier, getSender());
                    /*  Ack the sender. */
                    getSender().tell(new AckWorkRequestMessage(localIdentifier, remoteIdentifier), getSelf());
                    /*  Notify cowbirds.    */
                    notifyCowbirds();
                })
                .match(CowbirdRegistrationMessage.class, cowbirdRegistrationMessage -> {
                    log.info("cowbird-manager received a registration message. From: {}", cowbirdRegistrationMessage.getCowbirdState().getCowbirdRef().path().address().hostPort());

                    for(int index = 0; index < frontendsList.size(); index++) {
                        frontendsList.get(index).tell(cowbirdRegistrationMessage, self());
                    }

                    CowbirdState state = cowbirdRegistrationMessage.getCowbirdState();

                    hostCowbirdMap.put(state.getCowbirdRef().path().address().hostPort(), state);
                    if(state.getNodeType() == CowbirdNodeType.FOG_NODE) {
                        fogCowbirds.add(state);
                    } else {
                        cloudCowbirds.add(state);
                    }
                    if(WorkStateManager.sharedInstance().hasWork()) {
                        notifyCowbirds();
                    }
                })
                .match(FrontendRegistrationMessage.class, frontendRegistrationMessage -> {
                    frontendsList.add(sender());
                })
                .match(ResultMessage.class, message -> {
                    handleResultMessage(message);
                })
                .match(DeregisterWorkMessage.class, deregisterWorkMessage -> {
                    ActorRef cowbird = WorkStateManager.sharedInstance().unregisterCowbirdFromWork(deregisterWorkMessage.getIdentifier());
                    cowbird.tell(deregisterWorkMessage, getSelf());
                    deregisterFrontend(deregisterWorkMessage.getIdentifier());
                })
                .match(ClusterEvent.MemberRemoved.class, memberRemoved -> {
                    Member member = memberRemoved.member();
                    if(member.hasRole(Roles.COWBIRD_NODE)) {
                        log.info("Cowbird node detected as removed {}", member);
                        CowbirdState state = hostCowbirdMap.get(member.address().hostPort());
                        handleCowbirdDisconnection(state);
                    }

                    if(member.hasRole(Roles.COWBIRD_FRONTEND)) {
                        log.info("Frontend node detected as removed {}", member);
                        handleFrontendDisconnection(member);
                    }
                })
                .match(ClusterEvent.UnreachableMember.class, unreachableMember -> {
                    Member member = unreachableMember.member();
                    if(member.hasRole(Roles.COWBIRD_NODE)) {
                        log.info("Cowbird node detected as unreacheable {}", member);
//                        CowbirdState state = hostCowbirdMap.get(member.address().hostPort());
//                        handleCowbirdDisconnection(state);
                    }
                    if(unreachableMember.member().hasRole(Roles.COWBIRD_FRONTEND)) {
                        log.info("Frontend node detected as unreacheable {}", member);
//                        handleFrontendDisconnection(member);
                    }
                })
                .build();
    }


    private void handleCowbirdDisconnection(CowbirdState state) {
        WorkStateManager.sharedInstance().reassignWorkFromCowbird(state.getCowbirdRef());
        if(state.getNodeType() == CowbirdNodeType.FOG_NODE) {
            fogCowbirds.remove(state);
        } else {
            cloudCowbirds.remove(state);
        }

        if(WorkStateManager.sharedInstance().hasWork()) {
            notifyCowbirds();
        }
    }


    private void handleFrontendDisconnection(Member member) {
        String hostPort = member.address().hostPort();

        ArrayList<String> expressions = frontendHostsMap.get(hostPort);

        for (String expression : expressions) {
            ActorRef cowbird = WorkStateManager.sharedInstance().unregisterCowbirdFromWork(expression);
            DeregisterWorkMessage message = new DeregisterWorkMessage(expression);
            cowbird.tell(message, getSelf());
            frontendActorMap.remove(expression);
        }

        frontendHostsMap.remove(hostPort);
    }


    private void handleResultMessage(ResultMessage message) {
        ActorRef frontendRef = frontendActorMap.get(message.getIdentifier());

        frontendRef.tell(message, getSelf());
    }


    private void registerCowbird(ActorRef cowbird) {
            Work work = WorkStateManager.sharedInstance().registerCowbirdForWork(cowbird);

            WorkMessage message = new WorkMessage(work);

            cowbird.tell(message, getSelf());
    }


    private void notifyCowbirds() {

        log.info("Cowbirds notified");
        if (fogCowbirds.size() > 0) {
            CowbirdState head = fogCowbirds.peek();

            /*  Fog workload threshold. */
            if ((head.getCurrentLoad() < head.getSystemLoad() * 2) || (cloudCowbirds.size() == 0)) {
                registerCowbird(fogCowbirds.poll().getCowbirdRef());
                return;
            }
        }

        if (cloudCowbirds.size() > 0) {
            registerCowbird(cloudCowbirds.poll().getCowbirdRef());
            return;
        }

        log.info("CowbirdManager:notifyCowbirds()   -   No cowbirds available for work at the moment.");
    }


//        if(cowbirds.size() > 0) {
//            ActorRef cowbird = cowbirds.poll().getCowbirdRef();
//
//            Work work = WorkStateManager.sharedInstance().registerCowbirdForWork(cowbird);
//
//            WorkMessage message = new WorkMessage(work);
//
//            cowbird.tell(message, getSelf());
//        } else {
//            log.info("CowbirdManager:notifyCowbirds()   -   No cowbirds available for work at the moment.");
//        }
//     }


    public void registerFrontend(String expressionIdentifier, ActorRef frontend) {
        if(this.frontendActorMap.containsKey(expressionIdentifier)) {
            log.info("CowbirdManager:registerFrontend() -   expression identifier already put in frontend map. Shall I abort?!");
        }

        String hostPort = frontend.path().address().hostPort();
        ArrayList<String> expressions;

        if(frontendHostsMap.containsKey(hostPort)) {
            expressions = frontendHostsMap.get(hostPort);
        } else {
            expressions = new ArrayList<>();
        }

        expressions.add(expressionIdentifier);

        frontendHostsMap.put(hostPort, expressions);
        frontendActorMap.put(expressionIdentifier, frontend);
    }


    public void deregisterFrontend(String expressionIdentifier) {
        ActorRef frontend = frontendActorMap.remove(expressionIdentifier);
        String hostPort = frontend.path().address().hostPort();

        ArrayList<String> expressions = frontendHostsMap.get(hostPort);
        expressions.removeIf(expression -> expression.equals(expressionIdentifier));

        if(expressions.size() == 0) {
            frontendHostsMap.remove(hostPort);
        } else {
            frontendHostsMap.put(hostPort, expressions);
        }
    }


    public CowbirdManager() {
        /*  Registrating mediator actor ref for receiving CowbirdRegistrationMessages.    */
        mediator = DistributedPubSub.get(getContext().system()).mediator();
        mediator.tell(new DistributedPubSubMediator.Put(getSelf()), getSelf());
    }


    public static Props props() {
        return Props.create(CowbirdManager.class);
    }
}
