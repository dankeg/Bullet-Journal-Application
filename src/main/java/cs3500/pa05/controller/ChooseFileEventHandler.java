package cs3500.pa05.controller;

import cs3500.pa05.model.PrimaryModel;
import cs3500.pa05.view.WeekView;
import java.io.File;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * ChooseFileEventHandler
 */
public class ChooseFileEventHandler extends AbstractHandler {
  private final FileChooser fileChooser = new FileChooser();
  private Stage stage;
  private PrimaryController primaryController;

  /**
   * constructor
   *
   * @param stage Stage
   * @param primaryController PrimaryController
   */
  public ChooseFileEventHandler(Stage stage, PrimaryController primaryController) {
    this.stage = stage;
    this.primaryController = primaryController;
  }

  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    File file = null;
    file = fileChooser.showOpenDialog(stage);

    if (file != null && file.toString().endsWith(".bujo")) {
      PrimaryModel model = new PrimaryModel(file); //create new file from existing path

      setModel(model); //set the model within the abstract handler
      primaryController.run(ScreenState.WEEKVIEW);
    }
  }
}
