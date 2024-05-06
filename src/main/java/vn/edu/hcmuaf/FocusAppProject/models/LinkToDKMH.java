package vn.edu.hcmuaf.FocusAppProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "link_to_dkmh")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkToDKMH extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "mssv", nullable = false, length = 8)
    private String mssv;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "access_token")
    private String accessToken;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
