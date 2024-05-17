package vn.edu.hcmuaf.FocusAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingProgramDTO {
    @JsonProperty("year")
    private int year;
    @JsonProperty("department")
    private DepartmentDTO department;
    @JsonProperty("list_semesters")
    List<SemesterDTO> semesters;
}
