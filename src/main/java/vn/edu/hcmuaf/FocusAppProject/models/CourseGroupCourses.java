package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.hcmuaf.FocusAppProject.models.keys.KeyCourseGroupCourses;

@Entity(name = "course_group_courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseGroupCourses {
    @EmbeddedId
    private KeyCourseGroupCourses id;
    @JsonBackReference
    @MapsId("courseGroupId")
    @ManyToOne
    @JoinColumn(name = "course_group_id")
    private CourseGroup group;
    @JsonBackReference
    @MapsId("courseId")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
