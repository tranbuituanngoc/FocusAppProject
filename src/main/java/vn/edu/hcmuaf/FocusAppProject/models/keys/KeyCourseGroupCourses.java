package vn.edu.hcmuaf.FocusAppProject.models.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class KeyCourseGroupCourses implements Serializable {
    @Column(name = "course_group_id")
    private long courseGroupId;
    @Column(name = "course_id")
    private int courseId;
}
