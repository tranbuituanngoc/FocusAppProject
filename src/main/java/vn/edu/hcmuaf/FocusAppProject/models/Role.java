package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "user_permission")
    private boolean userPermission;
    @Column(name = "role_permission")
    private boolean rolePermission;
    @JsonBackReference
    @OneToMany(mappedBy = "roles")
    Set<User> listUser;
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
