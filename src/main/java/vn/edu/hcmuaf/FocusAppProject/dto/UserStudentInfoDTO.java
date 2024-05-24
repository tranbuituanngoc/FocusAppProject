package vn.edu.hcmuaf.FocusAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStudentInfoDTO {
    @JsonProperty("user_id")
    long userId;
    @JsonProperty("department_id")
    String departmentId;
    @JsonProperty("major_id")
    String majorId;
    @JsonProperty("desired_score")
    double desiredScore;
}
