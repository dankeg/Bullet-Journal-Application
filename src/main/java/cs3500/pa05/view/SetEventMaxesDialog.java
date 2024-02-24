package cs3500.pa05.view;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 * SetEventMaxesDialog class
 */
public class SetEventMaxesDialog extends Dialog<Integer> {

  /**
   * SetEventMaxesDialog constructor
   */
  public SetEventMaxesDialog() {
    this.setTitle("Configure UserEvent Max:");
    this.setHeaderText("Please configure maximums for your weekly Events");
    this.setResizable(false);

    Label name = new Label("Maximum Events: ");
    TextField maxesField = new TextField();

    GridPane promptGrid = new GridPane();
    promptGrid.add(name, 1, 1);
    promptGrid.add(maxesField, 2, 1);
    this.getDialogPane().setContent(promptGrid);

    ButtonType buttonSubmit = new ButtonType("Submit Maxes", ButtonBar.ButtonData.OK_DONE);
    this.getDialogPane().getButtonTypes().add(buttonSubmit);

    this.setResultConverter(new Callback<ButtonType, Integer>() {
      @Override
      public Integer call(ButtonType b) {

        if (b == buttonSubmit) {

          return Integer.parseInt(maxesField.getText());
        }

        return null;
      }
    });
  }
}
