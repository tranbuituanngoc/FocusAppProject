package vn.edu.hcmuaf.FocusAppProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "component_score")
    private double componentScore;
    @Column(name = "score")
    private double score;
    @Column(name = "final_score_10")
    private double finalScore10;
    @Column(name = "final_score_4")
    private double finalScore4;
    @Column(name = "final_score_char")
    private double finalScoreChar;
    @Column(name = "result")
    private boolean result;
    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semesters semesters;
    @ManyToOne
    @JoinColumn(name = "user_course_id")
    UserCourse userCourse;
}
