package cs3500.pa05.model.record;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * UserEvent class
 */
public class UserEvent {
  @JsonProperty
  private String description;
  @JsonProperty
  private String name;
  @JsonProperty
  private DayEnum day;
  @JsonProperty
  private String startTime;
  @JsonProperty
  private String duration;

  /**
   * constructor
   *
   * @param name String
   * @param day Enum
   * @param startTime String
   * @param duration String
   * @param description String
   */
  @JsonCreator
  public UserEvent(@JsonProperty("name") String name,
                   @JsonProperty("day") DayEnum day,
                   @JsonProperty("startTime") String startTime,
                   @JsonProperty("duration") String duration,
                   @JsonProperty("description") String description) {
    this.name = name;
    this.day = day;
    this.startTime = startTime;
    this.duration = duration;
    this.description = description;
  }

  /**
   * getter for name
   *
   * @return String
   */
  public String getName() {
    return name;
  }

  /**
   * getter for day
   *
   * @return DayEnum day
   */
  public DayEnum getDay() {
    return day;
  }

  /**
   * getter for startTime
   *
   * @return String
   */
  public String getStartTime() {
    return startTime;
  }

  /**
   * getter for duration
   *
   * @return String
   */
  public String getDuration() {
    return duration;
  }

  /**
   * getter for description
   *
   * @return String
   */
  public String getDescription() {
    return description;
  }

}
