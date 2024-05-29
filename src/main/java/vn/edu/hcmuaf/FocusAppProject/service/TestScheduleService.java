package vn.edu.hcmuaf.FocusAppProject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.dto.TestScheduleDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserTestScheduleDTO;
import vn.edu.hcmuaf.FocusAppProject.models.TestSchedule;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import vn.edu.hcmuaf.FocusAppProject.models.UserCourse;
import vn.edu.hcmuaf.FocusAppProject.models.UserSemesters;
import vn.edu.hcmuaf.FocusAppProject.repository.*;
import vn.edu.hcmuaf.FocusAppProject.response.TestScheduleResponse;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.TestScheduleServiceImp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestScheduleService implements TestScheduleServiceImp {
    @Autowired
    private UserCourseRepository userCourseRepository;
    @Autowired
    private UserSemesterRepository userSemesterRepository;
    @Autowired
    private TestScheduleRepository testScheduleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    @Transactional
    public void createTestSchedules(UserTestScheduleDTO userTestScheduleDTO) throws Exception {
        for (TestScheduleDTO testScheduleDTO : userTestScheduleDTO.getTestSchedules()) {
            UserSemesters userSemesters = userSemesterRepository.findByUserIdAndSemesterSemesterId(userTestScheduleDTO.getUserId(), userTestScheduleDTO.getSemesterId()).orElseThrow(() -> new Exception("Semester " + userTestScheduleDTO.getSemesterId() + " and user " + userTestScheduleDTO.getUserId() + " not found"));
            UserCourse userCourse = userCourseRepository.findByUserIdAndCourseIdAndUserSemestersId(userTestScheduleDTO.getUserId(), testScheduleDTO.getCourseId(), userSemesters.getId());
            if (userCourse == null) {
                throw new Exception("Course " + testScheduleDTO.getCourseId() + " not found");
            } else {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate testDate = LocalDate.parse(testScheduleDTO.getTestDate(), dateFormatter);

                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime testTime = LocalTime.parse(testScheduleDTO.getTestTime(), timeFormatter);

                TestSchedule existingTestSchedule = testScheduleRepository.findByUserCourseIdAndTestDateAndTestTime(userCourse.getId(), testDate, testTime);
                if (existingTestSchedule == null) {
                    System.out.println("Insert TestSchedule -----------------------------------------------------------------------------------");
                    testScheduleRepository.save(new TestSchedule().builder()
                            .userCourse(userCourse)
                            .testDate(testDate)
                            .testRoom(testScheduleDTO.getTestRoom())
                            .startSlot(testScheduleDTO.getStartSlot())
                            .testTime(testTime)
                            .build());
                }
            }
        }
    }

    @Override
    public List<TestScheduleResponse> getCurrentTestSchedulesByUserId(long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
        LocalDate currentDate = LocalDate.now();
        int semesterId = semesterRepository.findCurrentSemesterIdsByDate(currentDate);
        UserSemesters currentUserSemester = userSemesterRepository.findByUserIdAndSemesterSemesterId(user.getId(), semesterId).orElseThrow(() -> new Exception("User semester not found"));
        List<UserCourse> userCourses = userCourseRepository.findByUserSemestersId(currentUserSemester.getId());
        List<TestScheduleResponse> testScheduleResponses = new ArrayList<>();
        for (UserCourse userCourse : userCourses) {
            System.out.println("UserCourse: " + userCourse.getId());
            List<TestSchedule> testSchedules = testScheduleRepository.findByUserCourseId(userCourse.getId());
            for (TestSchedule testSchedule : testSchedules) {
                System.out.println(testSchedule.getTestDate() + " " + testSchedule.getTestTime() + " " + testSchedule.getTestRoom() + " " + testSchedule.getStartSlot());
                testScheduleResponses.add(new TestScheduleResponse().builder()
                        .courseId(userCourse.getCourse().getId())
                        .courseId(userCourse.getCourse().getId())
                        .courseName(userCourse.getCourse().getCourseName())
                        .testDate(testSchedule.getTestDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .testRoom(testSchedule.getTestRoom())
                        .testSlot(testSchedule.getStartSlot())
                        .testTime(testSchedule.getTestTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                        .build());
            }
        }
        return testScheduleResponses;
    }
}
