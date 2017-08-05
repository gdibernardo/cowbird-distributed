package distributed.frontend;

import akka.actor.Props;

import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;

import distributed.NodeActor;
import distributed.Work;

import distributed.messages.AckWorkRequestMessage;
import distributed.messages.DeregisterWorkMessage;
import distributed.messages.NodeActorConnectingMessage;
import distributed.messages.WorkRequestMessage;
import distributed.messages.ping.AckPingMessage;
import distributed.messages.ping.PingMessage;
import distributed.messages.result.ResultMessage;
import distributed.messages.result.TriStateResultMessage;
import distributed.messages.result.ValueResultMessage;

import engine.ExpressionListener;

import interdroid.swancore.swansong.Expression;
import interdroid.swancore.swansong.TriStateExpression;
import interdroid.swancore.swansong.ValueExpression;


/**
 * Created by gdibernardo on 06/07/2017.
 */


public class FrontendActor extends NodeActor {

    private String expressionIdentifier;

    private String remoteIdentifier;

    private ExpressionListener listener;
    private Expression expression;

    private Cluster cluster = Cluster.get(getContext().system());


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
                    sendWorkToMaster();
                })
                .match(AckWorkRequestMessage.class, ackWorkRequestMessage -> {
                    remoteIdentifier = ackWorkRequestMessage.getRemoteIdentifier();
                })
                .match(ResultMessage.class, message -> {
                    handleResultMessage(message);
                })
                .match(DeregisterWorkMessage.class, deregisterWorkMessage -> {
                    deregisterFromMaster();
                })
                .match(ClusterEvent.MemberUp.class, memberUp -> {
                    //  DO STUFF
                })
                .build();
    }


    private void handleResultMessage(ResultMessage resultMessage) {
        if (resultMessage instanceof TriStateResultMessage) {
            TriStateResultMessage message = (TriStateResultMessage) resultMessage;
            listener.onNewState(expressionIdentifier, message.getTimestamp(), message.getState());
        }

        if(resultMessage instanceof ValueResultMessage) {
            ValueResultMessage message = (ValueResultMessage) resultMessage;
            listener.onNewValues(expressionIdentifier, message.getValues());
        }
    }


    public FrontendActor(String expressionIdentifier, Expression expression, ExpressionListener listener) {
        super();

        this.expressionIdentifier = expressionIdentifier;
        this.expression = expression;
        this.listener = listener;
    }


    private void pingMaster() {
        sendToMaster(new PingMessage());
    }


    private void sendWorkToMaster() {

        Work work = null;

        if(expression instanceof ValueExpression) {
            work = Work.valueExpressionWork((ValueExpression) expression);
        }

        if(expression instanceof TriStateExpression) {
            work = Work.tristateExpressionWork((TriStateExpression) expression);
        }

        WorkRequestMessage message = new WorkRequestMessage(expressionIdentifier, work);
        sendToMaster(message);
    }


    private void deregisterFromMaster() {
        DeregisterWorkMessage message = new DeregisterWorkMessage(remoteIdentifier);
        sendToMaster(message);
        /*  Probably it is not the best way to shutdown the actor.   */
        context().stop(self());
    }


    /*  Default props.  */
    public static Props props(String expressionIdentifier, Expression expression, ExpressionListener listener) {
       return Props.create(FrontendActor.class, expressionIdentifier, expression, listener);
    }
}
