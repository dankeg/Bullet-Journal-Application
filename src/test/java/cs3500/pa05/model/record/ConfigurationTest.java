package cs3500.pa05.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To test all functionality and implementation for Configuration record
 */
class ConfigurationTest {
  Configuration config;

  /**
   * To instantiate a Configuration record for testing purposes
   */
  @BeforeEach
  public void setup() {
    List<DayEnum> days = new ArrayList<>(List.of(DayEnum.FRIDAY, DayEnum.SATURDAY));
    this.config = new Configuration(10, 12, days);
  }

  /**
   * To test getTaskMax for Configuration record
   */
  @Test
  public void testTaskMax() {
    assertEquals(10, config.taskMax());
  }

  /**
   * To test eventMax for Configuration record
   */
  @Test
  public void eventMax() {
    assertEquals(12, config.eventMax());
  }

  /**
   * To test dayEnum for Configuration record
   */
  @Test
  public void dayEnum() {
    List<DayEnum> days = new ArrayList<>(List.of(DayEnum.FRIDAY, DayEnum.SATURDAY));
    assertEquals(days, config.dayEnum());
  }
}