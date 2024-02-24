package cs3500.pa05.controller;

import cs3500.pa05.view.SplashScreenView;
import cs3500.pa05.view.TimedSplashScreenView;
import cs3500.pa05.view.WeekView;
import javafx.stage.Stage;

/**
 * PrimaryController class
 */
public class PrimaryController {
  private TimedSplashScreenView timedScreen;
  private SplashScreenView splashScreen;
  private WeekView weekScreen;
  private Stage appStage;

  /**
   * constructor
   *
   * @param stage Stage
   */
  public PrimaryController(Stage stage) {
    timedScreen = new TimedSplashScreenView(this);
    splashScreen = new SplashScreenView(stage, this);
    weekScreen = new WeekView(this);
    this.appStage = stage;
  }

  /**
   * run method
   *
   * @param state ScreenState
   */
  public void run(ScreenState state) {
    SceneChangeEventHandler sceneChangeEventHandler = new SceneChangeEventHandler(this);
    if (state == ScreenState.TIMESPLASHSCREEN) {
      this.appStage.setScene(timedScreen.display());
    } else if (state == ScreenState.SPLASHSCREEN) {
      this.appStage.setScene(splashScreen.display());
    } else if (state == ScreenState.WEEKVIEW) {
      this.appStage.setScene(weekScreen.display());
    }
  }

  /**
   * getStage method
   *
   * @return Stage
   */
  public Stage getStage() {
    return appStage;
  }



}
