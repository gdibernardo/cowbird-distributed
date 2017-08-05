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

import distributed.node.CowbirdState;

import java.util.ArrayList;
import java.util.HashMap;
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
    private PriorityQueue<CowbirdState> cowbirds = new PriorityQueue<>(COWBIRDS_INITIAL_CAPACITY, new CowbirdStateComparator());

    private ActorRef mediator;
    /*  This hashmap maps a Cowbirdn node instance to its host. */
    private HashMap<String, CowbirdState> hostCowbirdMap = new HashMap<>();

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
                    hostCowbirdMap.put(cowbirdRegistrationMessage.getCowbirdState().getCowbirdRef().path().address().hostPort(), cowbirdRegistrationMessage.getCowbirdState());
                    cowbirds.add(cowbirdRegistrationMessage.getCowbirdState());
                    if(WorkStateManager.sharedInstance().hasWork()) {
                        notifyCowbirds();
                    }
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
                        CowbirdState state = hostCowbirdMap.get(member.address().hostPort());
                        handleCowbirdDisconnection(state);
                    }
                    if(unreachableMember.member().hasRole(Roles.COWBIRD_FRONTEND)) {
                        log.info("Frontend node detected as unreacheable {}", member);
                        handleFrontendDisconnection(member);
                    }
                })
                .build();
    }


    private void handleCowbirdDisconnection(CowbirdState state) {
        WorkStateManager.sharedInstance().reassignWorkFromCowbird(state.getCowbirdRef());
        cowbirds.remove(state);
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


    private void notifyCowbirds() {
        if(cowbirds.size() > 0) {
            ActorRef cowbird = cowbirds.poll().getCowbirdRef();

            Work work = WorkStateManager.sharedInstance().registerCowbirdForWork(cowbird);

            WorkMessage message = new WorkMessage(work);

            cowbird.tell(message, getSelf());
        } else {
            log.info("CowbirdManager:notifyCowbirds()   -   No cowbirds available for work at the moment.");
        }
    }


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
