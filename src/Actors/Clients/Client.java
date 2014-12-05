package Actors.Clients;

import Actors.GenericActor;
import Networking.ConnectionHandler;

/**
 * Created by cccp on 03/12/2014.
 */
public class Client extends GenericActor {

    public Client(ConnectionHandler handler) {
        super(handler, type);
    }
}
