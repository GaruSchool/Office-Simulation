package RemoteActors;

import java.io.*;
import java.net.Socket;

/**
 * Created by t.garuglieri on 05/12/14.
 */
public class RemoteClient {


    public static final String MESSAGE_CLIENT = "#CLIENT_ENTERED";
    public static final String MESSAGE_EMPLOYEE = "#EMPLOYEE_DONE";
    public static final String MESSAGE_CLIENT_EXITED = "#CLIENT_EXITED";
    private Socket socket;

    public void connect(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
            this.onConnected();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onConnected() {
        sendMessage(MESSAGE_CLIENT);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            String response = reader.readLine();

            if(response.equals(MESSAGE_CLIENT_EXITED))
                this.socket.close();


        } catch (IOException e) {
            //TODO handle errors
        }
    }


    public void sendMessage(String message) {
        try {
            new PrintWriter(socket.getOutputStream(), true).println(message);
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
