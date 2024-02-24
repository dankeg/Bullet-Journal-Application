package cs3500.pa05.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.event.Event;

/**
 * To handle the event in which a URL is clicked, prompting program
 * to search for url on browser
 */
public class UrlEventHandler extends AbstractHandler {
  private final String url;

  /**
   * To instantiate this event handler with a url string
   *
   * @param url given url
   */
  public UrlEventHandler(String url) {
    this.url = url;
  }

  /**
   * handle method
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    try {
      Desktop.getDesktop().browse(new URI(url));
    } catch (IOException | URISyntaxException e) {
      System.out.println("damn");
    }
  }
}
