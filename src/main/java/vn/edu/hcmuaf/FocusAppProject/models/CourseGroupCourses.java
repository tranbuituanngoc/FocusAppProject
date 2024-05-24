package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.hcmuaf.FocusAppProject.models.keys.KeyCourseGroupCourses;

import java.util.Objects;

@Entity(name = "course_group_courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseGroupCourses {
    @EmbeddedId
    private KeyCourseGroupCourses id;
    @JsonManagedReference
    @MapsId("courseGroupId")
    @ManyToOne
    @JoinColumn(name = "course_group_id")
    private CourseGroup group;
    @JsonManagedReference
    @MapsId("courseId")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
