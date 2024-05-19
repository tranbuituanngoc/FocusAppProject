package vn.edu.hcmuaf.FocusAppProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.dto.TestScheduleDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserTestScheduleDTO;
import vn.edu.hcmuaf.FocusAppProject.models.TestSchedule;
import vn.edu.hcmuaf.FocusAppProject.models.UserCourse;
import vn.edu.hcmuaf.FocusAppProject.models.UserSemesters;
import vn.edu.hcmuaf.FocusAppProject.repository.TestScheduleRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.UserCourseRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.UserSemesterRepository;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.TestScheduleServiceImp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class TestScheduleService implements TestScheduleServiceImp {
    @Autowired
    private UserCourseRepository userCourseRepository;
    @Autowired
    private UserSemesterRepository userSemesterRepository;
    @Autowired
    private TestScheduleRepository testScheduleRepository;

    @Override
    public void createOrUpdateTestSchedules(UserTestScheduleDTO userTestScheduleDTO) throws Exception {
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
