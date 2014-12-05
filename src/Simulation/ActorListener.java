package Simulation;

import Actors.GenericActor;

/**
 * Created by cccp on 04/12/2014.
 */
public interface ActorListener {
    public abstract void onActorAction(GenericActor actor);
}
