package cs3500.pa05.model.record;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * UserTask class
 */
public class UserTask {
  @JsonProperty
  private String description;
  @JsonProperty
  private String name;
  @JsonProperty
  private DayEnum dayEnum;  //change to enum
  @JsonProperty
  private Boolean completed;

  /**
   * constructor
   *
   * @param dayEnum DayEnum
   * @param name String
   * @param description String
   */
  @JsonCreator
  public UserTask(@JsonProperty("day") DayEnum dayEnum,
                  @JsonProperty("name") String name,
                  @JsonProperty("description") String description) {
    this.dayEnum = dayEnum;
    this.name = name;
    this.description = description;
    this.completed = false;
  }

  /**
   * setter for compelted
   *
   * @param completed boolean
   */
  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  /**
   * getter for completed
   *
   * @return Boolean completed;
   */
  public Boolean getCompleted() {
    return completed;
  }

  /**
   * getter for day
   *
   * @return getDay
   */
  public DayEnum getDay() {
    return dayEnum;
  }

  /**
   * getDescription method
   *
   * @return String
   */
  public String getDescription() {
    return description;
  }

  /**
   * getName method
   *
   * @return String
   */
  public String getName() {
    return name;
  }
}
