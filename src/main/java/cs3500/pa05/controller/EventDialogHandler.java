package cs3500.pa05.controller;

import cs3500.pa05.model.record.UserEvent;
import cs3500.pa05.view.EventDialogue;
import java.util.Optional;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

/**
 * EventDialogHandler class
 */
public class EventDialogHandler extends AbstractHandler {
  private Button button;

  /**
   * constructor
   *
   * @param button Button
   */
  public EventDialogHandler(Button button) {
    this.button = button;
  }

  /**
   * constructor
   */
  public EventDialogHandler() {}

  /**
   * handle method
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    EventDialogue eventDialogue = new EventDialogue();
    Optional<UserEvent> result = eventDialogue.showAndWait();;
    if (result.isPresent()) {
      UserEvent providedEvent = result.get();
      staticModel.addEvent(providedEvent);
      eventDialogue.close();
    }

    if (staticModel.getUserEvents().size() >= staticModel.getEventMax() && !(button == null)) {
      Tooltip eventTooltip = new Tooltip(
          "Whoops, be careful you are at your maximum number of events");
      eventTooltip.setShowDelay(javafx.util.Duration.millis(10));
      this.button.setTooltip(eventTooltip);
    }
  }
}
