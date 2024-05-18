package vn.edu.hcmuaf.FocusAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTestScheduleDTO {
    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("semester_id")
    private int semesterId;
    @JsonProperty("list_test_schedules")
    private List<TestScheduleDTO> testSchedules;
}
