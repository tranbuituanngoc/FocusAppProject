package vn.edu.hcmuaf.FocusAppProject.models;

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
@Entity(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "deadline")
    private LocalDate deadline;
    @Column(name = "content")
    private String content;
    @Column(name = "is_complete")
    private boolean isComplete;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
