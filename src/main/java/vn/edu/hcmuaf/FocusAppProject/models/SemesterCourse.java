package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.hcmuaf.FocusAppProject.models.keys.KeySemesterCourse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "semester_course")
public class SemesterCourse {
    @EmbeddedId
    private KeySemesterCourse id;
    @JsonManagedReference
    @MapsId("semesterId")
    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;
    @JsonManagedReference
    @MapsId("courseId")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
