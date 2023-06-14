package it.tbt.controller.modelmanager.transitionmanager.impl;

import it.tbt.controller.modelmanager.ExploreStateImpl;
import it.tbt.controller.modelmanager.ModelState;
import it.tbt.controller.modelmanager.transitionmanager.api.TransitionManager;
import it.tbt.engine.api.Game;
import it.tbt.model.GameState;
import it.tbt.model.party.IParty;
import it.tbt.model.statechange.StateTrigger;
import it.tbt.model.world.api.World;

import java.util.Optional;

/**
 * Default implementation of a TransitionManager
 */

public final class TransitionManagerImpl implements TransitionManager {

    private Optional<GameState> currentGameState;
    private World world;
    private IParty party;
    private Optional<ModelState> currentModelState;
    private Boolean stateChanged = false;

    public TransitionManagerImpl(final World world, final IParty party) {
        this.world = world;
        this.party = party;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        if(world == null || party == null) {
            throw new IllegalStateException("Null objects have been passed to the transition manager.");
        } else {
            this.startObserving();
        }
    }

    /**
     * Subscribes to model objects who can trigger a GameState change.
     */
    private void startObserving() {
        if(this.party instanceof StateTrigger) {
            ((StateTrigger)this.party).addStateObserver(this);
        }
        for(var x: this.world.getListRoom()) {
            for(var y: x.getEntities()) {
                if(y instanceof StateTrigger) {
                    ((StateTrigger)y).addStateObserver(this);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getState() {
        if(this.currentGameState.isEmpty()) {
            throw new IllegalStateException("Game Transition Manager not initialized properly. GameState not present.");
        }
        return this.currentGameState.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelState getCurrentModelState() {
        if(this.currentModelState.isEmpty()) {
            throw new IllegalStateException("Game Transition Manager not initialized properly. ModelState not present.");
        }
        return this.currentModelState.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean hasStateChanged() {
        var x = false;
        if(this.stateChanged==true) {
            x = true;
            this.stateChanged = false;
        }
        return x;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onExplore() {
        stateChanged = true;
        this.currentGameState = Optional.of(GameState.EXPLORE);
        this.currentModelState = Optional.of(new ExploreStateImpl(this.party.getCurrentRoom(), this.party));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFight() {
        //TO-DO
    }
}
