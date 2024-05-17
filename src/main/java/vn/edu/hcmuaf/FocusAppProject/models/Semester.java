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

import java.util.List;

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
    @JsonBackReference
    @OneToMany(mappedBy = "semester")
    private List<SemesterCourse> semesterCourses;
    @JsonBackReference
    @OneToMany(mappedBy = "semester")
    private List<TrainingProgramSemesters> trainingProgramSemesters;
}
