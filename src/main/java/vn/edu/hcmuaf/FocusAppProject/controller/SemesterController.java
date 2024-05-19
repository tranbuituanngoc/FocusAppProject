package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.FocusAppProject.dto.SemesterDetailDTO;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.SemesterServiceImp;

import java.util.List;

@RestController
@RequestMapping("/api/hocki")
public class SemesterController {
    @Autowired
    private SemesterServiceImp semesterService;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> createOrUpdateSemesters(@RequestBody List<@Valid SemesterDetailDTO> listSemester) throws Exception {
        try {
            for (SemesterDetailDTO semesterDetailDTO : listSemester) {
                semesterService.createOrUpdateSemester(semesterDetailDTO);
            }
            return ResponseEntity.ok("Create or update semesters successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/current")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> getCurrentSemester() throws Exception {
        try {
            Integer semesterId = semesterService.getCurrentSemester();
            return ResponseEntity.ok(semesterId + "");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
