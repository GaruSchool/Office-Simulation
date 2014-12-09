package RemoteActors;

/**
 * Created by cccp on 08/12/2014.
 */
public abstract interface RemoteClientInterface {
    public abstract void onClientEntered();

    public abstract void onClientLeave();

    public abstract void onClientQueued();

}
