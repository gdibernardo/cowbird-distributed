package distributed.node;

import akka.actor.Props;

import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.cluster.Member;

import akka.event.Logging;
import akka.event.LoggingAdapter;

import distributed.NodeActor;
import distributed.Roles;
import distributed.Work;
import distributed.messages.CowbirdRegistrationMessage;
import distributed.messages.DeregisterWorkMessage;
import distributed.messages.NodeActorConnectingMessage;
import distributed.messages.WorkMessage;
import distributed.messages.ping.AckPingMessage;
import distributed.messages.ping.PingMessage;
import distributed.messages.result.ResultMessage;
import distributed.messages.result.TriStateResultMessage;
import distributed.messages.result.ValueResultMessage;

import engine.ExpressionManager;
import engine.SwanException;
import engine.TriStateExpressionListener;
import engine.ValueExpressionListener;

import interdroid.swancore.swansong.TimestampedValue;
import interdroid.swancore.swansong.TriState;
import interdroid.swancore.swansong.TriStateExpression;
import interdroid.swancore.swansong.ValueExpression;



/**
 * Created by gdibernardo on 03/07/2017.
 */


public class CowbirdNode extends NodeActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private Cluster cluster = Cluster.get(getContext().system());

    public Cluster currentCluster() {
        return cluster;
    }


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
                .match(ClusterEvent.MemberUp.class, memberUp -> {
                    Member member = memberUp.member();
                    if(member.hasRole(Roles.COWBIRDS_MANAGER)) {
                        // DO STUFF
                    }
                })
                .match(NodeActorConnectingMessage.class, nodeActorConnectingMessage -> {
                    pingMaster();
                })
                .match(AckPingMessage.class, ackPingMessage -> {
                    cancelConnectingTask();
                    register();
                })
                .match(WorkMessage.class, work -> {
                    doWork(work.getWork());
                    register();
                })
                .match(DeregisterWorkMessage.class, deregisterWorkMessage -> {
                    deregisterWork(deregisterWorkMessage.getIdentifier());
                    register();
                })
                .build();
    }


    private void deregisterWork(String identifier) {
        ExpressionManager.unregisterExpression(identifier);
    }


    private void doWork(Work work) {
        switch (work.getType()) {
            case VALUE_EXPRESSION:
                doValueExpressionWork(work);
                break;
            case TRI_STATE_EXPRESSION:
                doTriStateExpressionWork(work);
                break;
            default:break;
        }
    }


    private void doValueExpressionWork(Work work) {
        ValueExpression expression = (ValueExpression) work.getExpression();
        String identifier = work.getIdentifier();

        try {
            ExpressionManager.registerValueExpression(identifier, expression, new ValueExpressionListener() {
                @Override
                public void onNewValues(String id, TimestampedValue[] newValues) {
                    if(newValues!=null && newValues.length>0) {
                        log.info("CowbirdNode produced some new values for expression: {}", id);
                        ValueResultMessage message = new ValueResultMessage(id, System.currentTimeMillis(), newValues);
                        notifyMasterWithResult(message);
                    }
                }
            });
        } catch (SwanException e) {
            e.printStackTrace();
        }
    }


    private void doTriStateExpressionWork(Work work) {
        TriStateExpression expression = (TriStateExpression) work.getExpression();
        String identifier = work.getIdentifier();

        try {
            ExpressionManager.registerTriStateExpression(identifier, expression, new TriStateExpressionListener() {
                @Override
                public void onNewState(String id, long timestamp, TriState newState) {
                    TriStateResultMessage message = new TriStateResultMessage(id, timestamp, newState);
                    notifyMasterWithResult(message);
                }
            });
        } catch (SwanException e) {
            e.printStackTrace();
        }
    }


    private void notifyMasterWithResult(ResultMessage message) {
        sendToMaster(message);
    }


    public CowbirdNode() {
        super();

        CowbirdConfiguration.nodeConfiguration().setCowbirdInstance(self());
    }


    public static Props props() {
        return Props.create(CowbirdNode.class);
    }


    public void register() {
        CowbirdRegistrationMessage message = new CowbirdRegistrationMessage(CowbirdConfiguration.nodeConfiguration().state());

        sendToMaster(message);
    }


    private void pingMaster() {
        PingMessage message = new PingMessage();
        sendToMaster(message);
    }
}
