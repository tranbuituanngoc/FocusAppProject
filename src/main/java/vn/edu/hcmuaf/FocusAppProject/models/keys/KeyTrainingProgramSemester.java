package vn.edu.hcmuaf.FocusAppProject.models.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class KeyTrainingProgramSemester implements Serializable {
    @Column(name = "training_program_id")
    private long trainingProgramId;
    @Column(name = "semester_id")
    private int semesterId;
}
