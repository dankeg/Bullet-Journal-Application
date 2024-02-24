package cs3500.pa05.view;

import cs3500.pa05.model.record.DayEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 * setDayDialog class
 */
public class SetDayDialog extends Dialog<DayEnum> {

  /**
   * setDayDialog constructor
   */
  public SetDayDialog() {
    this.setTitle("Change the Week Start Day");
    this.setHeaderText("Please provide the day of the week");
    this.setResizable(false);

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

    GridPane promptGrid = new GridPane();
    promptGrid.add(dayOfWeek, 1, 1);
    promptGrid.add(dayOfWeekOptions, 2, 1);
    this.getDialogPane().setContent(promptGrid);

    ButtonType buttonSubmit = new ButtonType("Submit Change", ButtonBar.ButtonData.OK_DONE);
    this.getDialogPane().getButtonTypes().add(buttonSubmit);

    this.setResultConverter(new Callback<>() {
      /**
       * call method
       *
       * @param b The single argument upon which the returned value should be
       *      determined.
       * @return DayEnum
       */
      @Override
      public DayEnum call(ButtonType b) {

        if (b == buttonSubmit && !dayOfWeekOptions.getValue().equals(null)) {

          return DayEnum.valueOf(dayOfWeekOptions.getValue().toString());
        }

        return null;
      }
    });
  }
}

