package Test;

import RemoteActors.Implementations.RemoteClient;
import Simulation.BaseOffice;

/**
 * Created by t.garuglieri on 05/12/14.
 */
public class ClientTest {
    public static void main(String[] args) {
        for (int i = 0; i < 15; i++)
            new Thread() {
                @Override
                public void run() {
                    new RemoteClient().connect("127.0.0.1", BaseOffice.DEFAULT_PORT);
                }
            }.start();

    }
}
