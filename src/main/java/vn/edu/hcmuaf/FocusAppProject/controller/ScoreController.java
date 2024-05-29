package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.FocusAppProject.dto.UserSemesterDTO;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.ScoreServiceImp;

@RestController
@RequestMapping("/api/diem")
public class ScoreController {
    @Autowired
    private ScoreServiceImp scoreService;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> createScore(@RequestBody @Valid UserSemesterDTO userSemesterDTO) throws Exception {
        try {
            scoreService.createScore(userSemesterDTO);
            return ResponseEntity.ok("Create score successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-score")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getScore(@RequestParam long userId) {
        try {
            return ResponseEntity.ok(scoreService.getScore(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
