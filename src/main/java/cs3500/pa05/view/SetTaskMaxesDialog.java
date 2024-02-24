package cs3500.pa05.view;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 * SetTaskMaxesDialog clsss
 */
public class SetTaskMaxesDialog extends Dialog<Integer> {

  /**
   * constructor
   */
  public SetTaskMaxesDialog() {
    this.setTitle("Configure UserTask Maxes:");
    this.setHeaderText("Please configure maximums for your weekly Events");
    this.setResizable(false);

    Label name = new Label("Maximum Tasks: ");
    TextField maxesField = new TextField();

    GridPane promptGrid = new GridPane();
    promptGrid.add(name, 1, 1);
    promptGrid.add(maxesField, 2, 1);
    this.getDialogPane().setContent(promptGrid);

    ButtonType buttonSubmit = new ButtonType("Submit Maxes", ButtonBar.ButtonData.OK_DONE);
    this.getDialogPane().getButtonTypes().add(buttonSubmit);

    this.setResultConverter(new Callback<>() {
      /**
       * call method
       *
       * @param b The single argument upon which the returned value should be
       *      determined.
       * @return Integer
       */
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