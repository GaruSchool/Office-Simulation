package Networking;

import Actors.GenericActor;
import Simulation.ActorListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by cccp on 04/12/2014.
 */
public class ServerOffice implements ActorConnectionListener {


    public static final String MESSAGE_CLIENT_ID = "#ID ";
    private final String MESSAGE_QUEUE_EMPTY = "#EMPTY";
    private ServerSocket serverSocket;
    private ActorListener actorListener;
    private ArrayList<ConnectionHandler> handlers;

    public ServerOffice(ActorListener actorListener) {
        this.actorListener = actorListener;
        this.handlers = new ArrayList<ConnectionHandler>();
    }

    public void start(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            this.startConnectionHandling();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startConnectionHandling() {
        while (!serverSocket.isClosed()) {
            try {
                Socket incomingConnection = serverSocket.accept();
                ConnectionHandler handler = new ConnectionHandler(incomingConnection, this);
                handler.start();
                this.handlers.add(handler);
            } catch (IOException e) {
                e.printStackTrace(); //TODO Handle Error
            }
        }
    }

    @Override
    public void onActorMessageRecived(ConnectionHandler handler, int actorType) {
        if (actorType != -1)
            actorListener.onActorAction(new GenericActor(handler, actorType));
    }

    public void onClientRemoved(GenericActor actor) {
        actor.getHandler().sendMessage(ActorConnectionListener.MESSAGE_CLIENT_EXITED);
        actor.getHandler().dispose();
    }

    public void onQueueEmpty() {
        broadcastMessage(MESSAGE_QUEUE_EMPTY);
    }

    public void onEmployeeDone(GenericActor employee, GenericActor client) {
        employee.getHandler().sendMessage(MESSAGE_CLIENT_ID + String.valueOf(client.getId()));
        onClientRemoved(client);
    }

    private void broadcastMessage(String message) {
        for (ConnectionHandler handler : handlers)
            handler.sendMessage(message);
    }


}
