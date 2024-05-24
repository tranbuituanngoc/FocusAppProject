package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "test_schedule")
public class TestSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_course_id")
    private UserCourse userCourse;
    @Column(name = "test_room")
    private String testRoom;
    @Column(name = "test_time")
    private LocalTime testTime;
    @Column(name = "test_date")
    private LocalDate testDate;
    @Column(name = "start_slot")
    private int startSlot;
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
