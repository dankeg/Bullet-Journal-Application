package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.record.Configuration;
import cs3500.pa05.model.record.DayEnum;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To test all implementation for BujoFileCreator class
 */
class BujoFileCreatorTest {
  BujoFileCreator bujoFileCreator;
  Configuration config;
  JsonNode configurationNode;

  /**
   * To set up a BujoFileCreator for test
   */
  @BeforeEach
  public void setup() {
    this.bujoFileCreator = new BujoFileCreator();
    List<DayEnum> days = new ArrayList<>(List.of(DayEnum.FRIDAY, DayEnum.SATURDAY));
    this.config = new Configuration(10, 12, days);
    this.configurationNode = JsonUtils.serializeRecord(config);
  }

  /**
   * To test createBujo for BujoFileCreator
   */
  @Test
  void createBujo() {
    Path pathToTestFile = Path.of("src/main/resources/creation.bujo");
    BujoFileCreator.createBujo(this.configurationNode, pathToTestFile);
    File creationFile = pathToTestFile.toFile();
    assertTrue(creationFile.exists());
    String fileContents = "";
    try {
      fileContents = Files.readAllLines(pathToTestFile).toString();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertTrue(fileContents.contains("10"));
    assertTrue(fileContents.contains("12"));
  }

  /**
   * To test exception thrown in writeToFile when file argument
   * does not exist
   */
  @Test
  public void testBujoCreationException() {
    Path fakePath = Path.of("RandomDirectory/RandomPath");
    assertThrows(
        RuntimeException.class,
        () -> BujoFileCreator.createBujo(configurationNode, fakePath));
  }
}