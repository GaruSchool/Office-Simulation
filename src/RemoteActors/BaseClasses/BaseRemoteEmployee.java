package RemoteActors.BaseClasses;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by t.garuglieri on 05/12/14.
 */
public abstract class BaseRemoteEmployee implements RemoteEmployeeListener {


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
        onEmployeeEntered();
        this.handler = new ClientConnectionHandler(socket, this);
        this.handler.start();
        sendMessage(MESSAGE_EMPLOYEE);
    }


    public void sendMessage(String message) {
        try {
            new PrintWriter(socket.getOutputStream(), true).println(message);
        } catch (IOException e) {
            closeAndDispose();
        }
    }

    private void closeAndDispose() {
        try {
            if (!socket.isClosed())
                socket.close();
            handler.dispose();
        } catch (IOException e) {
        }
    }

    @Override
    public void onEmployeeMessageRecived(ClientConnectionHandler handler, String message) {
        if (message.equals(MESSAGE_QUEUE_EMPTY))
            sendMessage(MESSAGE_EMPLOYEE);
        else if (message.contains(MESSAGE_CLIENT_ID)) {
            onClientDone(getId(message));
            sendMessage(MESSAGE_EMPLOYEE); //TODO add some delay
        }
    }

    @Override
    public void onEmployeeDisposed(ClientConnectionHandler employee) {
        onEmployeeExited();
    }

    private String getId(String message) {
        return message.replaceAll(MESSAGE_CLIENT_ID, "").replaceAll(" ", "");
    }

    public abstract void onClientDone(String clientID);

    public abstract void onEmployeeEntered();

    public abstract void onEmployeeExited();

}
