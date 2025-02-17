package vn.edu.hcmuaf.FocusAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {
    @JsonProperty("course_id")
    private int courseId;
    @JsonProperty("course_name")
    private String courseName;
    @JsonProperty("is_mandatory")
    private boolean isMandatory;
    @JsonProperty("theory_hours")
    private Integer theoryHours;
    @JsonProperty("practice_hours")
    private Integer practiceHours;
    @JsonProperty("credits")
    private Integer credits;
}
