package engine.remote;

/*  An object of this class monitors latency taken by the Streams layer for evaluating offloaded expressions.    */
/*  The monitored latency might not be the total time required by the system to evaluate the entire SWAN expression.    */

import java.util.HashMap;
import java.util.Map;


public class RemoteEvaluationLatencyMonitor {

    private static RemoteEvaluationLatencyMonitor instance = new RemoteEvaluationLatencyMonitor();

    public static RemoteEvaluationLatencyMonitor sharedInstance() {
        return instance;
    }

    private Map<String, Long> producingTimestampsMap = new HashMap<>();


    public long getLatency(String expressionId) {

        if(producingTimestampsMap.containsKey(expressionId)) {
            long timestamp = producingTimestampsMap.get(expressionId);
            long now = System.currentTimeMillis();
            producingTimestampsMap.put(expressionId, now);
            return now - timestamp;
        }

        // return now - timestamp;
        return 0;
    }

    public void register(String expressionId) {
        producingTimestampsMap.put(expressionId, System.currentTimeMillis());
    }

}
