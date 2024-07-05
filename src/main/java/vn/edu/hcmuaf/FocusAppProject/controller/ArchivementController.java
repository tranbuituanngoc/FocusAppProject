package vn.edu.hcmuaf.FocusAppProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.FocusAppProject.dto.AchievementDTO;
import vn.edu.hcmuaf.FocusAppProject.service.AchievementService;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.AchievementServiceImp;

@RestController
@RequestMapping("/api/archivement")
public class ArchivementController {
    @Autowired
    private AchievementServiceImp achievementService;

    @GetMapping("/total-time")
    @PreAuthorize("IsAuthenticated()")
    public ResponseEntity<?> getTotalTime(@RequestParam("userId") long userId){
        try {
            return ResponseEntity.ok(achievementService.getTotalTime(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update-total-time")
    @PreAuthorize("IsAuthenticated()")
    public ResponseEntity<?> updateTotalTime(@RequestBody AchievementDTO achievementDTO){
        try {
            return ResponseEntity.ok(achievementService.updateTotalTime(achievementDTO.getUserId(), achievementDTO.getTotalTime()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
