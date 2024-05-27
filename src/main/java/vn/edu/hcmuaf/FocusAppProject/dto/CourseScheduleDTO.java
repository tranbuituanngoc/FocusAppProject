package vn.edu.hcmuaf.FocusAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourseScheduleDTO {
    @JsonProperty("course_id")
    private int courseId;
    @JsonProperty("course_name")
    private String courseName;
    @JsonProperty("is_practice")
    private boolean practice;
    @JsonProperty("study_slot")
    private int studySlot;
    @JsonProperty("date_num_type")
    private int dateNumType;
    @JsonProperty("course_room")
    private String courseRoom;
    @JsonProperty("num_of_lession")
    private int numOfLession;
}
