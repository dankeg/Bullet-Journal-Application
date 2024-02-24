package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class used to serialize records into nodes, allowing Bujo to be serialized
 * and written to given file
 */
public class JsonUtils {

  /**
   * To convert a given record object to a JsonNode
   *
   * @param record given record
   * @return the JsonNode representation of the given record
   * @throws IllegalArgumentException if the record could not be converted correctly
   */
  public static JsonNode serializeRecord(Record record) throws IllegalArgumentException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode tempNode = mapper.convertValue(record, JsonNode.class);
      return tempNode;
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    }
  }
}