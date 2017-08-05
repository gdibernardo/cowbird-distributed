package distributed;

import akka.actor.AbstractActor;
import akka.actor.Props;

import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;

import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by gdibernardo on 03/07/2017.
 */


public class CowbirdClusterMonitor extends AbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    Cluster cluster = Cluster.get(getContext().system());


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
                .match(ClusterEvent.MemberUp.class, memberUp -> {
                    log.info("Cowbird node is up {}", memberUp.member());
                })
                .match(ClusterEvent.UnreachableMember.class, memberUnreacheable -> {
                    log.info("Cowbird node detected as unreacheable {}", memberUnreacheable.member());
                })
                .match(ClusterEvent.MemberRemoved.class, memberRemoved -> {
                    log.info("Cowbird node removed {}", memberRemoved.member());
                })
                .match(ClusterEvent.MemberEvent.class, memberEvent -> {
                    // ignore
                })
                .build();
    }


    public static Props props() {
        return Props.create(CowbirdClusterMonitor.class);
    }
}
