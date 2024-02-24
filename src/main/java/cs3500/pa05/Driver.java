package cs3500.pa05;

import cs3500.pa05.controller.PrimaryController;
import cs3500.pa05.controller.ScreenState;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Driver class --> main application
 */
public class Driver extends Application {
  /**
   * start method
   *
   * @param primaryStage the primary stage for this application, onto which
   *     the application scene can be set.
   *     Applications may create other stages, if needed, but they will not be
   *     primary stages.
   *
   * @throws Exception Exception
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Stage stage = primaryStage;
    stage.setTitle("Bullet Journal");

    PrimaryController controller = new PrimaryController(stage);
    controller.run(ScreenState.TIMESPLASHSCREEN);

    stage.show();
  }

  /**
   * main method
   *
   * @param args String array
   */
  public static void main(String[] args) {
    launch();
  }
}
