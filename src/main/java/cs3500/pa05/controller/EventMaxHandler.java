package cs3500.pa05.controller;

import cs3500.pa05.view.SetEventMaxesDialog;
import java.util.Optional;
import javafx.event.Event;

/**
 * EventMaxHandler class
 */
public class EventMaxHandler extends AbstractHandler {

  /**
   * handle method
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    SetEventMaxesDialog eventMaxDialogue = new SetEventMaxesDialog();
    Optional<Integer> result = eventMaxDialogue.showAndWait();;
    if (result.isPresent()) {
      Integer providedInt = result.get();
      staticModel.addEventMax(providedInt);
      eventMaxDialogue.close();
    }
  }
}
