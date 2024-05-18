package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "training_program")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "year")
    private int year;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @JsonBackReference
    @OneToMany(mappedBy = "trainingProgram")
    Set<CourseGroup> courseGroups;
    @JsonBackReference
    @OneToMany(mappedBy = "trainingProgram")
    Set<User> users;
    @JsonBackReference
    @OneToMany(mappedBy = "trainingProgram")
    Set<TrainingProgramSemesters> trainingProgramSemesters;
}
