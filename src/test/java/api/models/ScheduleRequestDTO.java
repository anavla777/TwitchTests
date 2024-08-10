package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleRequestDTO {
    @JsonProperty("start_time")
    public String startTime;
    public String timezone;
    public int duration;
    @JsonProperty("is_recurring")
    public boolean isRecurring;
    @JsonProperty("category_id")
    public int categoryId;
    public String title;
}
