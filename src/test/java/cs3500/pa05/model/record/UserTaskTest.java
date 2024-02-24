package cs3500.pa05.model.record;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To test all functionality and implementation for UserTask
 */
class UserTaskTest {
  UserTask task;
  UserTask task2;

  /**
   * To instantiate two tasks for testing purposes
   */
  @BeforeEach
  public void setup() {
    this.task =
        new UserTask(DayEnum.MONDAY, "Task name", "Test description");
    this.task2 =
        new UserTask(DayEnum.WEDNESDAY, "Task name2", "Test description2");
  }

  /**
   * To test setting the tasks completion using getCompleted() method for integration
   */
  @Test
  public void testSetCompleted() {
    assertEquals(false, task.getCompleted());
    assertEquals(false, task2.getCompleted());
    task.setCompleted(true);
    task2.setCompleted(true);
    assertEquals(true, task.getCompleted());
    assertEquals(true, task2.getCompleted());
  }

  /**
   * To test getDay method for UserTask class
   */
  @Test
  public void testGetDay() {
    assertEquals(DayEnum.MONDAY, task.getDay());
    assertEquals(DayEnum.WEDNESDAY, task2.getDay());
  }

  /**
   * To test getDescription for UserTask class
   */
  @Test
  public void testGetDescription() {
    assertEquals("Test description", task.getDescription());
    assertEquals("Test description2", task2.getDescription());
  }

  /**
   * To test getName for UserTask class
   */
  @Test
  public void testGetName() {
    assertEquals("Task name", task.getName());
    assertEquals("Task name2", task2.getName());
  }
}