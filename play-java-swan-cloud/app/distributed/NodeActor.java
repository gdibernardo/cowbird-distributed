package distributed;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Cancellable;

import akka.cluster.pubsub.DistributedPubSub;
import akka.cluster.pubsub.DistributedPubSubMediator;
import distributed.messages.NodeActorConnectingMessage;

import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by gdibernardo on 11/07/2017.
 */
public abstract class NodeActor extends AbstractActor {

    private static final int DEFAULT_DURATION_INTERVAL = 1;   //  seconds

    private ActorRef mediator;

    private Cancellable pingTask;


    public NodeActor() {

        this.mediator = DistributedPubSub.get(getContext().system()).mediator();

        this.pingTask = getContext().system().scheduler().schedule(Duration.Zero(),
                Duration.create(DEFAULT_DURATION_INTERVAL, TimeUnit.SECONDS),
                self(),
                new NodeActorConnectingMessage(),
                getContext().dispatcher(),
                null);
    }


    @Override
    public Receive createReceive() {
        return null;
    }


    public void cancelConnectingTask() {
        this.pingTask.cancel();
    }


    public static String pathToMaster() {
        return "/user/" + Roles.COWBIRDS_MANAGER;
    }


    public void sendToMaster(Object message) {
        mediator.tell(new DistributedPubSubMediator.Send(pathToMaster(), message, false), getSelf());
    }
}
