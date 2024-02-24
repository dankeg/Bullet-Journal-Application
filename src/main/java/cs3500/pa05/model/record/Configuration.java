package cs3500.pa05.model.record;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * To represent the configurations necessary to create a Bujo program,
 * visually and functionally
 *
 * @param taskMax int
 * @param eventMax int
 * @param dayEnum list
 */
public record Configuration(
    @JsonProperty("taskMax") int taskMax,
    @JsonProperty("eventMax") int eventMax,
    @JsonProperty("weekLayout") List<DayEnum> dayEnum
) {
}
