package Networking;

/**
 * Created by cccp on 04/12/2014.
 */
public interface ActorConnectionListener {

    public static final String MESSAGE_CLIENT = "#CLIENT_ENTERED";
    public static final String MESSAGE_EMPLOYEE = "#EMPLOYEE_DONE";
    public static final String MESSAGE_CLIENT_EXITED = "#CLIENT_EXITED";

    public void onActorMessageRecived(ConnectionHandler handler, int actorType);
}
