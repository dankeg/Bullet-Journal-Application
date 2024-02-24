package cs3500.pa05.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * To represent a fake Object that will always throw an exception when serialized into
 * Json
 */
public class MockObject {
  final int length;

  /**
   * JSON creator for this object, so that it will always fail
   *
   * @param length int
   */
  @JsonCreator
  public MockObject(
      @JsonProperty ("length") int length) {
    this.length = length;
  }

  /**
   * Getter for the length of this mock object
   *
   * @return illegal argument exception always
   */
  public int getLength() {
    throw new IllegalArgumentException("imagine");
  }
}
