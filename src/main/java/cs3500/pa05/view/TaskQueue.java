package cs3500.pa05.view;

import cs3500.pa05.controller.ModelHolder;
import cs3500.pa05.model.record.UserTask;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * TaskQueue class
 */
public class TaskQueue extends ModelHolder {

  /**
   * no arg constructor
   */
  public TaskQueue() {}

  /**
   * getTaskQueue method --> returns TaskQueue as a VBox
   *
   * @return VBox
   */
  public VBox getTaskQueue() {
    VBox taskQueueContents = new VBox();
    taskQueueContents.setPrefHeight(460);
    taskQueueContents.setPrefWidth(170);
    taskQueueContents.getChildren().add(new Label("UserTask Queue"));
    taskQueueContents.setSpacing(10);
    taskQueueContents.setStyle("-fx-padding: 10; "
        + "-fx-background-color: #fffce0; "
        + "-fx-background-image: url('https://media.istockphoto.com/id/519847559/"
        + "vector/lined-paper-from-a-notebook.jpg?s=612x612&w=0&k=20&c=PrlbBZFrU6Vfrre8"
        + "-sdmucKI3AGBZ8AwxVWIepxFD34='); "
        + "-fx-background-radius: 5; "
        + "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.3), 10, 0, 0, 0); "
        + "-fx-background-size: cover; "
        + "-fx-background-position: left;");

    VBox tasks = new VBox();
    tasks.setSpacing(5);

    ListChangeListener<UserTask> taskListener = change -> {
      tasks.getChildren().clear();
      for (UserTask addedItem : staticModel.getUserTasks()) {

        GridPane taskCompleted = new GridPane();
        taskCompleted.setHgap(10);
        taskCompleted.setPrefWidth(170);
        taskCompleted.setStyle("-fx-padding: 5; -fx-background-color: floralwhite; "
            + "-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 5px;");
        Label name = new Label(addedItem.getName());
        Circle completedCircle = new Circle(7);
        completedCircle.setFill(Color.WHITE);
        if (addedItem.getCompleted()) {
          completedCircle.setFill(Color.GREEN);
        }
        taskCompleted.add(name, 1, 1);
        taskCompleted.add(completedCircle, 2, 1);
        tasks.getChildren().add(taskCompleted);
      }
    };

    //lister to populate queue with list of tasks
    staticModel.getUserTasks().addListener(taskListener);
    Platform.runLater(() -> taskListener.onChanged(null)); //tricks into populating task list

    taskQueueContents.getChildren().add(tasks);
    return taskQueueContents;
  }
}
