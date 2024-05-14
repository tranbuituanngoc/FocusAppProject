package vn.edu.hcmuaf.FocusAppProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @Column(name = "access_token", nullable = false, length = 1000)
    private String accessToken;
    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;
    @Column(name = "token_type", nullable = false)
    private String tokenType;
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;
}
