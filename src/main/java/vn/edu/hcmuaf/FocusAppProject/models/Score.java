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
    @Column(name = "expected_score_coefficient_4")
    private double expectedScoreCoefficient4;
    @Column(name = "actual_score_coefficient_4")
    private double actualScoreCoefficient4;
    @Column(name = "expected_score_coefficient_10")
    private double expectedScoreCoefficient10;
    @Column(name = "actual_score_coefficient_10")
    private double actualScoreCoefficient10;
    @Column(name = "classification")
    private String classification;
    @ManyToOne
    @JoinColumn(name = "user_course_id")
    UserCourse userCourse;
}
