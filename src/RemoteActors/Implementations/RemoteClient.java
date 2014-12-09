package RemoteActors.Implementations;

import RemoteActors.BaseClasses.BaseRemoteClient;

/**
 * Created by cccp on 09/12/2014.
 */
public class RemoteClient extends BaseRemoteClient {
    @Override
    public void onClientEntered() {
        System.out.println("Premere un tasto per mettersi in coda");
    }

    @Override
    public void onClientQueued() {
        System.out.println("Attesa di un impiegato libero");
    }

    @Override
    public void onClientLeft() {
        System.out.println("Esco dal negozio");
    }
}
