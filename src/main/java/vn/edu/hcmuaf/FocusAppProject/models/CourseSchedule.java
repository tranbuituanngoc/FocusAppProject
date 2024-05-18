package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "course_schedule")
public class CourseSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "is_practice")
    private boolean practice;
    @Column(name = "study_slot")
    private int studySlot;
    @Column(name = "date_num_type")
    private int dateNumType;
    @Column(name = "course_room")
    private String courseRoom;
    @Column(name = "num_of_lession")
    private int numOfLession;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_course_id")
    private UserCourse userCourse;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "week_id")
    private Week week;

}
