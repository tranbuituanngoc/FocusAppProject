package vn.edu.hcmuaf.FocusAppProject.models.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class KeyUserCourse implements Serializable {
    @Column(name = "user_id")
    private long userId;
    @Column(name = "course_id")
    private int courseId;
}
