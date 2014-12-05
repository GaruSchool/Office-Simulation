package Test;

import Simulation.Office;

/**
 * Created by cccp on 04/12/2014.
 */
public class OfficeSimulationTest {

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                new Office().open();
            }
        }.start();
    }

}
