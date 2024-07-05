package vn.edu.hcmuaf.FocusAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {
    private int id;
    @JsonProperty("role_name")
    private String roleName;
    @JsonProperty("user_permission")
    private boolean userPermission;
    @JsonProperty("role_permission")
    private boolean rolePermission;
}
