package cs3500.pa05.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.record.Bujo;
import cs3500.pa05.model.record.Configuration;
import cs3500.pa05.model.record.DayEnum;
import cs3500.pa05.model.record.EventsList;
import cs3500.pa05.model.record.TasksList;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To test all functionality and implementation for BujoInterpreter class
 */
class BujoInterpreterTest {
  BujoInterpreter bujoInterpreter;

  /**
   * To instantaite a BujoInterpreter for testing purposes
   */
  @BeforeEach
  public void setup() {
    bujoInterpreter = new BujoInterpreter();
  }

  /**
   * To test parseBujo method
   */
  @Test
  public void testParseBujo() {
    ObjectMapper mapper = new ObjectMapper();
    Path pathToBujoJson = Path.of("src/main/resources/contentToCopy.bujo");
    Bujo parsedBujo = bujoInterpreter.parseBujo(pathToBujoJson);
    Configuration config =
        mapper.convertValue(parsedBujo.configuration(), Configuration.class);
    EventsList eventsList =
        mapper.convertValue(parsedBujo.userEvents(), EventsList.class);
    TasksList tasksList =
        mapper.convertValue(parsedBujo.userTasks(), TasksList.class);

    // Test correct config parsing
    Assertions.assertEquals(10, config.taskMax());
    Assertions.assertEquals(9, config.eventMax());

    // Test correct EventsList parsing
    Assertions.assertEquals("q", eventsList.events().get(0).getName());
    Assertions.assertEquals(DayEnum.FRIDAY, eventsList.events().get(0).getDay());
    Assertions.assertEquals("asa", eventsList.events().get(0).getDuration());

    // Test correct TasksList parsing
    Assertions.assertEquals(DayEnum.TUESDAY, tasksList.tasks().get(0).getDay());
    Assertions.assertEquals(DayEnum.TUESDAY, tasksList.tasks().get(1).getDay());
    Assertions.assertEquals(DayEnum.TUESDAY, tasksList.tasks().get(2).getDay());
  }

  /**
   * To test the case in which a path to parse from does not exist
   */
  @Test
  public void testParseException() {
    Path randomPath = Path.of("src/main/random.bujo");
    Assertions.assertThrows(
        RuntimeException.class,
        () -> bujoInterpreter.parseBujo(randomPath));
  }
}