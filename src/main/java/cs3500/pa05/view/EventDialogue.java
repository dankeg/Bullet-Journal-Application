package cs3500.pa05.view;


import cs3500.pa05.model.record.DayEnum;
import cs3500.pa05.model.record.UserEvent;
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
 * EventDialogue class
 */
public class EventDialogue extends Dialog<UserEvent> {
  /**
   * constructor
   */
  public EventDialogue() {
    this.setTitle("Create a new UserEvent:");
    this.setHeaderText("Please provide a new UserEvent to add to your week");
    this.setResizable(false);

    Label name = new Label("Name: ");
    Label description = new Label("Description: ");
    Label dayOfWeek = new Label("Day of the Week: ");
    Label startTime = new Label("Start Time: ");
    Label duration = new Label("Duration: ");
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
    TextField timeField = new TextField();
    TextField durationField = new TextField();

    GridPane promptGrid = new GridPane();
    promptGrid.setVgap(10);
    promptGrid.add(name, 1, 1);
    promptGrid.add(nameField, 2, 1);
    promptGrid.add(description, 1, 2);
    promptGrid.add(descriptionField, 2, 2);
    promptGrid.add(dayOfWeek, 1, 3);
    promptGrid.add(dayOfWeekOptions, 2, 3);
    promptGrid.add(startTime, 1, 4);
    promptGrid.add(timeField, 2, 4);
    promptGrid.add(duration, 1, 5);
    promptGrid.add(durationField, 2, 5);
    this.getDialogPane().setContent(promptGrid);

    ButtonType buttonSubmit = new ButtonType("Submit New UserEvent", ButtonBar.ButtonData.OK_DONE);
    this.getDialogPane().getButtonTypes().add(buttonSubmit);

    this.setResultConverter(new Callback<ButtonType, UserEvent>() {
      /**
       * userEvent method
       *
       * @param b The single argument upon which the returned value should be
       *      determined.
       * @return UserEvent
       */
      @Override
      public UserEvent call(ButtonType b) {

        if (b == buttonSubmit && !dayOfWeekOptions.getValue().equals(null)
            && !nameField.getText().equals("")
            && !timeField.getText().equals("")
            && !durationField.getText().equals("")) {

          return new UserEvent(nameField.getText(),
              DayEnum.valueOf(dayOfWeekOptions.getValue().toString()),
              timeField.getText(),
              durationField.getText(),
              descriptionField.getText());
        }

        return null;
      }
    });
  }
}