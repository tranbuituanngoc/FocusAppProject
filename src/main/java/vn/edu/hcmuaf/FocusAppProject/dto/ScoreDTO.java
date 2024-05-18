package vn.edu.hcmuaf.FocusAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {
    @JsonProperty("course_id")
    private int courseId;
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
