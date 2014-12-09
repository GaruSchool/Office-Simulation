package RemoteActors;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by t.garuglieri on 05/12/14.
 */
public class RemoteEmployee implements RemoteEmployeeListener {


    public static final String MESSAGE_CLIENT = "#CLIENT_ENTERED";
    public static final String MESSAGE_EMPLOYEE = "#EMPLOYEE_DONE";
    public static final String MESSAGE_CLIENT_EXITED = "#CLIENT_EXITED";
    public static final String MESSAGE_QUEUE_EMPTY = "#EMPTY";
    public static final String MESSAGE_CLIENT_ID = "#ID ";

    private Socket socket;

    private ClientConnectionHandler handler;

    public void connect(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
            this.onConnected();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onConnected() {
        this.handler = new ClientConnectionHandler(socket, this);
        this.handler.start();
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
    public void onEmployeeMessageRecived(String message) {
        if (message.equals(MESSAGE_QUEUE_EMPTY))
            sendMessage(MESSAGE_EMPLOYEE);
        else if (message.contains(MESSAGE_CLIENT_ID))
            onClientDone(message);
    }

    @Override
    public void onEmployeeDisposed(ClientConnectionHandler employee) {

    }

    private void onClientDone(String message) {
        System.out.println(getId(message));
    }

    private String getId(String message) {
        return message.replaceAll(MESSAGE_CLIENT_ID, "").replaceAll(" ", "");
    }

}
