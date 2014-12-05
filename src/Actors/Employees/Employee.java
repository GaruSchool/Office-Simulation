package Actors.Employees;

import Actors.GenericActor;
import Networking.ConnectionHandler;

/**
 * Created by cccp on 03/12/2014.
 */
public class Employee extends GenericActor {

    public Employee(ConnectionHandler handler, int type) {
        super(handler, type);
    }
}
