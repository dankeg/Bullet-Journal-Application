package cs3500.pa05.model.record;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To test all functionality and implementation for TasksLists record
 */
class TasksListTest {
  UserTask task;
  UserTask task2;
  TasksList tasksList;

  /**
   * To instantiate TasksList record for testing purposes
   */
  @BeforeEach
  public void setup() {
    this.task =
        new UserTask(DayEnum.MONDAY, "Task name", "Test description");
    this.task2 =
        new UserTask(DayEnum.WEDNESDAY, "Task name2", "Test description2");
    List<UserTask> tasks = new ArrayList<>(List.of(task, task2));
    this.tasksList = new TasksList(tasks);
  }

  /**
   * To test getTasks for TasksList record
   */
  @Test
  public void testTasks() {
    assertEquals(this.task, this.tasksList.tasks().get(0));
    assertEquals(this.task2, this.tasksList.tasks().get(1));
  }
}