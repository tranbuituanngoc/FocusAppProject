package vn.edu.hcmuaf.FocusAppProject.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.hcmuaf.FocusAppProject.dto.CourseScheduleDTO;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseScheduleResponse {
    @JsonProperty("dates")
    List<LocalDate> dates;
    @JsonProperty("list_course")
    List<CourseScheduleDTO> listCourse;
}
