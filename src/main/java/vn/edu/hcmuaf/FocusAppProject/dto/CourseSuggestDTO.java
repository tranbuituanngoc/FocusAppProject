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
public class CourseSuggestDTO {
    @JsonProperty("type_suggestion")
    private int typeSuggestion;
    @JsonProperty("list_course")
    private List<CourseDTO> listCourse;
    @JsonProperty("user_id")
    private long userId;
}
