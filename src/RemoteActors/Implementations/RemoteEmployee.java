package RemoteActors.Implementations;

import RemoteActors.BaseClasses.BaseRemoteEmployee;

/**
 * Created by cccp on 09/12/2014.
 */
public class RemoteEmployee extends BaseRemoteEmployee {
    @Override
    public void onClientDone(String clientID) {
        System.out.println("Servito cliente n°: " + clientID);
    }

    @Override
    public void onEmployeeEntered() {
        System.out.println("Impiegato entrato a lavoro!");
    }

    @Override
    public void onEmployeeExited() {
        System.out.println("Impiegato uscito da lavoro!");
    }

    @Override
    public void onOfficeClosed() {
        System.out.println("L'ufficio è ancora chiuso");
    }


}
