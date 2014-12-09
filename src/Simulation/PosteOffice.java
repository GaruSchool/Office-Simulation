package Simulation;

import Actors.GenericActor;

/**
 * Created by t.garuglieri on 09/12/14.
 */
public class PosteOffice extends BaseOffice {
    @Override
    public void onOfficeOpened() {
        System.out.println("Ufficio Aperto!");
    }

    @Override
    public void onOfficeClosed() {
        System.out.println("Ufficio Chiuso!");
    }

    @Override
    public void onClientEntered() {
        System.out.println("Cliente Aggiunto!");
        System.out.println("Stato Coda: " + getQueue().getSize() + "/" + getQueue().getMaxSize());
    }

    @Override
    public void onClientLeave(GenericActor client) {
        System.out.println("Cliente Uscito!");
        System.out.println("Stato Coda: " + getQueue().getSize() + "/" + getQueue().getMaxSize());
    }

    @Override
    public void onQueueFull() {
        System.out.println("Coda Piena!");
        System.out.println("Stato Coda: " + getQueue().getSize() + "/" + getQueue().getMaxSize());
    }

    @Override
    public void onEmployeeDone() {
        System.out.println("Impiegato ha finito di lavorare!");
    }
}
