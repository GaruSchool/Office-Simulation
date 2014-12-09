package Test;

import RemoteActors.Implementations.RemoteEmployee;
import Simulation.BaseOffice;


/**
 * Created by t.garuglieri on 05/12/14.
 */
public class EmployeeTest {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                new RemoteEmployee().connect("127.0.0.1", BaseOffice.DEFAULT_PORT);
            }
        }.start();

    }
}
