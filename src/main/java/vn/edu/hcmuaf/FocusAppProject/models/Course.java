package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "courses")
public class Course {
    @Id
    private int id;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "theory_hours")
    private int theoryHours;
    @Column(name = "practice_hours")
    private int practiceHours;
    @Column(name = "credits")
    private int credits;
    @Column(name = "is_mandatory")
    private boolean isMandatory;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "prerequisites",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_id")
    )
    private List<Course> prerequisites;
    @JsonBackReference
    @OneToMany(mappedBy = "course")
    List<UserCourse> userCourses;
    @JsonBackReference
    @OneToMany(mappedBy = "course")
    Set<CourseGroupCourses> courseGroupCourses;
    @JsonBackReference
    @OneToMany(mappedBy = "course")
    Set<SemesterCourse> semesterCourses;

}
