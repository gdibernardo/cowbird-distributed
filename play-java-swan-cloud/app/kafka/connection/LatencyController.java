package kafka.connection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LatencyController {

    private static LatencyController controller = new LatencyController();

    public static LatencyController sharedInstance() {
        return controller;
    }

    private ConcurrentMap<String, Long> timestampMap = new ConcurrentHashMap<>();

    public void set(String expressionId, Long timestamp) {
        timestampMap.put(expressionId, timestamp);
    }

    public void remove(String expressionId) {
        timestampMap.remove(expressionId);
    }

    public Long get(String expressionId) {
        return timestampMap.get(expressionId);
    }

    public boolean contains(String expressionId) {
        return timestampMap.containsKey(expressionId);
    }
}

