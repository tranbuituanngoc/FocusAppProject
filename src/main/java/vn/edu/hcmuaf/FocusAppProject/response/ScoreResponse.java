package vn.edu.hcmuaf.FocusAppProject.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreResponse {
    @JsonProperty("course_id")
    private int courseId;
    @JsonProperty("course_name")
    private String courseName;
    @JsonProperty("credit")
    private int credit;
    @JsonProperty("component_score")
    private Double componentScore;
    @JsonProperty("score")
    private Double score;
    @JsonProperty("final_score_10")
    private Double finalScore10;
    @JsonProperty("final_score_4")
    private Double finalScore4;
    @JsonProperty("final_score_char")
    private String finalScoreChar;
    @JsonProperty("result")
    private boolean result;
}
