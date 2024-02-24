package cs3500.pa05.controller;

import cs3500.pa05.model.record.UserTask;
import javafx.event.Event;

/**
 * CompleteTaskEventHandler
 */
public class CompleteTaskEventHandler extends AbstractHandler {
  private UserTask task;

  /**
   * constructor
   *
   * @param task UserTask
   */
  public CompleteTaskEventHandler(UserTask task) {
    this.task = task;
  }

  /**
   * handle
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    UserTask newTask = new UserTask(task.getDay(), task.getName(), task.getDescription());
    newTask.setCompleted(!task.getCompleted());
    int taskIndex = staticModel.getUserTasks().indexOf(task);
    System.out.println(taskIndex);
    staticModel.getUserTasks().add(taskIndex, newTask);
    staticModel.getUserTasks().remove(task);
  }
}
