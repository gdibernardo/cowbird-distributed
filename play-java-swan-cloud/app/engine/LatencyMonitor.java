package engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LatencyMonitor {
    private static LatencyMonitor latencyMonitorInstance = new LatencyMonitor();

    private Map<String, ConcurrentLinkedQueue<Long>> notificationTimeMap = new HashMap<>();

    private Map<String, ConcurrentLinkedQueue<Long>> averageNotificationTimeMap = new HashMap<>();


    public static LatencyMonitor sharedInstance() {
        return latencyMonitorInstance;
    }


    public void registerForNotificationTime(String id) {
        notificationTimeMap.put(id, new ConcurrentLinkedQueue<Long>());
        averageNotificationTimeMap.put(id, new ConcurrentLinkedQueue<Long>());
    }

    public long getNotificationTime(String id) {
        if(notificationTimeMap.containsKey(id)) {
           Long timestamp = notificationTimeMap.get(id).poll();
           averageNotificationTimeMap.get(id).add(timestamp);
           return timestamp;
        } else {
            return 0;
        }
    }


    public void addNotificationTime(String id, long timestamp) {
        if(notificationTimeMap.containsKey(id)) {
            notificationTimeMap.get(id).add(timestamp);
        }
 //      else {
//            // throw new RuntimeException("Provided expression is not registered for notification time.");
//        }
    }

    public Long getNotificationTimeAverage(String id) {
        if(averageNotificationTimeMap.containsKey(id)) {
            ConcurrentLinkedQueue<Long> queue = averageNotificationTimeMap.get(id);
            int size = queue.size();
            if(size > 0) {
                Iterator<Long> iterator = queue.iterator();
                long sum = 0;
                while (iterator.hasNext()) {
                    sum += iterator.next();
                }

                return sum/size;
            }

            return new Long(0);

        } else {
            throw new RuntimeException("Provided expression is not registered for notification time.");
        }
    }
}
