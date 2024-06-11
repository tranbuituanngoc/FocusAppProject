package vn.edu.hcmuaf.FocusAppProject.controller;

import com.nimbusds.jose.util.Pair;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.FocusAppProject.dto.UserSemesterDTO;
import vn.edu.hcmuaf.FocusAppProject.exception.ScoreNotValidException;
import vn.edu.hcmuaf.FocusAppProject.response.SemesterScoreResponse;
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

    @GetMapping("/get-desired-score")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getDesiredScore(@RequestParam long userId) {
        try {
            SemesterScoreResponse response = scoreService.caculateRequireScore(userId);
            Pair<String, SemesterScoreResponse> successPair = Pair.of("success", response);
            return ResponseEntity.ok(successPair);
        } catch (ScoreNotValidException e) {
            Pair<String, String> errorPair = Pair.of("error", e.getMessage());
            return ResponseEntity.ok(errorPair);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
