package distributed.frontend;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import distributed.Roles;
import distributed.messages.DeregisterWorkMessage;

import engine.ExpressionListener;
import engine.TriStateExpressionListener;
import engine.ValueExpressionListener;

import interdroid.swancore.swansong.*;

import java.util.HashMap;

/**
 * Created by gdibernardo on 06/07/2017.
 */

public class FrontendManager {

    private static final String EXPRESSION_PREFIX = "COWBIRD_LOCAL_EXPRESSION_";

    private int expressionIdentifier = 0;


    private int getPort() {
        return 0;
    }

    private ActorSystem system;


    public ActorSystem system() {
        return system;
    }


    private String getExpressionId() {
        return EXPRESSION_PREFIX + expressionIdentifier++;
    }


    private HashMap<String, ActorRef> frontendActorsMap = new HashMap<>();


    private FrontendManager() {
        final Config config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + getPort())
                .withFallback(ConfigFactory.parseString(String.format("akka.cluster.roles = [%s]", Roles.COWBIRD_FRONTEND)))
                .withFallback(ConfigFactory.load());

        this.system = ActorSystem.create("CowbirdClusterSystem", config);
    }


    private static FrontendManager instance = new FrontendManager();

    public static FrontendManager sharedInstance() {
        return instance;
    }


    public String registerTriStateExpression(TriStateExpression expression, TriStateExpressionListener listener) {
        ExpressionListener expressionListener = new ExpressionListener() {
            @Override
            public void onNewState(String id, long timestamp, TriState newState) {
                listener.onNewState(id, timestamp, newState);
            }

            @Override
            public void onNewValues(String id, TimestampedValue[] newValues) {
            }
        };

        return registerExpression((Expression) expression, expressionListener);
    }


    public String registerValueExpression(ValueExpression expression, ValueExpressionListener listener) {
        ExpressionListener expressionListener = new ExpressionListener() {
            @Override
            public void onNewState(String id, long timestamp, TriState newState) {

            }

            @Override
            public void onNewValues(String id, TimestampedValue[] newValues) {
                listener.onNewValues(id, newValues);
            }
        };

        return registerExpression((Expression) expression, expressionListener);
    }


    public void unregisterExpression(String expression) {
        if(frontendActorsMap.containsKey(expression)) {
            ActorRef ref = frontendActorsMap.get(expression);
            ref.tell(new DeregisterWorkMessage(expression), null);

            frontendActorsMap.remove(expression);
        } else {
            System.err.println("FrontendManager:unregisterExpression()    -   Can't unregister this expression");
        }
    }


    private String registerExpression(Expression expression, ExpressionListener listener) {

        String expressionIdentifier = getExpressionId();

        final ActorRef frontend = system.actorOf(FrontendActor.props(expressionIdentifier, expression, listener), Roles.COWBIRD_FRONTEND + (this.expressionIdentifier - 1));

        frontendActorsMap.put(expressionIdentifier, frontend);

        return expressionIdentifier;
    }

}
