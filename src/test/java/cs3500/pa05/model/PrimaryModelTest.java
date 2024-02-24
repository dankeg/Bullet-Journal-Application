package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa05.model.record.DayEnum;
import cs3500.pa05.model.record.UserEvent;
import cs3500.pa05.model.record.UserTask;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class containing tests for the class PrimaryModel
 */
class PrimaryModelTest {
  PrimaryModel example1;
  PrimaryModel example2;

  /**
   * Initializes examples for usage in tests
   */
  @BeforeEach
  void setup() {
    try {
      FileWriter writeContent = new FileWriter("src/main/resources/existingFile.bujo");
      List<String> strList = Files.readAllLines(Path.of("src/main/resources/contentToCopy.bujo"));
      String fullString = "";

      for (String str : strList) {
        fullString = fullString + str;
      }

      System.out.println(fullString);
      writeContent.write(fullString);
      writeContent.close();

      File existingFile = new File("src/main/resources/existingFile.bujo");
      File newPath = new File("src/main/resources");
      example1 = new PrimaryModel(existingFile);
      example2 = new PrimaryModel(newPath);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Tests the getTaskMax method of the class PrimaryModel
   */
  @Test
  void getTaskMax() {
    Assertions.assertEquals(example1.getTaskMax(), 10);
    Assertions.assertEquals(example2.getTaskMax(), 1000);
  }

  /**
   * Tests the getEventMax method of the class PrimaryModel
   */
  @Test
  void getEventMax() {
    Assertions.assertEquals(example1.getEventMax(), 9);
    Assertions.assertEquals(example2.getEventMax(), 1000);
  }

  /**
   * Tests the getUserTasks method of the class PrimaryModel
   */
  @Test
  void getUserTasks() {
    ObservableList<UserTask> expectedExample1 = FXCollections.observableArrayList();

    expectedExample1.add(new UserTask(DayEnum.TUESDAY, "qwq", "wqweqw"));
    UserTask task1 = new UserTask(DayEnum.TUESDAY, "wqe", "q");
    task1.setCompleted(true);

    UserTask task2 = new UserTask(DayEnum.TUESDAY, "weqweqw", "qeqwew");
    task2.setCompleted(true);

    expectedExample1.add(task1);
    expectedExample1.add(task2);

    ObservableList<UserTask> outputExample1 = example1.getUserTasks();

    for (int i = 0; i < outputExample1.size(); i++) {
      UserTask taskExample = expectedExample1.get(i);
      UserTask taskReal = outputExample1.get(i);

      Assertions.assertEquals(taskExample.getCompleted(), taskReal.getCompleted());
      Assertions.assertEquals(taskExample.getName(), taskReal.getName());
      Assertions.assertEquals(taskExample.getDescription(), taskReal.getDescription());
      Assertions.assertEquals(taskExample.getDay(), taskExample.getDay());
    }

    ObservableList<UserTask> expectedExample2 = FXCollections.observableArrayList();

    Assertions.assertEquals(example2.getUserTasks(), expectedExample2);
  }

  /**
   * Tests the getUserEvents method of the class PrimaryModel
   */
  @Test
  void getUserEvents() {
    ObservableList<UserEvent> expectedExample1 = FXCollections.observableArrayList();
    ObservableList<UserEvent> expectedExample2 = FXCollections.observableArrayList();

    expectedExample1.add(new UserEvent("q", DayEnum.FRIDAY, "ewdreqw",
        "asa", "wq"));

    ObservableList<UserEvent> outputExample1 = FXCollections.observableArrayList();

    for (int i = 0; i < outputExample1.size(); i++) {
      System.out.println(i);
      UserEvent taskExample = expectedExample1.get(i);
      UserEvent taskReal = outputExample1.get(i);

      Assertions.assertEquals(taskExample.getDay(), taskReal.getDay());
      Assertions.assertEquals(taskExample.getName(), taskReal.getName());
      Assertions.assertEquals(taskExample.getDuration(), taskReal.getDuration());
      Assertions.assertEquals(taskExample.getStartTime(), taskReal.getStartTime());
      Assertions.assertEquals(taskExample.getDescription(), taskReal.getDescription());
    }

    Assertions.assertEquals(example2.getUserEvents(), expectedExample2);
  }

  /**
   * Tests the getWeekStart method of the class PrimaryModel
   */
  @Test
  void getWeekStart() {
    ObservableList<DayEnum> expectedExample1 = FXCollections.observableArrayList();
    ObservableList<DayEnum> expectedExample2 = FXCollections.observableArrayList();

    expectedExample1.addAll(List.of(DayEnum.THURSDAY, DayEnum.FRIDAY, DayEnum.SATURDAY,
        DayEnum.SUNDAY, DayEnum.MONDAY, DayEnum.TUESDAY, DayEnum.WEDNESDAY));

    expectedExample2.addAll(List.of(DayEnum.MONDAY, DayEnum.TUESDAY, DayEnum.WEDNESDAY,
        DayEnum.THURSDAY, DayEnum.FRIDAY, DayEnum.SATURDAY, DayEnum.SUNDAY));

    Assertions.assertEquals(example1.getWeekStart(), expectedExample1);
    Assertions.assertEquals(example2.getWeekStart(), expectedExample2);
  }

  /**
   * Tests the setWeekStart method of the class PrimaryModel
   */
  @Test
  void setWeekStart() {
    Assertions.assertDoesNotThrow(() -> example1.setWeekStart(DayEnum.FRIDAY));
    Assertions.assertDoesNotThrow(() -> example2.setWeekStart(DayEnum.WEDNESDAY));

    ObservableList<DayEnum> expectedExample1 = FXCollections.observableArrayList();
    ObservableList<DayEnum> expectedExample2 = FXCollections.observableArrayList();

    expectedExample1.addAll(DayEnum.FRIDAY, DayEnum.SATURDAY, DayEnum.SUNDAY, DayEnum.MONDAY,
        DayEnum.TUESDAY, DayEnum.WEDNESDAY, DayEnum.THURSDAY);
    expectedExample2.addAll(DayEnum.WEDNESDAY, DayEnum.THURSDAY, DayEnum.FRIDAY, DayEnum.SATURDAY,
        DayEnum.SUNDAY, DayEnum.MONDAY, DayEnum.TUESDAY);

    Assertions.assertEquals(example1.getWeekStart(), expectedExample1);
    Assertions.assertEquals(example2.getWeekStart(), expectedExample2);
  }

  /**
   * Tests the addTask method of the class PrimaryModel
   */
  @Test
  void addTask() {
    UserTask newTask = new UserTask(DayEnum.FRIDAY, "Some Content Here",
        "More Content Here");
    Assertions.assertDoesNotThrow(() -> example1.addTask(newTask));
    Assertions.assertDoesNotThrow(() -> example2.addTask((newTask)));

    ObservableList<UserTask> expectedExample1 = FXCollections.observableArrayList();

    expectedExample1.add(new UserTask(DayEnum.TUESDAY, "qwq", "wqweqw"));
    UserTask task1 = new UserTask(DayEnum.TUESDAY, "wqe", "q");
    task1.setCompleted(true);

    UserTask task2 = new UserTask(DayEnum.TUESDAY, "weqweqw", "qeqwew");
    task2.setCompleted(true);

    expectedExample1.add(task1);
    expectedExample1.add(task2);
    expectedExample1.add(newTask);

    ObservableList<UserTask> expectedExample2 = FXCollections.observableArrayList();
    expectedExample2.add(newTask);

    ObservableList<UserTask> outputExample1 = example1.getUserTasks();
    ObservableList<UserTask> outputExample2 = example2.getUserTasks();

    for (int i = 0; i < outputExample1.size(); i++) {
      UserTask taskExample = expectedExample1.get(i);
      UserTask taskReal = outputExample1.get(i);

      Assertions.assertEquals(taskExample.getCompleted(), taskReal.getCompleted());
      Assertions.assertEquals(taskExample.getName(), taskReal.getName());
      Assertions.assertEquals(taskExample.getDescription(), taskReal.getDescription());
      Assertions.assertEquals(taskExample.getDay(), taskExample.getDay());
    }

    for (int i = 0; i < outputExample2.size(); i++) {
      UserTask taskExample = expectedExample2.get(i);
      UserTask taskReal = outputExample2.get(i);

      Assertions.assertEquals(taskExample.getCompleted(), taskReal.getCompleted());
      Assertions.assertEquals(taskExample.getName(), taskReal.getName());
      Assertions.assertEquals(taskExample.getDescription(), taskReal.getDescription());
      Assertions.assertEquals(taskExample.getDay(), taskExample.getDay());
    }

  }

  /**
   * Tests the addEvent method of the class PrimaryModel
   */
  @Test
  void addEvent() {
    UserEvent newEvent = new UserEvent("Some information", DayEnum.SATURDAY,
        "More information", "12:00", "30");
    UserEvent existingEvent = new UserEvent("q", DayEnum.FRIDAY, "ewdreqw",
        "asa", "wq");

    ObservableList<UserEvent> expectedExample1 = FXCollections.observableArrayList();
    ObservableList<UserEvent> expectedExample2 = FXCollections.observableArrayList();

    expectedExample1.add(existingEvent);
    expectedExample1.add((newEvent));

    expectedExample2.add(newEvent);

    Assertions.assertDoesNotThrow(() -> example1.addEvent(newEvent));
    Assertions.assertDoesNotThrow(() -> example2.addEvent((newEvent)));

    ObservableList<UserEvent> outputExample1 = example1.getUserEvents();
    ObservableList<UserEvent> outputExample2 = example2.getUserEvents();

    for (int i = 0; i < outputExample1.size(); i++) {
      UserEvent taskExample = expectedExample1.get(i);
      UserEvent taskReal = outputExample1.get(i);

      Assertions.assertEquals(taskExample.getDay(), taskReal.getDay());
      Assertions.assertEquals(taskExample.getName(), taskReal.getName());
      Assertions.assertEquals(taskExample.getDuration(), taskReal.getDuration());
      Assertions.assertEquals(taskExample.getStartTime(), taskReal.getStartTime());
      Assertions.assertEquals(taskExample.getDescription(), taskReal.getDescription());
    }

    for (int i = 0; i < outputExample2.size(); i++) {
      UserEvent taskExample = expectedExample2.get(i);
      UserEvent taskReal = outputExample2.get(i);

      Assertions.assertEquals(taskExample.getDay(), taskReal.getDay());
      Assertions.assertEquals(taskExample.getName(), taskReal.getName());
      Assertions.assertEquals(taskExample.getDuration(), taskReal.getDuration());
      Assertions.assertEquals(taskExample.getStartTime(), taskReal.getStartTime());
      Assertions.assertEquals(taskExample.getDescription(), taskReal.getDescription());
    }


  }

  /**
   * Tests the addTaskMax method of the class PrimaryModel
   */
  @Test
  void addTaskMax() {
    Assertions.assertDoesNotThrow(() -> example1.addTaskMax(100));
    Assertions.assertDoesNotThrow(() -> example2.addTaskMax(45));

    Assertions.assertEquals(example1.getTaskMax(), 100);
    Assertions.assertEquals(example2.getTaskMax(), 45);
  }

  /**
   * Tests the addEventMax method of the class PrimaryModel
   */
  @Test
  void addEventMax() {
    Assertions.assertDoesNotThrow(() -> example1.addEventMax(10));
    Assertions.assertDoesNotThrow(() -> example2.addEventMax(54));

    Assertions.assertEquals(example1.getEventMax(), 10);
    Assertions.assertEquals(example2.getEventMax(), 54);
  }

  /**
   * Tests the save method of the class PrimaryModel
   */
  @Test
  void save() {
    Assertions.assertDoesNotThrow(() -> example1.save());
    Assertions.assertDoesNotThrow(() -> example2.save());

    try {
      Assertions.assertEquals(Files.readAllLines(Path.of(
          "src/main/resources/contentToCopy.bujo")), Files.readAllLines(
          Path.of("src/main/resources/existingFile.bujo")));
    } catch (IOException e) {
      fail();
    }

    try {
      Assertions.assertEquals(Files.readAllLines(Path.of(
          "src/main/resources/journal.bujo")), Files.readAllLines(
          Path.of("src/main/resources/emptyJournal.bujo")));
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * Tests the calculatePercentCompleted method of the class PrimaryModel
   */
  @Test
  void calculatePercentCompleted() {
    Assertions.assertEquals(example1.calculatePercentCompleted(), 66.66666666666666);
    Assertions.assertEquals(example2.calculatePercentCompleted(), 0);
  }
}