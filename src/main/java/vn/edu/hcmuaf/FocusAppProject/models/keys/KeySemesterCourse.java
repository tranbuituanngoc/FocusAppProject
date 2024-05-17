package vn.edu.hcmuaf.FocusAppProject.models.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class KeySemesterCourse implements Serializable{
    @Column(name = "semester_id")
    private int semesterId;
    @Column(name = "course_id")
    private int courseId;
}
