package cs3500.pa05.view;

import cs3500.pa05.controller.DeleteEventEventHandler;
import cs3500.pa05.controller.UrlEventHandler;
import cs3500.pa05.model.record.UserEvent;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * EventView class
 */
public class EventView extends VBox {
  private UserEvent event;

  /**
   * EventView class
   *
   * @param event UserEvent
   */
  public EventView(UserEvent event) {
    this.event = event;
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
    deleteButton.setOnAction(new DeleteEventEventHandler(event));

    //create button row
    HBox buttonRow = new HBox();
    buttonRow.setAlignment(Pos.BASELINE_RIGHT);
    buttonRow.getChildren().add(deleteButton);

    //task name
    Label taskName = new Label(event.getName());
    taskName.setFont(Font.font("Arial", FontWeight.BOLD, 15));

    //insert line break
    Line line = new Line(0, 0, 125, 0);
    line.setStroke(new Color(0.2, 0.2, 0.2, 0.1));

    //task description
    TextFlow eventDescription = this.handleDescription();
    taskName.setFont(Font.font(12));
    eventDescription.setPrefWidth(125);

    //task description
    Label time = new Label(event.getStartTime() + " - " + event.getDuration());
    taskName.setFont(Font.font(12));

    //a name, description, day of week, start time, and duration

    this.getChildren().addAll(buttonRow, taskName, line, eventDescription, time);
  }

  /**
   * To create a description with a clickable link, in the event that a description
   * contains a URL
   */
  private TextFlow handleDescription() {
    TextFlow taskDescriptionFlow = new TextFlow();
    String description = event.getDescription();

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
