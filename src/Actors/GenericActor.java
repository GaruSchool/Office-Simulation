package Actors;

import Networking.ConnectionHandler;

/**
 * Created by cccp on 04/12/2014.
 */
public class GenericActor {
    public static final int ACTOR_CLIENT = 0;
    public static final int ACTOR_EMPLOYEE = 1;

    private ConnectionHandler handler;
    private int type;
    private int id = 0;

    public GenericActor(ConnectionHandler handler, int type) {
        this.handler = handler;
        this.type = type;
    }

    public ConnectionHandler getHandler() {
        return handler;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
