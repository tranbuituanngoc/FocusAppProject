package vn.edu.hcmuaf.FocusAppProject.models;

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
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @OneToMany(mappedBy = "trainingProgram")
    Set<CourseGroup> courseGroups;
}
