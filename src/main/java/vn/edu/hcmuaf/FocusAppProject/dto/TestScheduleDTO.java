package vn.edu.hcmuaf.FocusAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestScheduleDTO {
    @JsonProperty("course_id")
    private int courseId;
    @JsonProperty("test_room")
    private String testRoom;
    @JsonProperty("test_time")
    private String testTime;
    @JsonProperty("test_date")
    private String testDate;
    @JsonProperty("start_slot")
    private int startSlot;
}
