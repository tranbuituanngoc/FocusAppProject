package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.hcmuaf.FocusAppProject.models.key.KeyUserCourse;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user_course")
public class UserCourse {
    @EmbeddedId
    private KeyUserCourse key;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "userCourse")
    Set<Score> scores;
    @Column(name = "isPass")
    private boolean isPass;
}
