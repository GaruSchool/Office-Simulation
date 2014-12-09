package Queue;

import Actors.GenericActor;
import Buffers.BufferException;
import Buffers.GenericCircularBuffer;

/**
 * Created by cccp on 03/12/2014.
 */
public class QueueManager {

    private Object queueLock = new Object();
    private GenericCircularBuffer<GenericActor> clients;

    public QueueManager(int queueSize) {
        this.clients = new GenericCircularBuffer<GenericActor>(queueSize);
    }

    public void addClient(GenericActor newClient) {
        synchronized (queueLock) {
            try {
                newClient.setId(this.clients.getCurrentIndex() + 1);
                this.clients.add(newClient);
            } catch (BufferException e) {
                System.out.println("Impossibile aggiungere " + newClient.toString() + " Queue piena");
            }
        }
    }

    public void removeClient() {
        synchronized (queueLock) {
            try {
                this.clients.remove();
            } catch (BufferException e) {
                System.out.println("Impossibile rimuovere l'oggetto Queue vuota");
            }
        }
    }

    public GenericActor getCurrentClient() {
        try {
            return this.clients.get();
        } catch (BufferException e) {
            return null;
        }
    }

    public boolean isFull() {
        synchronized (queueLock) {
            return this.clients.isFull();
        }
    }

    public boolean isEmpty() {
        synchronized (queueLock) {
            return this.clients.isEmpty();
        }
    }

    public int getSize() {
        return clients.size();
    }

    public int getMaxSize() {
        return clients.getMaxSize();
    }


}
