package cs3500.pa05.controller;


import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * SceneChangeEventHandler class
 */
public class SceneChangeEventHandler implements EventHandler {
  private ScreenState state = ScreenState.SPLASHSCREEN;
  private PrimaryController controller;

  /**
   * constructor
   *
   * @param controller PrimaryController
   */
  public SceneChangeEventHandler(PrimaryController controller) {
    this.controller = controller;
  }

  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    if (this.state == ScreenState.SPLASHSCREEN) {
      this.state = ScreenState.WEEKVIEW;
      this.controller.run(this.state);
    }
  }

}
