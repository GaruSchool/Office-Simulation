package Simulation;

import Actors.GenericActor;
import Networking.ServerOffice;
import Queue.QueueManagerHelper;


/**
 * Created by cccp on 04/12/2014.
 */
public class Office implements ActorListener {
    public static final int DEFAULT_PORT = 9999;
    private ServerOffice serverOffice;

    public Office() {
        this.serverOffice = new ServerOffice(this);
    }

    public void open() {
        this.serverOffice.start(DEFAULT_PORT);
    }

    @Override
    public void onActorAction(GenericActor actor) {
        if (actor.getType() == GenericActor.ACTOR_CLIENT) {
            handleClientEntered(actor);
        } else if (actor.getType() == GenericActor.ACTOR_EMPLOYEE) {
            HandleEmployeeDone(actor);
        }
    }

    private void HandleEmployeeDone(GenericActor actor) {
        if (!QueueManagerHelper.getInstance().isEmpty()) {
            GenericActor removedActor = QueueManagerHelper.getInstance().getCurrentClient();
            QueueManagerHelper.getInstance().removeClient();
            serverOffice.onEmployeeDone(actor, removedActor);
        } else
            notifyEmptyQueue();
    }

    private void handleClientEntered(GenericActor actor) {
        if (!QueueManagerHelper.getInstance().isFull()) {
            QueueManagerHelper.getInstance().addClient(actor);
        } else {
            notifyActorDisconnected(actor);
        }
    }

    private void notifyActorDisconnected(GenericActor actor) {
        serverOffice.onClientRemoved(actor);
    }

    private void notifyEmptyQueue() {
        serverOffice.onQueueEmpty();
    }


}
