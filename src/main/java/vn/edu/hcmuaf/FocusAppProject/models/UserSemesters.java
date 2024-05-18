package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "user_semesters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSemesters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "gpa_4")
    private double gpa4;
    @Column(name = "cumulative_gpa_4")
    private double cumulativeGpa4;
    @Column(name = "gpa_10")
    private double gpa10;
    @Column(name = "cumulative_gpa_10")
    private double cumulativeGpa10;
    @Column(name = "cumulative_credit")
    private int cumulativeCredit;
    @Column(name = "credit_hours")
    private int creditHours;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "semester_id")
    private Semester semester;
    @JsonBackReference
    @OneToMany(mappedBy = "userSemesters")
    Set<UserCourse> userCourses;

}
