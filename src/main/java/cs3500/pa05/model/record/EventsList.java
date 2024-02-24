package cs3500.pa05.model.record;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * EventsList record
 *
 * @param events list
 */
public record EventsList(
    @JsonProperty("events") List<UserEvent> events
) {
}
