package vn.edu.hcmuaf.FocusAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserScheduleDTO {
    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("list_weeks")
    private List<WeekDTO> weeks;
    @JsonProperty("semester_id")
    private int semesterId;
}
