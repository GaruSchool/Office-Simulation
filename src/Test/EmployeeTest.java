package Test;

import RemoteActors.RemoteEmployee;
import Simulation.Office;

/**
 * Created by t.garuglieri on 05/12/14.
 */
public class EmployeeTest {
    public static void main(String[] args) {
        new RemoteEmployee().connect("127.0.0.1", Office.DEFAULT_PORT);

    }
}
