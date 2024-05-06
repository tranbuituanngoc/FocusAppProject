package vn.edu.hcmuaf.FocusAppProject.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Column(name = "issued")
    private LocalDateTime issued;
    @Column(name = "expires")
    private LocalDateTime expires;

    @PrePersist
    protected void onCreate() {
        issued = LocalDateTime.now();
        expires = issued.plusMinutes(30);
    }

    @PreUpdate
    protected void onUpdate() {
        issued = LocalDateTime.now();
        expires = issued.plusMinutes(30);
    }
}
