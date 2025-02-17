package vn.edu.hcmuaf.FocusAppProject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.Util.EmailUtil;
import vn.edu.hcmuaf.FocusAppProject.dto.CourseScheduleDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserScheduleDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.WeekDTO;
import vn.edu.hcmuaf.FocusAppProject.exception.DataNotFoundException;
import vn.edu.hcmuaf.FocusAppProject.models.*;
import vn.edu.hcmuaf.FocusAppProject.repository.*;
import vn.edu.hcmuaf.FocusAppProject.response.CourseScheduleResponse;
import vn.edu.hcmuaf.FocusAppProject.response.WeekResponse;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.CourseScheduleServiceImp;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EmailUtil emailUtil;

    @Override
    @Transactional
    public void createUserSchedule(UserScheduleDTO userScheduleDTO) throws Exception {
        for (WeekDTO week : userScheduleDTO.getWeeks()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.parse(week.getStartDate(), formatter);
            LocalDate endDate = LocalDate.parse(week.getEndDate(), formatter);
            Integer weekSemester = semesterRepository.findCurrentSemesterIdsByDate(endDate);
            Semester semester = semesterRepository.findById(weekSemester).orElseThrow(() -> new Exception("Semester " + weekSemester + " not found"));
            if (!weekRepository.existsBySemesterWeekAndStartDateAndEndDate(week.getSemesterWeek(), startDate, endDate)) {
                weekRepository.save(new Week().builder()
                        .semesterWeek(week.getSemesterWeek())
                        .startDate(startDate)
                        .endDate(endDate)
                        .semester(semester)
                        .build());
            }
            if (!week.getCourseSchedules().isEmpty()) {
                for (CourseScheduleDTO courseScheduleDTO : week.getCourseSchedules()) {
                    UserSemesters userSemesters = userSemesterRepository.findByUserIdAndSemesterSemesterId(userScheduleDTO.getUserId(), userScheduleDTO.getSemesterId()).orElseThrow(() -> new Exception("Semester " + userScheduleDTO.getSemesterId() + " and user " + userScheduleDTO.getUserId() + " not found"));
                    UserCourse userCourse = userCourseRepository.findByUserIdAndCourseIdAndUserSemestersId(userScheduleDTO.getUserId(), courseScheduleDTO.getCourseId(), userSemesters.getId());

                    if (userCourse == null) {
                        throw new Exception("Course " + courseScheduleDTO.getCourseId() + " not found");
                    } else {
                        Week weekEntity = weekRepository.findBySemesterWeekAndStartDateAndEndDate(week.getSemesterWeek(), startDate, endDate);

                        if (!courseScheduleRepository.existsByUserCourseIdAndWeekId(userCourse.getId(), weekEntity.getId())) {
                            System.out.println("Insert CourseSchedule type 1 -----------------------------------------------------------------------------------");
                            courseScheduleRepository.save(new CourseSchedule().builder()
                                    .userCourse(userCourse)
                                    .week(weekEntity)
                                    .courseRoom(courseScheduleDTO.getCourseRoom())
                                    .dateNumType(courseScheduleDTO.getDateNumType())
                                    .studySlot(courseScheduleDTO.getStudySlot())
                                    .numOfLession(courseScheduleDTO.getNumOfLession())
                                    .practice(courseScheduleDTO.isPractice())
                                    .build());
                        } else {
                            List<CourseSchedule> existingCourseSchedules = courseScheduleRepository.findByUserCourseIdAndWeekId(userCourse.getId(), weekEntity.getId());

                            boolean hasTheory = existingCourseSchedules.stream().anyMatch(cs -> !cs.isPractice());
                            boolean hasPractice = existingCourseSchedules.stream().anyMatch(cs -> cs.isPractice());

                            if ((courseScheduleDTO.isPractice() && !hasPractice) || (!courseScheduleDTO.isPractice() && !hasTheory)) {
                                System.out.println("Insert CourseSchedule type 2 -----------------------------------------------------------------------------------");
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
    }

    @Override
    public WeekResponse getWeeksByCurrentSemester() throws DataNotFoundException {
        LocalDate currentDate = LocalDate.now();
        int semesterId = semesterRepository.findCurrentSemesterIdsByDate(currentDate);
        List<Week> weeks = weekRepository.findBySemesterSemesterId(semesterId);
        List<WeekDTO> weekDTOS = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-'Th'M-yyyy", new Locale("vi", "VN"));
        if (weeks != null) {
            Week currentWeek = weekRepository.findCurrentWeekByDate(currentDate);
            if (currentWeek == null) {
                throw new DataNotFoundException("Current week not found");
            } else {
                for (Week week : weeks) {
                    String startDate = week.getStartDate().format(formatter);
                    String endDate = week.getEndDate().format(formatter);
                    weekDTOS.add(new WeekDTO().builder()
                            .weekId(week.getId())
                            .startDate(startDate)
                            .endDate(endDate)
                            .build());
                }
                return WeekResponse.builder()
                        .weekId(currentWeek.getId())
                        .startDate(currentWeek.getStartDate().format(formatter))
                        .endDate(currentWeek.getEndDate().format(formatter))
                        .listWeek(weekDTOS)
                        .build();
            }
        } else {
            throw new DataNotFoundException("Weeks not found");
        }
    }

    @Override
    public CourseScheduleResponse getCourseSchedulesByWeekIdAndUserId(Long weekId, Long userId) throws DataNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User " + userId + " not found"));
        Week week = weekRepository.findById(weekId).orElseThrow(() -> new DataNotFoundException("Week " + weekId + " not found"));

        List<LocalDate> dates = new ArrayList<>();
        for (LocalDate date = week.getStartDate(); !date.isAfter(week.getEndDate()); date = date.plusDays(1)) {
            dates.add(date);
        }
        List<CourseSchedule> courseSchedules = courseScheduleRepository.findByUserIdAndWeekId(user.getId(), week.getId());
        courseSchedules.stream().forEach(courseSchedule -> {
            System.out.println(courseSchedule.getId());
        });
        List<CourseScheduleDTO> courseScheduleDTOS = new ArrayList<>();
        for (CourseSchedule courseSchedule : courseSchedules) {
            Course course = courseSchedule.getUserCourse().getCourse();
            courseScheduleDTOS.add(new CourseScheduleDTO().builder()
                    .courseId(course.getId())
                    .courseName(course.getCourseName())
                    .courseRoom(courseSchedule.getCourseRoom())
                    .dateNumType(courseSchedule.getDateNumType())
                    .numOfLession(courseSchedule.getNumOfLession())
                    .practice(courseSchedule.isPractice())
                    .studySlot(courseSchedule.getStudySlot())
                    .build());
        }
        return new CourseScheduleResponse().builder()
                .dates(dates)
                .listCourse(courseScheduleDTOS)
                .build();
    }

    @Override
    public void sendCourseNotifications(int studySlot) throws Exception {
        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue() + 1;
        if (dayOfWeekNumber == 8) {
            dayOfWeekNumber = 1;
        }
        List<CourseSchedule> courseSchedules = courseScheduleRepository.findCoursesByStudySlotAndDate(studySlot, currentDate, dayOfWeekNumber);
        for (CourseSchedule courseSchedule : courseSchedules) {
            String time = "";
            switch (courseSchedule.getStudySlot()) {
                case 1:
                    time = "7:00 - 9:30";
                    break;
                case 4:
                    time = "9:35 - 11:55";
                    break;
                case 7:
                    time = "12:15 - 14:45";
                    break;
                case 10:
                    time = "14:50 - 17:00";
                    break;
                default:
                    break;
            }
            User user = courseSchedule.getUserCourse().getUser();
            Course course = courseSchedule.getUserCourse().getCourse();
            Map<String, String> values = Map.of(
                    "user-name", user.getName(),
                    "course-name", course.getCourseName(),
                    "time", time,
                    "course-room", courseSchedule.getCourseRoom());
            String subject = courseSchedule.getCourseRoom() + " - " + course.getCourseName() + " - " + time;
            emailUtil.sendMail(user.getEmail(), subject, "announcement-course", values);
        }
    }

    @Override
    public List<CourseScheduleDTO> getCourseSchedulesByDateForUser(Long userId) throws DataNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User " + userId + " not found"));
        LocalDate currentDate = LocalDate.now();
        List<CourseSchedule> courseSchedules = courseScheduleRepository.findCourseSchedulesByUserIdAndDate(user.getId(), currentDate);
        if(courseSchedules!= null) {
            List<CourseScheduleDTO> courseScheduleDTOS = new ArrayList<>();
            for (CourseSchedule courseSchedule : courseSchedules) {
                Course course = courseSchedule.getUserCourse().getCourse();
                courseScheduleDTOS.add(new CourseScheduleDTO().builder()
                        .courseId(course.getId())
                        .courseName(course.getCourseName())
                        .courseRoom(courseSchedule.getCourseRoom())
                        .dateNumType(courseSchedule.getDateNumType())
                        .numOfLession(courseSchedule.getNumOfLession())
                        .practice(courseSchedule.isPractice())
                        .studySlot(courseSchedule.getStudySlot())
                        .build());
            }
            return courseScheduleDTOS;
        }
        return null;
    }

    @Scheduled(cron = "0 30 6 * * ?") // Run at 6:30AM
    public void sendCourseNotificationsSlot1() throws Exception {
        sendCourseNotifications(1);
    }

    @Scheduled(cron = "0 0 9 * * ?") // Run at 9:00AM
    public void sendCourseNotificationsSlot2() throws Exception {
        sendCourseNotifications(2);
    }

    @Scheduled(cron = "0 45 11 * * ?") // Run at 11:45AM
    public void sendCourseNotificationsSlot3() throws Exception {
        sendCourseNotifications(3);
    }

    @Scheduled(cron = "0 20 14 * * ?") // Run at 2:20PM
    public void sendCourseNotificationsSlot4() throws Exception {
        sendCourseNotifications(4);
    }
}
