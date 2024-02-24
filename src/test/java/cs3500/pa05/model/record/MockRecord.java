package cs3500.pa05.model.record;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.MockObject;

/**
 * MockRecord object
 *
 * @param object MockObject
 */
public record MockRecord(
    @JsonProperty("fake-property") MockObject object
) {
}