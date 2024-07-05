package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.FocusAppProject.dto.CourseScheduleDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserScheduleDTO;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.CourseScheduleServiceImp;

import java.util.List;

@RestController
@RequestMapping("/api/tkb")
public class ScheduleController {
    @Autowired
    private CourseScheduleServiceImp courseScheduleService;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> createOrUpdateSchedules(@RequestBody @Valid UserScheduleDTO userScheduleDTO) throws Exception {
        try {
            courseScheduleService.createUserSchedule(userScheduleDTO);
            return ResponseEntity.ok("Update Schedule successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-weeks")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getWeeksByCurrentSemester() {
        try {
            return ResponseEntity.ok(courseScheduleService.getWeeksByCurrentSemester());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-schedule")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getScheduleByWeek(@RequestParam long userId, @RequestParam long weekId) {
        try {
            return ResponseEntity.ok(courseScheduleService.getCourseSchedulesByWeekIdAndUserId(weekId, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/get-schedule-by-date")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getScheduleByDate(@RequestParam long userId) {
        try {
          List<CourseScheduleDTO> courseScheduleDTOS= courseScheduleService.getCourseSchedulesByDateForUser(userId);
            if (courseScheduleDTOS == null || courseScheduleDTOS.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(courseScheduleDTOS);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
