package distributed.frontend;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import distributed.Roles;
import distributed.frontend.location.Coordinate;
import distributed.frontend.location.LocationService;
import distributed.frontend.resource.CowbirdResourceState;
import distributed.frontend.resource.CowbirdResourceStateComparator;
import distributed.messages.DeregisterWorkMessage;

import distributed.node.CowbirdState;
import engine.ExpressionListener;
import engine.TriStateExpressionListener;
import engine.ValueExpressionListener;

import interdroid.swancore.swansong.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.locks.ReentrantLock;

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

    private ActorRef frontendResourceManagerActor;

    private String getExpressionId() {
        return EXPRESSION_PREFIX + expressionIdentifier++;
    }

    private HashMap<String, ActorRef> frontendActorsMap = new HashMap<>();

    private ReentrantLock resourceLock = new ReentrantLock();

    private Map<String, CowbirdResourceState> cowbirdStateMap = new HashMap<>();

    private FrontendManager() {
        final Config config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + getPort())
                .withFallback(ConfigFactory.parseString(String.format("akka.cluster.roles = [%s]", Roles.COWBIRD_FRONTEND)))
                .withFallback(ConfigFactory.load());

        this.system = ActorSystem.create("CowbirdClusterSystem", config);

        frontendResourceManagerActor = system.actorOf(FrontendResourceManagerActor.props(), Roles.COWBIRD_FRONTEND_RESOURCE_MANAGER);
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

    protected void registerCowbirdNode(CowbirdState cowbirdState) {
        resourceLock.lock();
        try {

            CowbirdResourceState resourceState = new CowbirdResourceState(cowbirdState);
            String key = cowbirdState.getCowbirdRef().path().toString();

            if(cowbirdStateMap.containsKey(key)) {
                resourceState = cowbirdStateMap.get(key);
                resourceState.setState(cowbirdState);
            } else {
                String ip = cowbirdState.getCowbirdRef().path().address().host().get();
                Coordinate coordinates = LocationService.sharedInstance().getCoordinatesFromIP(ip);
                resourceState.setCoordinates(coordinates);
            }

            cowbirdStateMap.put(cowbirdState.getCowbirdRef().path().toString(), resourceState);

        } finally {
            resourceLock.unlock();
        }
    }

    public CowbirdResourceState getCowbirdResource(Coordinate requestCoordinates) {
        resourceLock.lock();
        try {
            Iterator<String> iterator = cowbirdStateMap.keySet().iterator();

            PriorityQueue<CowbirdResourceState> resourceStatesQueue = new PriorityQueue<>(8, new CowbirdResourceStateComparator());
            while (iterator.hasNext()) {
                String key = iterator.next();
                CowbirdResourceState resourceState = cowbirdStateMap.get(key);
                if(resourceState.getResourceUtilization() >= resourceState.getState().getCurrentLoad()) {
                    continue;
                }

                double distance = LocationService.sharedInstance().distance(requestCoordinates, resourceState.getCoordinates());
                resourceState.setDistanceFromRequest(distance);

                resourceStatesQueue.add(resourceState);
            }

            if(resourceStatesQueue.size() > 0) {
                CowbirdResourceState resourceState = resourceStatesQueue.poll();
                resourceState.setResourceUtilization(resourceState.getResourceUtilization() + 1);

                return resourceState;
            }
        } finally {
            resourceLock.unlock();
        }

        return null;
    }
}
