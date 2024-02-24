package cs3500.pa05.view;

import cs3500.pa05.controller.ModelHolder;
import cs3500.pa05.model.record.DayEnum;
import cs3500.pa05.model.record.UserEvent;
import cs3500.pa05.model.record.UserTask;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * DayView class
 */
public class DayView extends ModelHolder {
  private String title;
  private DayEnum day;

  /**
   * constructor
   *
   * @param day DayEnum
   */
  public DayView(DayEnum day) {
    this.title = day.toString().charAt(0) + day.toString().substring(1).toLowerCase();
    this.day = day;
  }

  /**
   * getDayView method returns the day as a VBOX
   *
   * @return VBox
   */
  public VBox getDayView() {
    VBox wholeDay = new VBox(); //stores the whole box including the bottom progress bar
    wholeDay.setSpacing(3);

    VBox mainDayContents = new VBox(); //the whole column for the day
    mainDayContents.setStyle("-fx-padding: 10; "
        + "-fx-background-color: rgba(211,181,236,0.7); "
        + "-fx-background-radius: 5; "
        + "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.3), 10, 0, 0, 0); ");
    mainDayContents.setPrefHeight(430);
    mainDayContents.setPrefWidth(130);
    Label titleLabel = new Label(title);
    mainDayContents.getChildren().add(titleLabel);
    mainDayContents.setSpacing(5);

    VBox daysAgenda = new VBox(); //all the events and tasks for the day
    daysAgenda.setSpacing(5);

    HBox progressBarBox = new HBox(); //holds the progress box if it exists

    //lister to populate queue with list of tasks
    staticModel.getUserTasks().addListener((ListChangeListener<UserTask>) change -> {
      daysAgenda.getChildren().clear();
      for (UserEvent addedItem : staticModel.getUserEvents()) {
        if (addedItem.getDay() == day) {
          daysAgenda.getChildren().add(new EventView(addedItem));
        }
      }

      int completedTasks = 0;
      int totalTasks = 0;
      for (UserTask addedItem : staticModel.getUserTasks()) {
        if (addedItem.getDay() == day) {
          if (addedItem.getCompleted()) {
            completedTasks++;
          }
          totalTasks++;
          daysAgenda.getChildren().add(new TaskView(addedItem));
        }
      }
      //update the progress bar and add
      progressBarBox.getChildren().clear();
      progressBarBox.getChildren().add(progressComponent(completedTasks, totalTasks));
    });

    ListChangeListener<UserEvent> tasksListener = change -> {
      daysAgenda.getChildren().clear();
      for (UserEvent addedItem : staticModel.getUserEvents()) {
        if (addedItem.getDay() == day) {
          daysAgenda.getChildren().add(new EventView(addedItem));
        }
      }

      int completedTasks = 0;
      int totalTasks = 0;
      for (UserTask addedItem : staticModel.getUserTasks()) {
        if (addedItem.getDay() == day) {
          if (addedItem.getCompleted()) {
            completedTasks++;
          }
          totalTasks++;
          daysAgenda.getChildren().add(new TaskView(addedItem));
        }
      }

      // update the progress bar and add
      progressBarBox.getChildren().clear();
      progressBarBox.getChildren().add(progressComponent(completedTasks, totalTasks));
    };

    //lister to populate queue with list of Events
    staticModel.getUserEvents().addListener(tasksListener);
    Platform.runLater(() -> tasksListener.onChanged(null)); //trick list into populating

    mainDayContents.getChildren().add(daysAgenda);

    wholeDay.getChildren().addAll(mainDayContents, progressBarBox);
    return wholeDay;
  }

  /**
   * returns the progress component of a day
   *
   * @param completedTasks int
   * @param totalTasks int
   *
   * @return HBox
   */
  public HBox progressComponent(int completedTasks, int totalTasks) {
    if (totalTasks == 0) { //return empty hbox if there are no tasks
      return new HBox();
    }

    HBox progressRow = new HBox();
    progressRow.setSpacing(10);

    Label progressLabel = new Label(String.valueOf(totalTasks - completedTasks));
    progressLabel.setFont(Font.font("Arial", 14));

    double progress = (double) completedTasks / totalTasks;
    ProgressBar progressBar = new ProgressBar(progress);
    progressBar.setPrefSize(130, 14);
    progressBar.setMinSize(130, 14);
    progressBar.setMaxSize(130, 14);
    progressBar.setStyle("-fx-accent: rgba(225,89,225,0.41);");

    progressRow.getChildren().addAll(progressBar, progressLabel);
    return progressRow;
  }
}
