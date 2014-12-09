package RemoteActors;

import Helpers.InputHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by t.garuglieri on 05/12/14.
 */
public class RemoteClient extends RemoteClientInterface {


    public static final String MESSAGE_CLIENT = "#CLIENT_ENTERED";
    public static final String MESSAGE_EMPLOYEE = "#EMPLOYEE_DONE";
    public static final String MESSAGE_CLIENT_EXITED = "#CLIENT_EXITED";
    private Socket socket;

    public void connect(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
            this.onConnected();
        } catch (IOException e) {
            System.out.println("Impossibile connettersi al server");
        }
    }

    private void onConnected() {

        try {

            InputHelper.getInputInterface(InputHelper.INPUT_INTERFACE_KEYBOARD).getInput();

            sendMessage(MESSAGE_CLIENT);

            onClientEntered();

            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String response = reader.readLine();

            if (response.equals(MESSAGE_CLIENT_EXITED)) {
                onClientLeave();
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

    @Override
    public void onClientEntered() {
        onClientQueued();
    }

    @Override
    public void onClientLeave() {

    }

    @Override
    public void onClientQueued() {

    }
}
