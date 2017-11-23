package engine;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SensedDataBroker {

    Map<String, ConcurrentLinkedQueue<Double>> sensedValues = new HashMap<>();

    private static SensedDataBroker broker = new SensedDataBroker();

    public static SensedDataBroker sharedInstance() {
        return broker;
    }

    public void addSensor(String sensorId) {
        sensedValues.put(sensorId, new ConcurrentLinkedQueue<Double>());
    }

    public void removeSensor(String sensorId) {
        sensedValues.remove(sensorId);
    }


    public boolean hasValue(String sensorId) {
        if(sensedValues.containsKey(sensorId)) {
            ConcurrentLinkedQueue<Double> queue = sensedValues.get(sensorId);
            if (queue.size() > 0) {
                return true;
            }
        }

        return false;
    }

    public double getValue(String sensorId) {

        ConcurrentLinkedQueue<Double> queue = sensedValues.get(sensorId);

        return queue.poll();
    }
}
