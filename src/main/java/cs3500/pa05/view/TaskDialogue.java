package cs3500.pa05.view;

import cs3500.pa05.model.record.DayEnum;
import cs3500.pa05.model.record.UserTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 * TaskDialogue class
 */
public class TaskDialogue extends Dialog<UserTask> {
  /**
   * constructor
   */
  public TaskDialogue() {
    this.setTitle("Create a new task:");
    this.setHeaderText("Please provide a new UserTask to add to your week");
    this.setResizable(false);

    Label name = new Label("Name: ");
    Label description = new Label("Description: ");
    Label dayOfWeek = new Label("Day of the Week: ");
    ObservableList<String> options =
        FXCollections.observableArrayList(
            "MONDAY",
            "TUESDAY",
            "WEDNESDAY",
            "THURSDAY",
            "FRIDAY",
            "SATURDAY",
            "SUNDAY"
        );

    ComboBox dayOfWeekOptions = new ComboBox(options);

    TextField nameField = new TextField();
    TextField descriptionField = new TextField();

    GridPane promptGrid = new GridPane();
    promptGrid.add(name, 1, 1);
    promptGrid.add(nameField, 2, 1);
    promptGrid.add(description, 1, 2);
    promptGrid.add(descriptionField, 2, 2);
    promptGrid.add(dayOfWeek, 1, 3);
    promptGrid.add(dayOfWeekOptions, 2, 3);
    this.getDialogPane().setContent(promptGrid);

    ButtonType buttonSubmit = new ButtonType("Submit New UserTask", ButtonBar.ButtonData.OK_DONE);
    this.getDialogPane().getButtonTypes().add(buttonSubmit);

    this.setResultConverter(new Callback<ButtonType, UserTask>() {
      @Override
      public UserTask call(ButtonType b) {

        if (b == buttonSubmit && !(dayOfWeekOptions.getValue() == null)
            && !nameField.getText().equals("")) {

          return new UserTask(DayEnum.valueOf(dayOfWeekOptions.getValue().toString()),
              nameField.getText(),
              descriptionField.getText());
        }

        return null;
      }
    });
  }
}
