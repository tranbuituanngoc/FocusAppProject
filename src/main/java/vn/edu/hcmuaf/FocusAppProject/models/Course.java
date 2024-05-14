package vn.edu.hcmuaf.FocusAppProject.models;

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
    @Column(name = "theory_hours")
    private int theoryHours;
    @Column(name = "practice_hours")
    private int practiceHours;
    @Column(name = "credits")
    private int credits;
    @Column(name = "is_mandatory")
    private boolean isMandatory;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToMany
    @JoinTable(
            name = "prerequisites",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_id")
    )
    private List<Course> prerequisites;
    @OneToMany(mappedBy = "course")
    List<UserCourse> userCourses;
    @OneToMany(mappedBy = "course")
    List<TestSchedule> testSchedules;
    @OneToMany(mappedBy = "course")
    List<CourseSchedule> courseSchedules;
    @OneToMany(mappedBy = "course")
    Set<CourseGroupCourses> courseGroupCourses;
}
