package vn.edu.hcmuaf.FocusAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeekDTO {
    @JsonProperty("semester_week")
    private int semesterWeek;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("course_schedules")
    private List<CourseScheduleDTO> courseSchedules;
}
