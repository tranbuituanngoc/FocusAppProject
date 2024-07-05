package vn.edu.hcmuaf.FocusAppProject.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserResponse {
    private long id;
    private String name;
    private String department;
    @JsonProperty("type_account")
    private String typeAccount;
    private String verify;
}
