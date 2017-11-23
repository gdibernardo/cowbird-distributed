package distributed.frontend;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;

import akka.event.Logging;
import akka.event.LoggingAdapter;
import distributed.NodeActor;
import distributed.messages.CowbirdRegistrationMessage;
import distributed.messages.FrontendRegistrationMessage;
import distributed.messages.NodeActorConnectingMessage;
import distributed.messages.ping.AckPingMessage;
import distributed.messages.ping.PingMessage;


public class FrontendResourceManagerActor extends NodeActor {

    private Cluster cluster = Cluster.get(getContext().system());

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);


    @Override
    public void preStart() throws Exception {
        // super.preStart();
        cluster.subscribe(self(), ClusterEvent.MemberUp.class);
    }


    @Override
    public void postStop() throws Exception {
        // super.postStop();
        cluster.unsubscribe(self());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(NodeActorConnectingMessage.class, nodeActorConnectingMessage -> {
                    pingMaster();
                })
                .match(AckPingMessage.class, ackPingMessage -> {
                    cancelConnectingTask();
                    sendToMaster(new FrontendRegistrationMessage());
                })
                .match(CowbirdRegistrationMessage.class, cowbirdRegistrationMessage -> {
                    FrontendManager.sharedInstance().registerCowbirdNode(cowbirdRegistrationMessage.getCowbirdState());
                })
                .build();
    }

    private void pingMaster() {
        sendToMaster(new PingMessage());
    }

    /*  Default props.  */
    public static Props props() {
        return Props.create(FrontendResourceManagerActor.class);
    }
}
