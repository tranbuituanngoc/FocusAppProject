package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;
    @Column(name = "user_permission")
    private boolean userPermission;
    @Column(name = "role_permission")
    private boolean rolePermission;
    @Column(name = "is_delete")
    private boolean isDelete;

    @JsonBackReference
    @OneToMany(mappedBy = "roles")
    Set<User> listUser;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
