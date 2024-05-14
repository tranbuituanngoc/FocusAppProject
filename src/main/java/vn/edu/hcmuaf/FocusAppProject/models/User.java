package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "email", length = 300, nullable = false)
    private String email;
    @Column(name = "name",length = 300)
    private String name;
    @Column(name = "password", length = 200, nullable = false)
    private String password;
    @Column(name = "provider")
    private String provider;
    @Column(name = "token")
    private String token;
    @Column(name = "is_verify")
    private boolean isVerify;
    @Column(name = "verification_code", length = 10, nullable = false)
    private String verificationCode;
    @Column(name = "time_valid")
    private Timestamp timeValid;
    @Column(name = "status")
    private boolean status;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roles;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @Column(name = "faculty")
    private String faculty;
    @Column(name = "major")
    private String major;
    @Column(name = "desired_score")
    private double desiredScore;

    @OneToMany(mappedBy = "users")
    List<Note> notes;
    @OneToMany(mappedBy = "user")
    List<Semesters> semesters;
    @OneToMany(mappedBy = "user")
    List<Achivement> achivements;
    @OneToMany(mappedBy = "user")
    List<Todo> todos;
    @OneToMany(mappedBy = "user")
    List<Project> projects;
    @OneToMany(mappedBy = "user")
    List<UserCourse> userCourses;
    @OneToOne(mappedBy = "user")
    LinkToDKMH linkToDKMH;

    public User(String email, String password, Role roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + getRoles().getRoleName().toUpperCase()));
        return authorityList;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }
}
