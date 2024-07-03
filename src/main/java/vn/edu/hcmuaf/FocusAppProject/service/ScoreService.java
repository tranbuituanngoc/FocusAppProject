package vn.edu.hcmuaf.FocusAppProject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.dto.ScoreDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.SemesterScoreDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserSemesterDTO;
import vn.edu.hcmuaf.FocusAppProject.exception.ScoreNotValidException;
import vn.edu.hcmuaf.FocusAppProject.models.*;
import vn.edu.hcmuaf.FocusAppProject.repository.*;
import vn.edu.hcmuaf.FocusAppProject.response.ScoreResponse;
import vn.edu.hcmuaf.FocusAppProject.response.SemesterScoreResponse;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.ScoreServiceImp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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


    @Override
    public List<SemesterScoreResponse> getScore(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User " + userId + " not found"));
        List<UserSemesters> userSemesters = userSemesterRepository.findByUserId(user.getId());
        List<SemesterScoreResponse> semesterScoreResponses = new ArrayList<>();
        for (UserSemesters userSemester : userSemesters) {
            if (userSemester.getSemester() != null) {
                List<UserCourse> userCourses = userCourseRepository.findByUserSemestersId(userSemester.getId());
                List<ScoreResponse> scoreDTOS = new ArrayList<>();
                for (UserCourse userCourse : userCourses) {
                    Score scores = scoreRepository.findByUserCourseId(userCourse.getId());
                    ScoreResponse scoreResponse = ScoreResponse.builder()
                            .courseId(userCourse.getCourse().getId())
                            .courseName(userCourse.getCourse().getCourseName())
                            .credit(userCourse.getCourse().getCredits())
                            .componentScore(scores.getComponentScore())
                            .score(scores.getScore())
                            .finalScore10(scores.getFinalScore10())
                            .finalScore4(scores.getFinalScore4())
                            .finalScoreChar(scores.getFinalScoreChar())
                            .result(scores.isResult())
                            .build();
                    scoreDTOS.add(scoreResponse);
                }
                SemesterScoreResponse semesterScoreResponse = SemesterScoreResponse.builder()
                        .semesterId(userSemester.getSemester().getSemesterId())
                        .semesterName(userSemester.getSemester().getSemesterName())
                        .gpa4(userSemester.getGpa4())
                        .cumulativeGpa4(userSemester.getCumulativeGpa4())
                        .gpa10(userSemester.getGpa10())
                        .cumulativeGpa10(userSemester.getCumulativeGpa10())
                        .cumulativeCredit(userSemester.getCumulativeCredit())
                        .creditHours(userSemester.getCreditHours())
                        .scores(scoreDTOS)
                        .build();
                semesterScoreResponses.add(semesterScoreResponse);
            } else {
                throw new RuntimeException("Semester not found");
            }
        }
        return semesterScoreResponses;
    }

    @Override
    public SemesterScoreResponse caculateRequireScore(long userId) throws ScoreNotValidException {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User " + userId + " not found"));
        LocalDate currentDate = LocalDate.now();
        int semesterId = semesterRepository.findCurrentSemesterIdsByDate(currentDate);
        List<Course> currentCourses = userCourseRepository.findCoursesBySemesterIdAndUserIdOrderByCourseCreditDESC(semesterId, userId);
        Optional<UserSemesters> currentUserSemester = userSemesterRepository.findByUserIdAndSemesterSemesterId(userId, semesterId);
        Semester currentSemester = semesterRepository.findById(semesterId).orElseThrow(() -> new RuntimeException("Semester " + semesterId + " not found"));

        double[] scoreRange = {4, 3.5, 3, 2.5, 2, 1.5, 1};
        double tolerance = 0.1;
        double[] credits = currentCourses.stream().mapToDouble(Course::getCredits).toArray();


        if (currentUserSemester == null) {
            throw new RuntimeException("User semester not found");
        }

        int totalCredit = currentUserSemester.get().getCumulativeCredit();
        int currentCredit = 0;

        for (Course course : currentCourses) {
            totalCredit += course.getCredits();
            currentCredit += course.getCredits();
        }

        double requireGpa = calculateRequireGpa(totalCredit, currentUserSemester.get().getCumulativeGpa4(), currentCredit, user.getDesiredScore(), currentUserSemester.get().getCumulativeCredit());
        if (requireGpa > 4.0) {
            throw new ScoreNotValidException("Điểm cần đạt của bạn lớn hơn 4.0. Vui lòng giảm điểm mong muốn xuống để thực hiện tính toán lại.");
        }
        requireGpa = Math.round(requireGpa * 100.0) / 100.0;

        double target = requireGpa * currentCredit;

        double[] scoresRequire = new double[credits.length];
        double[] previousScoresRequire = new double[credits.length];
        int index = fillScores(scoresRequire, scoreRange, credits, target);
        scoresRequire = calculateRequiredScores(scoresRequire, credits, target, scoreRange, tolerance, index, previousScoresRequire);
        List<ScoreResponse> scoreResponses = new ArrayList<>();
        for (int i = 0; i < scoresRequire.length; i++) {
            Course course = currentCourses.get(i);
            double score = scoresRequire[i];
            ScoreResponse scoreResponse = ScoreResponse.builder()
                    .courseId(course.getId())
                    .courseName(course.getCourseName())
                    .credit(course.getCredits())
                    .finalScore4(score)
                    .result(true)
                    .build();
            scoreResponses.add(scoreResponse);
        }
        SemesterScoreResponse semesterScoreResponse = SemesterScoreResponse.builder()
                .semesterId(currentSemester.getSemesterId())
                .semesterName(currentSemester.getSemesterName())
                .gpa4(requireGpa)
                .cumulativeGpa4(user.getDesiredScore())
                .cumulativeCredit(totalCredit)
                .creditHours(currentCredit)
                .scores(scoreResponses)
                .build();

        return semesterScoreResponse;
    }


    private double[] calculateRequiredScores(double[] scoresRequire, double[] credits, double target, double[] scoreRange, double tolerance, int index, double[] previous_scoresRequire) {
        double old_sum = Double.POSITIVE_INFINITY;
        for (int i = 0; i < scoresRequire.length; i++) {
            scoresRequire[i] = index == 0 ? scoreRange[index] : scoreRange[index - 1];
            double sum = calculateSum(credits, scoresRequire);
            if (sum >= target - 1 && sum < target) {
                for (int j = scoresRequire.length - 1; j > i; j--) {
                    scoresRequire[j] = index == 0 ? scoreRange[index] : scoreRange[index - 1];
                    double sum2 = calculateSum(credits, scoresRequire);
                    if (Math.abs(sum2 - target) <= tolerance) {
                        return scoresRequire;
                    }
                    if (sum2 > old_sum) {
                        return previous_scoresRequire;
                    }
                    old_sum = sum2;
                    System.arraycopy(scoresRequire, 0, previous_scoresRequire, 0, scoresRequire.length);
                }
            } else {
                if (sum > target) {
                    for (int j = scoresRequire.length - 1; j > i; j--) {
                        System.arraycopy(scoresRequire, 0, previous_scoresRequire, 0, scoresRequire.length);
                        scoresRequire[j] = index == scoresRequire.length ? scoreRange[index] : scoreRange[index + 1];
                        double sum2 = calculateSum(credits, scoresRequire);
                        if(sum2>target){
                            if (Math.abs(sum2 - target) <= tolerance) {
                                printArray(scoresRequire);
                                return scoresRequire;
                            }
                        }else{
                            break;
                        }
                    }

                        printArray(previous_scoresRequire);
                        return previous_scoresRequire;

                }
            }
        }
        return scoresRequire;
    }

    private int fillScores(double[] scoresRequire, double[] values, double[] credits, double target) {
        for (int i = 0; i < values.length; i++) {
            Arrays.fill(scoresRequire, values[i]);
            double sum = calculateSum(credits, scoresRequire);
            if (sum < target) {
                printArray(scoresRequire);
                return i;
            }
        }
        return scoresRequire.length;
    }

    private void printArray(double[] variants) {
        for (double v : variants) {
            System.out.print(v + "\t");
        }
        System.out.print("\n");
    }


    private double calculateSum(double[] credits, double[] scoresRequire) {
        double sum = 0;
        for (int i = 0; i < credits.length; i++) {
            sum += credits[i] * scoresRequire[i];
        }
        return sum;
    }


    private double calculateRequireGpa(int totalCredit, double cumulateGpa, int currentCredit, double desireGpa, int cumulateCredit) {
        double result = 0.0;
        try {
            result = (desireGpa * totalCredit - cumulateGpa * cumulateCredit) / currentCredit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
