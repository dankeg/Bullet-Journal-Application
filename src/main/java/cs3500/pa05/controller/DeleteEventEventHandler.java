package cs3500.pa05.controller;

import cs3500.pa05.model.record.UserEvent;
import cs3500.pa05.model.record.UserTask;
import javafx.event.Event;

/**
 * DeleteEventEventHandler class
 */
public class DeleteEventEventHandler extends AbstractHandler {
  private UserEvent event;

  /**
   * constructor
   *
   * @param event userEvent
   */
  public DeleteEventEventHandler(UserEvent event) {
    this.event = event;
  }

  /**
   * handle method
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    staticModel.getUserEvents().remove(this.event);
  }
}
