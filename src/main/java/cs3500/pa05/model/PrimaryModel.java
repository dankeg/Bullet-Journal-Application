package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.record.Bujo;
import cs3500.pa05.model.record.Configuration;
import cs3500.pa05.model.record.DayEnum;
import cs3500.pa05.model.record.EventsList;
import cs3500.pa05.model.record.TasksList;
import cs3500.pa05.model.record.UserEvent;
import cs3500.pa05.model.record.UserTask;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/**
 * PrimaryModel class
 */
public class PrimaryModel {
  // These should be in a hash map, of day to list of task and list of events
  private ObservableList<UserTask> userTasks;
  private ObservableList<UserEvent> userEvents;
  private ObservableList<DayEnum> dayOrder;
  private int eventMax;
  private int taskMax;
  //Configuration config;
  private File file;
  private Path pathToWrite;
  private Configuration config;

  /**
   * constructor overloaded if path already exists
   *
   * @param file File
   */
  public PrimaryModel(File file) {
    this.file = file;
    //initalize model

    //check if model is file (aka existing Bujo)
    if (this.file.isFile()) {
      BujoInterpreter interpreter = new BujoInterpreter();
      Bujo bujo = interpreter.parseBujo(file.toPath());
      ObjectMapper mapper = new ObjectMapper();
      // deserialize all of these records
      EventsList events = mapper.convertValue(bujo.userEvents(), EventsList.class);
      this.userTasks = FXCollections.observableArrayList();
      this.userEvents = FXCollections.observableArrayList();
      this.userEvents.addAll(events.events());
      TasksList tasks = mapper.convertValue(bujo.userTasks(), TasksList.class);
      this.userTasks.addAll(tasks.tasks());
      Configuration configuration = mapper.convertValue(bujo.configuration(), Configuration.class);
      this.config = configuration;
      this.eventMax = configuration.eventMax();
      this.taskMax = configuration.taskMax();
      this.dayOrder = FXCollections.observableArrayList();
      this.dayOrder.addAll(configuration.dayEnum());
      //else model is a path and create new (journal.bujo)
      this.pathToWrite = file.toPath();
    } else {
      this.userTasks = FXCollections.observableArrayList();
      this.userEvents = FXCollections.observableArrayList();
      this.eventMax = 1000;
      this.taskMax = 1000;
      this.dayOrder = FXCollections.observableArrayList();
      this.dayOrder.addAll(List.of(DayEnum.MONDAY, DayEnum.TUESDAY, DayEnum.WEDNESDAY,
          DayEnum.THURSDAY, DayEnum.FRIDAY, DayEnum.SATURDAY, DayEnum.SUNDAY));
      this.config = new Configuration(this.taskMax, this.eventMax, this.dayOrder);
      //else model is a path and create new (journal.bujo)
      String newBujoPath = file.toPath() + "/journal.bujo";
      this.pathToWrite = Path.of(newBujoPath);
    }
  }

  /**
   * getter for taskMax
   *
   * @return int
   */
  public int getTaskMax() {
    return taskMax;
  }

  /**
   * getter for eventMax
   *
   * @return int
   */
  public int getEventMax() {
    return eventMax;
  }

  /**
   * getter for userTasks
   *
   * @return Observable list
   */
  public ObservableList<UserTask> getUserTasks() {
    return userTasks;
  }

  /**
   * getter for userEvents
   *
   * @return Observable list
   */
  public ObservableList<UserEvent> getUserEvents() {
    return userEvents;
  }

  /**
   * getter for weekStart
   *
   * @return Observable list
   */
  public ObservableList<DayEnum> getWeekStart() {
    return dayOrder;
  }

  /**
   * setWeekStart method
   *
   * @param dayStart DayEnum
   */
  public void setWeekStart(DayEnum dayStart) {
    int indexOfBegin = dayOrder.indexOf(dayStart);

    for (int i = 0; i < indexOfBegin; i++) {
      dayOrder.add(dayOrder.remove(0));
    }
  }

  /**
   * addTask setter
   *
   * @param userTask UserTask
   */
  public void addTask(UserTask userTask) {
    userTasks.add(userTask);
  }

  /**
   * add event
   *
   * @param userEvent UserEvent
   */
  public void addEvent(UserEvent userEvent) {
    userEvents.add(userEvent);
  }

  /**
   * setter for taskMax
   *
   * @param max int
   */
  public void addTaskMax(int max) {
    this.taskMax = max;
  }

  /**
   * setter for eventMax
   *
   * @param max int
   */
  public void addEventMax(int max) {
    this.eventMax = max;
  }

  /**
   * save method --> saves model as bujo
   */
  public void save() {
    Configuration newConfig = new Configuration(this.taskMax, this.eventMax, this.dayOrder);
    JsonNode configNode = JsonUtils.serializeRecord(newConfig);
    EventsList eventsList = new EventsList(this.userEvents);
    JsonNode eventsNode = JsonUtils.serializeRecord(eventsList);
    TasksList tasksList = new TasksList(this.userTasks);
    JsonNode tasksNode = JsonUtils.serializeRecord(tasksList);
    Bujo saveBujo = new Bujo(configNode, eventsNode, tasksNode);
    JsonNode serializedBujo = JsonUtils.serializeRecord(saveBujo);
    BujoFileCreator.createBujo(serializedBujo, this.pathToWrite);
  }

  /**
   * To calculate the percentage of tasks completed
   *
   * @return percentage as an integer
   */
  public double calculatePercentCompleted() {
    if (this.userTasks.size() == 0) {
      return 0;
    }
    double completed = 0;
    for (UserTask task : this.userTasks) {
      if (task.getCompleted()) {
        completed++;
      }
    }

    return (completed / this.userTasks.size()) * 100;
  }
}
