package vn.edu.hcmuaf.FocusAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDTO {
    private long id;
    @JsonProperty("user_id")
    private long userId;
    private String content;
    @JsonProperty("is_done")
    private boolean isDone;
    private String title;
    private String deadline;
}
