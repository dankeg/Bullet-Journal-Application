package cs3500.pa05.controller;

import cs3500.pa05.model.PrimaryModel;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * AbstractHandler class
 */
public abstract class AbstractHandler extends ModelHolder implements EventHandler {
  /**
   * handle method
   *
   * @param event the event which occurred
   */
  @Override
  public abstract void handle(Event event);

}
