package sensors.impl;

import sensors.base.AbstractSwanSensor;
import sensors.base.SensorPoller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SoundSensor extends AbstractSwanSensor {

    private Map<String, SoundPoller> activeThreads = new HashMap<String, SoundPoller>();

    public static final String VALUE = "value";

    class SoundPoller extends SensorPoller {

        private long SENSOR_DELAY = 500;

        float i = 0;

        Random rand = new Random();

        float min = 0.0f;
        float max = 100.0f;

        SoundPoller(String id, String valuePath, HashMap configuration) {
            super(id, valuePath, configuration);
        }

        public void run() {
            while (!isInterrupted()) {
                long now = System.currentTimeMillis();

                i = rand.nextFloat() * (max - min) + min;

                updateResult(SoundSensor.this, i, now);

                try {
                    Thread.sleep(SENSOR_DELAY);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    @Override
    public void register(String id, String valuePath, HashMap configuration, HashMap httpConfiguration) {

        super.register(id,valuePath,configuration,httpConfiguration);

        SoundPoller soundPoller = new SoundPoller(id, valuePath, configuration);

        activeThreads.put(id, soundPoller);
        soundPoller.start();

    }

    @Override
    public void unregister(String id) {
        super.unregister(id);
        System.out.println("SoundSensor:    Unregister sensor called.");
        activeThreads.remove(id).interrupt();
    }


    @Override
    public String[] getValuePaths()  {
        return new String[]{ VALUE};
    }

    @Override
    public String getEntity() {
        return "sound";
    }

    @Override
    public String[] getConfiguration() {
        return new String[] {"delay"};
    }

    @Override
    public boolean isHighFrequency() {
        return false;
    }
}
