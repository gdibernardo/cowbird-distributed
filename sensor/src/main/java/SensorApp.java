import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import communication.CommunicationController;
import communication.LatencyController;
import org.json.JSONObject;

import java.io.IOException;


public class SensorApp {

    private static JSONObject getCowbirdNode(String frontendAddress) {
        try {

            HttpResponse<String> response = Unirest.get("http://"+frontendAddress+":9000/register/fog/sensor")
                    .asString();

            JSONObject jsonObject = new JSONObject(response.getBody());
            return jsonObject;

        } catch (UnirestException exception) {
            System.out.println("Exception in sending request " + exception.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {

        if (args.length < 3) {
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

        long frequency = Long.parseLong(args[1]);

        String frontendAddress = args[2];

        JSONObject cowbirdNode = getCowbirdNode(frontendAddress);

        int cowbirdNodePort = cowbirdNode.getInt("port");
        String cowbirdNodeAddress = cowbirdNode.getString("ip");

        Sensor sensor = new Sensor();

        int id = 0;

        long lastAckedMessage = System.currentTimeMillis();
        int delay = 5000;

        try {
            CommunicationController controller = new CommunicationController(cowbirdNodeAddress, cowbirdNodePort);
            while (true) {

                long now = System.currentTimeMillis();

                JSONObject object = new JSONObject();
                int currentId = id++;
                object.put("id", currentId);
                object.put("data", sensor.getData());
                object.put("timestamp", now);
                object.put("ack", 0);

                if(now >= lastAckedMessage + delay) {
                    object.put("ack", 1);
                    LatencyController.sharedInstance().setOutgoingId(currentId);
                    LatencyController.sharedInstance().setOutgoingTimestamp(now);
                    lastAckedMessage = now;
                }

                controller.sendData(object.toString());



                try {
                    Thread.sleep(frequency);
                } catch (InterruptedException e) {
                    break;
                }
            }
        } catch(IOException exception) {
            System.out.println(exception.getLocalizedMessage());
        }
    }
}