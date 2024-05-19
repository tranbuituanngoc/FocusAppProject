package vn.edu.hcmuaf.FocusAppProject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.dto.CourseScheduleDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserScheduleDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.WeekDTO;
import vn.edu.hcmuaf.FocusAppProject.models.CourseSchedule;
import vn.edu.hcmuaf.FocusAppProject.models.UserCourse;
import vn.edu.hcmuaf.FocusAppProject.models.UserSemesters;
import vn.edu.hcmuaf.FocusAppProject.models.Week;
import vn.edu.hcmuaf.FocusAppProject.repository.*;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.CourseScheduleServiceImp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CourseScheduleService implements CourseScheduleServiceImp {
    @Autowired
    private WeekRepository weekRepository;
    @Autowired
    private CourseScheduleRepository courseScheduleRepository;
    @Autowired
    private UserSemesterRepository userSemesterRepository;
    @Autowired
    private UserCourseRepository userCourseRepository;

    @Override
    @Transactional
    public void createOrUpdateUserSchedule(UserScheduleDTO userScheduleDTO) throws Exception {
        for (WeekDTO week : userScheduleDTO.getWeeks()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.parse(week.getStartDate(), formatter);
            LocalDate endDate = LocalDate.parse(week.getEndDate(), formatter);
            Week weekEntity = Week.builder()
                    .semesterWeek(week.getSemesterWeek())
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();
            weekRepository.save(weekEntity);
            if (!week.getCourseSchedules().isEmpty()) {
                for (CourseScheduleDTO courseScheduleDTO : week.getCourseSchedules()) {
                    UserSemesters userSemesters = userSemesterRepository.findByUserIdAndSemesterSemesterId(userScheduleDTO.getUserId(), userScheduleDTO.getSemesterId()).orElseThrow(() -> new Exception("Semester " + userScheduleDTO.getSemesterId() + " and user " + userScheduleDTO.getUserId() + " not found"));
                    UserCourse userCourse = userCourseRepository.findByUserIdAndCourseIdAndUserSemestersId(userScheduleDTO.getUserId(), courseScheduleDTO.getCourseId(), userSemesters.getId());
                    if (userCourse == null) {
                        throw new Exception("Course " + courseScheduleDTO.getCourseId() + " not found");
                    } else {
                        courseScheduleRepository.save(new CourseSchedule().builder()
                                .userCourse(userCourse)
                                .week(weekEntity)
                                .courseRoom(courseScheduleDTO.getCourseRoom())
                                .dateNumType(courseScheduleDTO.getDateNumType())
                                .studySlot(courseScheduleDTO.getStudySlot())
                                .numOfLession(courseScheduleDTO.getNumOfLession())
                                .practice(courseScheduleDTO.isPractice())
                                .build());
                    }
                }
            }
        }
    }
}
