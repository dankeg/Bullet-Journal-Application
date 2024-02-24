package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.record.Bujo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * To interpret a Bujo file, and be able to fetch all metadata necessary
 * for the program from a users desired path
 */
public class BujoInterpreter {

  /**
   * To parse the given file into a Bujo record to be used in the model
   *
   * @param path given path to .bujo file
   * @return bujo record
   */
  Bujo parseBujo(Path path) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      File file = path.toFile();
      Scanner scan = new Scanner(file);
      String addString = "";
      while (scan.hasNextLine()) {
        addString = addString + scan.nextLine();
      }
      JsonParser parser = mapper.getFactory().createParser(addString);
      Bujo bujoRecord = parser.readValueAs(Bujo.class);
      return bujoRecord;
    } catch (IOException e) {
      // Disconnected from server or parsing exception
      throw new RuntimeException("Could not parse the given JsonFile");
    }
  }
}
