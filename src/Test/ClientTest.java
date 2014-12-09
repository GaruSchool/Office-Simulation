package Test;

import RemoteActors.RemoteClient;
import Simulation.Office;

/**
 * Created by cccp on 08/12/2014.
 */
public class ClientTest {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                new RemoteClient().connect("127.0.0.1", Office.DEFAULT_PORT);
            }
        }.start();
    }
}
