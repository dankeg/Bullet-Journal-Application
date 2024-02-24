package cs3500.pa05.controller;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.BujoFileCreator;
import cs3500.pa05.model.JsonUtils;
import cs3500.pa05.model.record.Bujo;
import cs3500.pa05.model.record.Configuration;
import cs3500.pa05.model.record.UserEvent;
import cs3500.pa05.model.record.UserTask;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * SaveEventHandler class
 */
public class SaveEventHandler extends AbstractHandler {

  /**
   * handle method
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    staticModel.save();
  }
}
