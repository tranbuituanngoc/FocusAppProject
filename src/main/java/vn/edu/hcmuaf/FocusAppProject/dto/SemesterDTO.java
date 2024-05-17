package vn.edu.hcmuaf.FocusAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SemesterDTO {
    @JsonProperty("semester_id")
    private int semesterId;
    @JsonProperty("semester_name")
    private String semesterName;
    @JsonProperty("list_courses")
    List<CourseDTO> courses;
}
