package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.record.Configuration;
import cs3500.pa05.model.record.DayEnum;
import cs3500.pa05.model.record.MockRecord;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



/**
 * test Json Utils test
 */
class JsonUtilsTest {


  @Test
  void testConstructor() {
    Assertions.assertDoesNotThrow(() -> new JsonUtilsTest());
  }

  /**
   * test searializeRecords method for JsonUtils class
   */
  @Test
  void testSerializeRecord() {
    List<DayEnum> days = new ArrayList<>(List.of(DayEnum.FRIDAY, DayEnum.SATURDAY));
    Configuration config = new Configuration(10, 12, days);
    JsonNode utils = JsonUtils.serializeRecord(config);
    assertEquals(
        "{\"taskMax\":10,\"eventMax\":12,\"weekLayout\":[\"FRIDAY\",\"SATURDAY\"]}",
        utils.toString());

    MockObject mockObject = new MockObject(10);
    assertThrows(IllegalArgumentException.class,
        () -> JsonUtils.serializeRecord(new MockRecord(mockObject)));
  }
}