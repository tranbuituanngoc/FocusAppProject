package vn.edu.hcmuaf.FocusAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Emai is required")
    private String email;
    @NotBlank(message = "Your name cant be blank")
    private String name;
    @NotBlank(message = "Password cant be blank")
    private String password;
    @JsonProperty("retype_password")
    private String retypePassword;
    private String provider;
    private String token;
    @JsonProperty("is_verify")
    private boolean isVerify;
    @JsonProperty("verification_code")
    private String verificationCode;
    @JsonProperty("time_valid")
    private Timestamp timeValid;
    private boolean status;
    @NotNull(message = "Role id is required")
    @JsonProperty("role_id")
    private int role_id;
    private String faculty;
    private String major;
    @JsonProperty("desired_score")
    private double desiredScore;
}
