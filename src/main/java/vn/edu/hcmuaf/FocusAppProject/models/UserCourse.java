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
@Entity(name = "user_course")
public class UserCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_semester_id")
    private UserSemesters userSemesters;

    @JsonBackReference
    @OneToMany(mappedBy = "userCourse")
    Set<Score> scores;
    @JsonBackReference
    @OneToMany(mappedBy = "userCourse")
    List<CourseSchedule> courseSchedules;
    @JsonBackReference
    @OneToMany(mappedBy = "userCourse")
    List<TestSchedule> testSchedules;
}
