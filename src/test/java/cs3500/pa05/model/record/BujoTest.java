package cs3500.pa05.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.JsonUtils;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To test all functionality and implementation for BujoRecord
 */
class BujoTest {
  UserEvent event1;
  UserEvent event2;
  EventsList eventsList;
  UserTask task;
  UserTask task2;
  TasksList tasksList;
  Configuration config;
  JsonNode eventsListsNode;
  JsonNode tasksListNode;
  JsonNode configNode;
  Bujo bujo;

  /**
   * To instantiate a Bujo record to be tested in following methods
   */
  @BeforeEach
  public void setup() {
    this.task =
        new UserTask(DayEnum.MONDAY, "Task name", "Test description");
    this.task2 =
        new UserTask(DayEnum.WEDNESDAY, "Task name2", "Test description2");
    List<UserTask> tasks = new ArrayList<>(List.of(task, task2));
    this.tasksList = new TasksList(tasks);
    this.event1 =
        new UserEvent("Event1", DayEnum.WEDNESDAY, "2:00", "1 Hour", ""
            + "Exam");
    this.event2 =
        new UserEvent("Event2", DayEnum.THURSDAY, "4:00", "2 Hours", ""
            + "Quiz");
    List<UserEvent> events = new ArrayList<>(List.of(event1, event2));
    this.eventsList = new EventsList(events);
    List<DayEnum> days = new ArrayList<>(List.of(DayEnum.FRIDAY, DayEnum.SATURDAY));
    this.config = new Configuration(10, 12, days);
    this.eventsListsNode = JsonUtils.serializeRecord(eventsList);
    this.tasksListNode = JsonUtils.serializeRecord(tasksList);
    this.configNode = JsonUtils.serializeRecord(config);
    this.bujo = new Bujo(configNode, eventsListsNode, tasksListNode);
  }

  /**
   * To test configuration for BujoClass
   */
  @Test
  public void testConfiguration() {
    assertEquals(configNode, bujo.configuration());
  }

  /**
   * To test getUserEvents for Bujo record
   */
  @Test
  public void testUserEvents() {
    assertEquals(eventsListsNode, bujo.userEvents());
  }

  /**
   * To test getUserTasks for Bujo record
   */
  @Test
  public void testUserTasks() {
    assertEquals(tasksListNode, bujo.userTasks());
  }
}