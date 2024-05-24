package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "achivements")
public class Achivement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "total_time")
    private long totalTime;
    @Column(name = "date")
    private LocalDate date;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
