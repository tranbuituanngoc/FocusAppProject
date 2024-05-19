package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmuaf.FocusAppProject.dto.SemesterDetailDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserScheduleDTO;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.CourseScheduleServiceImp;

@RestController
@RequestMapping("/api/tkb")
public class ScheduleController {
    @Autowired
    private CourseScheduleServiceImp courseScheduleService;
    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> createOrUpdateSchedules(@RequestBody @Valid UserScheduleDTO userScheduleDTO) throws Exception {
        try {
            courseScheduleService.createOrUpdateUserSchedule(userScheduleDTO);
            return ResponseEntity.ok("Update Schedule successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
