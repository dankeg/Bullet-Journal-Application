package cs3500.pa05.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To test all functionality for EvensList record
 */
class EventsListTest {
  UserEvent event1;
  UserEvent event2;
  EventsList eventsList;

  /**
   * To instantiate EventsList record for testing purposes
   */
  @BeforeEach
  public void setup() {
    this.event1 =
        new UserEvent("Event1", DayEnum.WEDNESDAY, "2:00", "1 Hour", ""
            + "Exam");
    this.event2 =
        new UserEvent("Event2", DayEnum.THURSDAY, "4:00", "2 Hours", ""
            + "Quiz");
    List<UserEvent> events = new ArrayList<>(List.of(event1, event2));
    this.eventsList = new EventsList(events);
  }

  /**
   * To test getEvents for the EventsLists record
   */
  @Test
  void events() {
    assertEquals(this.event1, this.eventsList.events().get(0));
    assertEquals(this.event2, this.eventsList.events().get(1));
  }
}