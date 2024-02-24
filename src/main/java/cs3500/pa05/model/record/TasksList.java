package cs3500.pa05.model.record;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * tasksList record
 *
 * @param tasks record 
 */
public record TasksList(
    @JsonProperty("tasks") List<UserTask> tasks
) {
}
