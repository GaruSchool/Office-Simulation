package Simulation;

import Actors.GenericActor;
import Networking.ServerOffice;
import Queue.QueueManager;
import Queue.QueueManagerHelper;


/**
 * Created by cccp on 04/12/2014.
 */
public abstract class BaseOffice implements ActorListener {
    public static final int DEFAULT_PORT = 9999;
    private ServerOffice serverOffice;

    public BaseOffice() {
        this.serverOffice = new ServerOffice(this);
    }

    public void open() {
        onOfficeOpened();
        this.serverOffice.start(DEFAULT_PORT);
    }

    public void open(int port) {
        onOfficeOpened();
        this.serverOffice.start(port);
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
            onEmployeeDone();
            onClientLeave(removedActor);
        } else
            notifyEmptyQueue();
    }

    private void handleClientEntered(GenericActor actor) {
        if (!QueueManagerHelper.getInstance().isFull()) {
            QueueManagerHelper.getInstance().addClient(actor);
            onClientEntered();
        } else {
            notifyActorDisconnected(actor);
            onQueueFull();
        }
    }

    private void notifyActorDisconnected(GenericActor actor) {
        serverOffice.onClientRemoved(actor);
    }

    public QueueManager getQueue() {
        return QueueManagerHelper.getInstance();
    }

    private void notifyEmptyQueue() {
        serverOffice.onQueueEmpty();
    }

    public abstract void onOfficeOpened();

    public abstract void onOfficeClosed();

    public abstract void onClientEntered();

    public abstract void onClientLeave(GenericActor client);

    public abstract void onQueueFull();

    public abstract void onEmployeeDone();


}
