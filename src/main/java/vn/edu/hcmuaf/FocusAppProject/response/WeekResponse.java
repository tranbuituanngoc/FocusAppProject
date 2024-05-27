package vn.edu.hcmuaf.FocusAppProject.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.hcmuaf.FocusAppProject.dto.WeekDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeekResponse {
    @JsonProperty("week_id")
    private long weekId;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("list_weeks")
    private List<WeekDTO> listWeek;
}
