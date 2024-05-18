package vn.edu.hcmuaf.FocusAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSemesterDTO {
    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("list_semesters")
    private List<SemesterScoreDTO> semesters;
}
