package communication;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

public class AckPoller extends Thread {

    private BufferedReader inputStream;

    public AckPoller(BufferedReader inputStream) {
        this.inputStream = inputStream;
    }

    public void run() {
        while(!isInterrupted()) {
            try {
                String message = inputStream.readLine();
                JSONObject object = new JSONObject(message);

                int id = object.getInt("id");
                LatencyController.sharedInstance().setIncomingId(id);
                LatencyController.sharedInstance().setIncomingTimestamp(System.currentTimeMillis());
                System.out.println("Latency " + LatencyController.sharedInstance().getLatency());
            } catch (IOException exception) {
                System.out.println(exception.getLocalizedMessage());
            }
        }
    }
}
