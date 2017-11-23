package communication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CommunicationController {

    private Socket clientSocket;
    private DataOutputStream outputStream;
    private BufferedReader inputStream;


    private AckPoller ackPoller;

    public CommunicationController(String address, int port) throws IOException {
        clientSocket = new Socket(address, port);
        outputStream = new DataOutputStream(clientSocket.getOutputStream());
        inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    private void register() {

    }

    public void sendData(String data) throws IOException {
        outputStream.writeBytes(data);
        outputStream.writeBytes("\n");
        if(ackPoller == null) {
            ackPoller = new AckPoller(inputStream);
            ackPoller.start();
        }
    }

//    public void getAck() {
//        try {
//             String message = inputStream.readLine();
//            System.out.println("message " + message);
//        } catch (IOException exception) {
//            System.out.println(exception.getLocalizedMessage());
//        }
//    }
}
