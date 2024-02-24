package cs3500.pa05.view;

import cs3500.pa05.controller.ChangeDayDialogHandler;
import cs3500.pa05.controller.EventDialogHandler;
import cs3500.pa05.controller.EventMaxHandler;
import cs3500.pa05.controller.SaveEventHandler;
import cs3500.pa05.controller.TaskDialogHandler;
import cs3500.pa05.controller.TaskMaxDialogHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * MenuButtons class
 */
public class MenuButtons extends HBox {

  /**
   * constructor
   */
  public MenuButtons() {
    this.setSpacing(10);

    String styleString = "-fx-background-color: #ffffff;\n"
        + "    -fx-text-fill: #000000;\n"
        + "    -fx-font-size: 14px;\n"
        + "    -fx-font-family: \"Arial\";\n"
        + "    -fx-padding: 10px 20px;\n"
        + "    -fx-border-radius: 5px;";


    // set style for the buttons
    Button save = new Button("Save");
    save.setStyle(styleString);


    Button addTask = new Button("Add UserTask");
    addTask.setStyle(styleString);

    Button addEvent = new Button("Add UserEvent");
    addEvent.setStyle(styleString);

    Button setTaskMax = new Button("Set UserTask Max");
    setTaskMax.setStyle(styleString);

    Button setEventMax = new Button("Set UserEvent Max");
    setEventMax.setStyle(styleString);

    Button changeWeekStart = new Button("Change Start Day");
    changeWeekStart.setStyle(styleString);

    addTask.setOnAction(new TaskDialogHandler(addTask));
    addEvent.setOnAction(new EventDialogHandler(addEvent));
    setEventMax.setOnAction(new EventMaxHandler());
    setTaskMax.setOnAction(new TaskMaxDialogHandler());
    save.setOnAction(new SaveEventHandler());
    changeWeekStart.setOnAction(new ChangeDayDialogHandler());

    this.getChildren().add(save);
    this.getChildren().add(addTask);
    this.getChildren().add(addEvent);
    this.getChildren().add(setTaskMax);
    this.getChildren().add(setEventMax);
    this.getChildren().add(changeWeekStart);
  }
}
