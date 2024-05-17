package vn.edu.hcmuaf.FocusAppProject.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkToDKMHResponse {
    private String mssv;
    private LocalDateTime expires;
}
