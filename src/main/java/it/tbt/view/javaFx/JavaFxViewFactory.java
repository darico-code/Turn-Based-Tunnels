package it.tbt.view.javaFx;

import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.viewcontrollermanager.api.ExploreController;
import it.tbt.controller.viewcontrollermanager.api.ViewController;

import it.tbt.view.api.GameView;
import it.tbt.view.api.GameViewFactory;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * JavaFx Factory for GameViews
 */

public class JavaFxViewFactory implements GameViewFactory {

    private Stage stage;

    /**
     * @param stage used as reference to where the GameViews will reside.
     */
    public JavaFxViewFactory(final Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    /**
     * @return
     */
    @Override
    public GameView createMenu(final ViewController vc) {
        return new SimpleJavaFxViewMenu(vc);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameView createRoom(ExploreController exploreController, ExploreState exploreState) {
        Group group = new Group();
        Scene scene = new Scene(group, 300, 300);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
        return new JavaFxExploreView(exploreController, exploreState, this.stage, scene, group);
    }
}
