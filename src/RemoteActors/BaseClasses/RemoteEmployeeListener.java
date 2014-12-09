package RemoteActors.BaseClasses;

/**
 * Created by t.garuglieri on 05/12/14.
 */
public interface RemoteEmployeeListener {
    public abstract void onEmployeeMessageRecived(ClientConnectionHandler handler, String message);

    public abstract void onEmployeeDisposed(ClientConnectionHandler employee);
}
