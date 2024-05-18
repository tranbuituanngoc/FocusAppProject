package vn.edu.hcmuaf.FocusAppProject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.dto.ScoreDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.SemesterScoreDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserSemesterDTO;
import vn.edu.hcmuaf.FocusAppProject.models.*;
import vn.edu.hcmuaf.FocusAppProject.repository.*;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.ScoreServiceImp;

import java.util.Optional;

@Service
public class ScoreService implements ScoreServiceImp {
    @Autowired
    private UserSemesterRepository userSemesterRepository;
    @Autowired
    private UserCourseRepository userCourseRepository;
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public void createScore(UserSemesterDTO userSemesterDTO) {
        for (SemesterScoreDTO semesterDTO : userSemesterDTO.getSemesters()) {
            Optional<UserSemesters> userSemesters = userSemesterRepository.findByUserIdAndSemesterSemesterId(userSemesterDTO.getUserId(), semesterDTO.getSemesterId());
            if (!userSemesters.isPresent()) {
                User user = userRepository.findById(userSemesterDTO.getUserId()).orElseThrow(() -> new RuntimeException("User " + userSemesterDTO.getUserId() + " not found"));
                Semester semester = semesterRepository.findById(semesterDTO.getSemesterId()).orElseThrow(() -> new RuntimeException("Semester " + semesterDTO.getSemesterId() + " not found"));

                // Create user semester
                UserSemesters uSemester = userSemesterRepository.save(UserSemesters.builder()
                        .user(user)
                        .semester(semester)
                        .creditHours(semesterDTO.getCreditHours() == null ? 0 : semesterDTO.getCreditHours())
                        .gpa4(semesterDTO.getGpa4() == null ? 0.0 : semesterDTO.getGpa4())
                        .gpa10(semesterDTO.getGpa10() == null ? 0.0 : semesterDTO.getGpa10())
                        .cumulativeGpa4(semesterDTO.getCumulativeGpa4() == null ? 0.0 : semesterDTO.getCumulativeGpa4())
                        .cumulativeGpa10(semesterDTO.getCumulativeGpa10() == null ? 0.0 : semesterDTO.getCumulativeGpa10())
                        .cumulativeCredit(semesterDTO.getCumulativeCredit() == null ? 0 : semesterDTO.getCumulativeCredit())
                        .build());


                for (ScoreDTO scoreDTO : semesterDTO.getScores()) {
                    Course course = courseRepository.findById(scoreDTO.getCourseId()).orElseThrow(() -> new RuntimeException("Course " + scoreDTO.getCourseId() + " not found"));
                    // Create user course
                    UserCourse userCourse = userCourseRepository.save(UserCourse.builder()
                            .course(course)
                            .userSemesters(uSemester)
                            .user(user)
                            .build());

                    // Create score
                    scoreRepository.save(Score.builder()
                            .userCourse(userCourse)
                            .score(scoreDTO.getScore() == null ? 0.0 : scoreDTO.getScore())
                            .componentScore(scoreDTO.getComponentScore() == null ? 0.0 : scoreDTO.getComponentScore())
                            .finalScore4(scoreDTO.getFinalScore4() == null ? 0.0 : scoreDTO.getFinalScore4())
                            .finalScore10(scoreDTO.getFinalScore10() == null ? 0.0 : scoreDTO.getFinalScore10())
                            .finalScoreChar(scoreDTO.getFinalScoreChar().isEmpty() ? "" : scoreDTO.getFinalScoreChar())
                            .result(scoreDTO.isResult())
                            .build());
                }
            }
        }
    }

}
