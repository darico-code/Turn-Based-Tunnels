package it.tbt.engine.impl;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFx Launcher of the Game.
 */

public class JavaFxLauncher extends Application {

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final var x = new AnimationTimerGameLoopManager(new FixedTimeGameLoop(GameFactory.createJavaFxGame(primaryStage)));
        x.startLoop();
    }
}
