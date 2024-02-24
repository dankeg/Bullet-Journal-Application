package cs3500.pa05.controller;

import cs3500.pa05.model.record.UserTask;
import javafx.event.Event;

/**
 * DeleteTaskEventHandler class
 */
public class DeleteTaskEventHandler extends AbstractHandler {
  private UserTask task;

  /**
   * constructor
   *
   * @param task UserTask
   */
  public DeleteTaskEventHandler(UserTask task) {
    this.task = task;
  }

  /**
   * handle method
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    staticModel.getUserTasks().remove(task);
  }
}
