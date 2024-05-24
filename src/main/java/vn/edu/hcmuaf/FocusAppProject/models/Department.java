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

import java.util.Objects;
import java.util.Set;

@Entity(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    private long id;
    @Column(name = "department_name")
    private String departmentName;
    @JsonBackReference
    @OneToMany(mappedBy = "department")
    private Set<Course> courses;
    @JsonBackReference
    @OneToMany(mappedBy = "department")
    private Set<TrainingProgram> trainingPrograms;
    @JsonBackReference
    @OneToMany(mappedBy = "department")
    private Set<User> users;
    @JsonBackReference
    @OneToMany(mappedBy = "department")
    private Set<Major> majors;
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
