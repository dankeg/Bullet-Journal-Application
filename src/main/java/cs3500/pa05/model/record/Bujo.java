package cs3500.pa05.model.record;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

/**
 * To represent a Bujo in Json, containing both its configurations and all
 *  * of its weeks metadata
 *
 * @param configuration JsonNode
 * @param userEvents JsonNode
 * @param userTasks JsonNode
 */
public record Bujo(
    @JsonProperty("config") JsonNode configuration,
    @JsonProperty("events") JsonNode userEvents,
    @JsonProperty("tasks") JsonNode userTasks
) {
}