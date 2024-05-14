package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "department_name")
    private String departmentName;
    @OneToMany(mappedBy = "department")
    private Set<Course> courses;
    @OneToMany(mappedBy = "department")
    private Set<TrainingProgram> trainingPrograms;
    @JsonBackReference
    @OneToMany(mappedBy = "department")
    private Set<User> users;
}
