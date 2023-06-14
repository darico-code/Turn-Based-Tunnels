package it.tbt.controller.viewcontrollermanager.api;

import it.tbt.control.input.api.InputListener;
import it.tbt.model.command.api.Command;
import java.util.List;

/**
 * Interface for the input hub for the current view/state.
 */

public interface ViewController extends InputListener{
    /**
     * Returns the list of Commands intercepted
     * @return
     */
    public List<Command> getCommands();

    /**
     * Cleans the list of Commands this ViewController currently has.
     */
    public void clean();
}
