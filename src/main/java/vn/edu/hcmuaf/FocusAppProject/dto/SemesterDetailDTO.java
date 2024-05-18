package vn.edu.hcmuaf.FocusAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SemesterDetailDTO {
    @JsonProperty("semester_id")
    private int semesterId;
    @JsonProperty("semester_name")
    private String semesterName;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
}
