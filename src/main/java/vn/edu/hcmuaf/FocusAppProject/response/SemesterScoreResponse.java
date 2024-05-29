package vn.edu.hcmuaf.FocusAppProject.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.hcmuaf.FocusAppProject.dto.ScoreDTO;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SemesterScoreResponse {
    @JsonProperty("semester_id")
    private int semesterId;
    @JsonProperty("semester_name")
    private String semesterName;
    @JsonProperty("gpa_4")
    private Double gpa4;
    @JsonProperty("cumulative_gpa_4")
    private Double cumulativeGpa4;
    @JsonProperty("gpa_10")
    private Double gpa10;
    @JsonProperty("cumulative_gpa_10")
    private Double cumulativeGpa10;
    @JsonProperty("cumulative_credit")
    private Integer cumulativeCredit;
    @JsonProperty("credit_hours")
    private Integer creditHours;
    @JsonProperty("list_scores")
    private List<ScoreResponse> scores;
}
