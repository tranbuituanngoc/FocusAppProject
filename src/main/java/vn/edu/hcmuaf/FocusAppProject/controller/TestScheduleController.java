package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.FocusAppProject.dto.UserTestScheduleDTO;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.TestScheduleServiceImp;

@RestController
@RequestMapping("/api/lich-thi")
public class TestScheduleController {
    @Autowired
    private TestScheduleServiceImp testScheduleService;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> createOrUpdateSchedules(@RequestBody @Valid UserTestScheduleDTO userTestScheduleDTO) throws Exception {
        try {
            testScheduleService.createTestSchedules(userTestScheduleDTO);
            return ResponseEntity.ok("Update Test Schedule successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/get-test-schedule")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getTestSchedule(@RequestParam long userId) {
        try {
            return ResponseEntity.ok(testScheduleService.getCurrentTestSchedulesByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
