package Test;

import Simulation.BaseOffice;
import Simulation.PosteOffice;

/**
 * Created by cccp on 04/12/2014.
 */
public class OfficeSimulationTest {

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                new PosteOffice().open();
            }
        }.start();
    }

}
