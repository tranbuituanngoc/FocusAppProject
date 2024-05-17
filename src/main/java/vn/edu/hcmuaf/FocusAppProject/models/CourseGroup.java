package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "course_groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "min_credits")
    private int minCredits;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "training_program_id")
    private TrainingProgram trainingProgram;
    @JsonBackReference
    @OneToMany(mappedBy = "group")
    Set<CourseGroupCourses> courseGroupCourses;
}
