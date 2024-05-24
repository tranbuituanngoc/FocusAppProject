package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "semester")
public class Semester {
    @Id
    @Column(name = "semester_id")
    private int semesterId;
    @Column(name = "semester_name")
    private String semesterName;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @JsonBackReference
    @OneToMany(mappedBy = "semester")
    private List<SemesterCourse> semesterCourses;
    @JsonBackReference
    @OneToMany(mappedBy = "semester")
    private List<TrainingProgramSemesters> trainingProgramSemesters;
    @Override
    public int hashCode() {
        return Objects.hash(semesterId);
    }
}
