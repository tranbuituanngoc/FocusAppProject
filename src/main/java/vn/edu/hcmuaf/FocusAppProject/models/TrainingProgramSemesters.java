package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.hcmuaf.FocusAppProject.models.keys.KeyTrainingProgramSemester;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "training_program_semesters")
public class TrainingProgramSemesters {
    @EmbeddedId
    private KeyTrainingProgramSemester id;
    @MapsId("trainingProgramId")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "training_program_id")
    private TrainingProgram trainingProgram;
    @MapsId("semesterId")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
