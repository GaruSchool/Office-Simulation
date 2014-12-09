package RemoteActors.BaseClasses;

import Helpers.InputHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by t.garuglieri on 05/12/14.
 */
public abstract class BaseRemoteClient {


    public static final String MESSAGE_CLIENT = "#CLIENT_ENTERED";
    public static final String MESSAGE_EMPLOYEE = "#EMPLOYEE_DONE";
    public static final String MESSAGE_CLIENT_EXITED = "#CLIENT_EXITED";
    private Socket socket;

    public void connect(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
            this.onConnected();
        } catch (IOException e) {
            onOfficeClosed();
        }
    }

    private void onConnected() {

        try {
            onClientEntered();
            InputHelper.getInputInterface(InputHelper.INPUT_INTERFACE_DELAYED).getInput();

            sendMessage(MESSAGE_CLIENT);

            onClientQueued();

            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String response = reader.readLine();

            if (response.equals(MESSAGE_CLIENT_EXITED)) {
                onClientLeft();
                this.socket.close();
            }


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

    public abstract void onClientEntered();

    public abstract void onClientQueued();

    public abstract void onClientLeft();

    public abstract void onOfficeClosed();
}
