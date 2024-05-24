package vn.edu.hcmuaf.FocusAppProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.FocusAppProject.dto.CourseSuggestDTO;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.CourseServiceImp;

@RestController
@RequestMapping("/api/course-suggest")
public class CourseSuggestController {
    @Autowired
    private CourseServiceImp courseService;

    @GetMapping("/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getCourseForNextSemester(@PathVariable long userId) throws Exception {
        try {
            return ResponseEntity.ok(courseService.getCourseForNextSemester(userId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getImprove/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getListCourseNotifyToImrove(@PathVariable long userId) throws Exception {
        try {
            return ResponseEntity.ok(courseService.getNotifyForCoursesToImprove(userId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/handle-course-suggest")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> handleCourseSuggest(@RequestBody CourseSuggestDTO courseSuggestDTO) throws Exception {
        try {
            return ResponseEntity.ok( courseService.handleCourseSuggest(courseSuggestDTO));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
