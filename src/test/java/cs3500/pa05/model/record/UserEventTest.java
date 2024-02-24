package cs3500.pa05.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To test all functionality and implementation for UserEvent
 */
class UserEventTest {
  UserEvent event1;
  UserEvent event2;

  /**
   * To instantiate two events for testing purposes
   */
  @BeforeEach
  public void setup() {
    this.event1 =
        new UserEvent("Event1", DayEnum.WEDNESDAY, "2:00", "1 Hour", ""
            + "Exam");
    this.event2 =
        new UserEvent("Event2", DayEnum.THURSDAY, "4:00", "2 Hours", ""
            + "Quiz");
  }


  /**
   * To test getName for UserEvent class
   */
  @Test
  void getName() {
    assertEquals("Event1", event1.getName());
    assertEquals("Event2", event2.getName());
  }

  /**
   * To test getDay method for UserEvent
   */
  @Test
  void getDay() {
    assertEquals(DayEnum.WEDNESDAY, event1.getDay());
    assertEquals(DayEnum.THURSDAY, event2.getDay());
  }

  /**
   * To test getStart time for UserEvent class
   */
  @Test
  void getStartTime() {
    assertEquals("2:00", event1.getStartTime());
    assertEquals("4:00", event2.getStartTime());
  }

  /**
   * To test getDuration for UserEvent class
   */
  @Test
  void getDuration() {
    assertEquals("1 Hour", event1.getDuration());
    assertEquals("2 Hours", event2.getDuration());
  }

  /**
   * To test getDescription for UserEvent class
   */
  @Test
  void getDescription() {
    assertEquals("Exam", event1.getDescription());
    assertEquals("Quiz", event2.getDescription());
  }
}