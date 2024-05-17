package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "practice")
    private boolean practice;
    @Column(name = "study_slot")
    private int studySlot;
    @Column(name = "date_num_type")
    private int dateNumType;
    @Column(name = "course_room")
    private String courseRoom;
    @Column(name = "study_week")
    private int study_week;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
