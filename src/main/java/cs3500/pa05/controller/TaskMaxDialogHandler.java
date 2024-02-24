package cs3500.pa05.controller;

import cs3500.pa05.view.SetTaskMaxesDialog;
import java.util.Optional;
import javafx.event.Event;

/**
 * TaskMaxDialogHandler class
 */
public class TaskMaxDialogHandler extends AbstractHandler {
  /**
   * handle method
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    SetTaskMaxesDialog taskMaxDialogue = new SetTaskMaxesDialog();
    Optional<Integer> result = taskMaxDialogue.showAndWait();;
    if (result.isPresent()) {
      Integer providedInt = result.get();
      staticModel.addTaskMax(providedInt);
      taskMaxDialogue.close();
    }
  }
}
