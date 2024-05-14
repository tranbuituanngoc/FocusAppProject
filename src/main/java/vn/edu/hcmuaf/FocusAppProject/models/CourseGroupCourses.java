package vn.edu.hcmuaf.FocusAppProject.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.hcmuaf.FocusAppProject.models.key.KeyCourseGroupCourses;

@Entity(name = "course_group_courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseGroupCourses {
    @EmbeddedId
    KeyCourseGroupCourses key;
    @ManyToOne
    @JoinColumn(name = "course_group_id")
    private CourseGroup group;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
