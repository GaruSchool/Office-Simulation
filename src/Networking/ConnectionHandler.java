package Networking;

import Actors.GenericActor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by cccp on 04/12/2014.
 */
public class ConnectionHandler extends Thread {
    private Socket socket;
    private ActorConnectionListener listener;

    public ConnectionHandler(Socket socket, ActorConnectionListener listener) {
        this.socket = socket;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            while (!this.isInterrupted() && this.socket.isConnected()) {
                String message = reader.readLine();
                if (message.equals(ActorConnectionListener.MESSAGE_CLIENT))
                    listener.onActorMessageRecived(this, GenericActor.ACTOR_CLIENT);
                else if (message.equals(ActorConnectionListener.MESSAGE_EMPLOYEE))
                    listener.onActorMessageRecived(this, GenericActor.ACTOR_EMPLOYEE);
                else
                    listener.onActorMessageRecived(this, -1);

            }
        } catch (IOException e) {
            this.dispose();
        }

    }

    public void dispose() {
        try {
            this.socket.close();
            this.interrupt();
        } catch (IOException e) {
            // NULLA
        }
    }

    public void sendMessage(String message) {
        try {
            new PrintWriter(socket.getOutputStream(), true).println(message);
        } catch (IOException e) {
            dispose();
        }
    }


}
