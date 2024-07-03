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
@Entity(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "deadline")
    private LocalDate deadline;
    @Column(name = "content")
    private String content;
    @Column(name="title")
    private String title;
    @Column(name = "is_complete")
    private boolean isComplete;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
