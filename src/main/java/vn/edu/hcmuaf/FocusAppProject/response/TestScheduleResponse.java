package vn.edu.hcmuaf.FocusAppProject.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestScheduleResponse {
    @JsonProperty("course_id")
    long courseId;
    @JsonProperty("course_name")
    String courseName;
    @JsonProperty("test_date")
    String testDate;
    @JsonProperty("test_time")
    String testTime;
    @JsonProperty("test_room")
    String testRoom;
    @JsonProperty("test_slot")
    int testSlot;
}
