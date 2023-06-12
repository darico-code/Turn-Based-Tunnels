package it.tbt.engine.impl;

import it.tbt.Commons.resourceloader.world.impl.FileWorldCreationStrategy3;
import it.tbt.controller.ModelManager.GameStateManager;
import it.tbt.controller.ModelManager.IGameStateManager;
import it.tbt.controller.ViewControllerManager.api.ViewControllerManager;
import it.tbt.controller.ViewControllerManager.impl.GameViewManagerImpl2;
import it.tbt.model.Entities.SpatialEntity;
import it.tbt.model.Party.IParty;
import it.tbt.model.Party.Party;
import it.tbt.model.GameState;
import it.tbt.Commons.resourceloader.world.impl.FileWorldCreationStrategy;
import it.tbt.model.World.api.World;
import it.tbt.model.World.impl.RoomImpl;
import it.tbt.view.api.GameViewFactory;
import it.tbt.engine.api.Game;


public class GameImpl implements Game {

    private ViewControllerManager viewControllerManager;
    private IGameStateManager gameStateManager;

    public GameImpl(final GameViewFactory gvf) {
        viewControllerManager = new GameViewManagerImpl2(gvf);
        IParty t = new Party("Party", 0,0);
        World w = new FileWorldCreationStrategy3().createWorld();
        t.setCurrentRoom(w.getListRoom().stream().findAny().get());
        gameStateManager = new GameStateManager(t, w);
    } 

    /**
     *
     */
    @Override
    public void initialize() {
       this.viewControllerManager.renderView(this.gameStateManager.getState(), this.gameStateManager.getStateModel(), true);
    }

    /**
     *
     */
    @Override
    public void loadResources() {
        //TO LOAD RESOURCES
    }

    /**
     * @param deltaTime
     */
    @Override
    public void update(long deltaTime) {
        this.gameStateManager.updateState(deltaTime);
    }

    /**
     *
     */
    @Override
    public void render() {
        this.viewControllerManager.renderView(this.gameStateManager.getState(), this.gameStateManager.getStateModel(), this.gameStateManager.hasStateChanged());
    }

    /**
     * @param time
     */
    @Override
    public void render(long time) {
        //RENDER WITH TIME LAG TO REPRESENT
    }

    /**
     * @return
     */
    @Override
    public Boolean handleInput() {
        Boolean r = this.viewControllerManager.getCommands().isEmpty();
        if(r == false) {
            this.viewControllerManager.getCommands().get().stream().forEach(l -> l.execute());
            this.viewControllerManager.cleanCommands();
        }
        return !r;
    }

    /**
     * @return
     */
    @Override
    public Boolean isOver() {
        return this.gameStateManager.isOver();
    }

    /**
     *
     */
    @Override
    public void cleanup() {
        //CLEAN UP ON THE CLOSURE
    }
}
