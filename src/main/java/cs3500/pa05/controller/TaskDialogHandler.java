package cs3500.pa05.controller;

import cs3500.pa05.model.record.UserTask;
import cs3500.pa05.view.TaskDialogue;
import java.util.Optional;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

/**
 * TaskDialogHandler class
 */
public class TaskDialogHandler extends AbstractHandler {
  private Button button;

  /**
   * constructor
   *
   * @param button Button
   */
  public TaskDialogHandler(Button button) {
    this.button = button;
  }

  /**
   * no arg constructor
   */
  public TaskDialogHandler() {}


  /**
   * handle method
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    TaskDialogue taskDialogue = new TaskDialogue();
    Optional<UserTask> result = taskDialogue.showAndWait();;
    if (result.isPresent()) {
      UserTask providedUserTask = result.get();
      staticModel.addTask(providedUserTask);
      taskDialogue.close();
    }

    if (staticModel.getUserTasks().size() >= staticModel.getTaskMax() && !(button == null)) {
      Tooltip taskTooltip = new Tooltip(
          "Whoops, be careful you are at your maximum number of events");
      taskTooltip.setShowDelay(javafx.util.Duration.millis(10));
      this.button.setTooltip(taskTooltip);
    }
  }
}
