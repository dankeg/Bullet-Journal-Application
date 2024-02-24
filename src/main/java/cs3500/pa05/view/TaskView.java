package cs3500.pa05.view;

import cs3500.pa05.controller.CompleteTaskEventHandler;
import cs3500.pa05.controller.DeleteTaskEventHandler;
import cs3500.pa05.controller.UrlEventHandler;
import cs3500.pa05.model.record.UserTask;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * TaskView class
 */
public class TaskView extends VBox {
  private UserTask task;

  /**
   * constructor
   *
   * @param task UserTask
   */
  public TaskView(UserTask task) {
    this.task = task;
    this.setStyle("-fx-padding: 5; -fx-background-color: floralwhite; -fx-background-radius: 10;");
    this.setPrefWidth(125);
    this.setPrefHeight(50);

    //create delete button
    Button deleteButton = new Button("x");
    deleteButton.setStyle("-fx-text-fill: floralwhite; "
        + "-fx-background-color: lightcoral; "
        + "-fx-background-radius: 30; -fx-font-size:8");
    double r = 15;
    deleteButton.setShape(new Circle(r));
    deleteButton.setMinSize(r, r);
    deleteButton.setMaxSize(r, r);
    deleteButton.setOnAction(new DeleteTaskEventHandler(task));

    //create checked button
    CheckBox completed = new CheckBox();
    completed.setOnAction(new CompleteTaskEventHandler(task));
    completed.setSelected(task.getCompleted());

    //create button row
    HBox buttonRow = new HBox();
    buttonRow.setAlignment(Pos.BASELINE_RIGHT);
    buttonRow.getChildren().addAll(completed, deleteButton);
    buttonRow.setSpacing(5);

    //task name
    Label taskName = new Label(task.getName());
    taskName.setFont(Font.font(12));

    //insert line break
    Line line = new Line(0, 0, 125, 0);
    line.setStroke(new Color(0.2, 0.2, 0.2, 0.1));

    //task description, handles url creation
    TextFlow taskDescription = this.handleDescription();
    taskDescription.setPrefWidth(125);

    taskName.setFont(Font.font(12));

    this.getChildren().addAll(buttonRow, taskName, line, taskDescription);
  }

  /**
   * To create a description with a clickable link, in the event that a description
   * contains a URL
   */
  private TextFlow handleDescription() {
    TextFlow taskDescriptionFlow = new TextFlow();
    String description = task.getDescription();

    String regex = "\\b((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\b";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher;
    Scanner scanner = new Scanner(description);

    while (scanner.hasNext()) {
      String currentString = scanner.next();
      matcher = pattern.matcher(currentString);
      if (matcher.find()) {
        Hyperlink link = new Hyperlink(currentString);
        link.setOnAction(new UrlEventHandler(currentString));
        link.setPrefWidth(125);
        taskDescriptionFlow.getChildren().add(link);
      } else {
        Text text = new Text(currentString + " ");
        taskDescriptionFlow.getChildren().add(text);
      }
    }

    return taskDescriptionFlow;
  }
}
