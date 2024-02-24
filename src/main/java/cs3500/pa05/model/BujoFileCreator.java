package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * To create a .bujo file from the given serialized Bujo node,
 * and absolute path to file creation
 */
public class BujoFileCreator {

  /**
   * To write the contents of given bujo node to a .bujo file at given path
   *
   * @param bujoNode bujo node
   * @param path given path
   */
  public static void createBujo(JsonNode bujoNode, Path path) {
    // To create data from bujoNode, and .bujo path for file writing
    String bujoContents = bujoNode.toPrettyString();
    byte[] data = bujoContents.getBytes();

    // Write the file
    try {
      Files.write(path, data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
