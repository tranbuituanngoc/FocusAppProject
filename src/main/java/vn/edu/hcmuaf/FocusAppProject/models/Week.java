package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "weeks")
public class Week {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="semester_week")
    private int semesterWeek;
    @Column(name="start_date")
    private LocalDate startDate;
    @Column(name="end_date")
    private LocalDate endDate;
    @JsonBackReference
    @OneToMany(mappedBy = "week")
    private List<CourseSchedule> courseSchedules;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
