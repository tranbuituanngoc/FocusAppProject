package vn.edu.hcmuaf.FocusAppProject.models;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "courses")
public class Course {
    @Id
    @Column(nullable = false)
    private int id;
    @Column(name = "course_name",nullable = false)
    private String courseName;
    @Column(name = "level_of_difficult")
    private String levelOfDifficult;
    @Column(name = "semester")
    private String semester;
    @Column(name = "credit")
    private int credit;
    @Column(name = "obligatory")
    private boolean obligatory;
    @Column(name = "studied")
    private boolean studied;
    @OneToMany(mappedBy = "course")
    List<UserCourse> userCourses;
    @OneToMany(mappedBy = "course")
    List<TestSchedule> testSchedules;
    @OneToMany(mappedBy = "course")
    List<CourseSchedule> courseSchedules;
}
