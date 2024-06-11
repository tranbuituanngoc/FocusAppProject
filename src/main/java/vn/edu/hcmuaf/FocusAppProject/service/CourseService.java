package vn.edu.hcmuaf.FocusAppProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.dto.CourseDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.CourseSuggestDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.NotifyDTO;
import vn.edu.hcmuaf.FocusAppProject.models.*;
import vn.edu.hcmuaf.FocusAppProject.repository.*;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.CourseServiceImp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService implements CourseServiceImp {
    @Autowired
    private UserCourseRepository userCourseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private SemesterCourseRepository semesterCourseRepository;
    @Autowired
    private CourseGroupCourseRepository courseGroupCourseRepository;
    @Autowired
    private CourseGroupRepository courseGroupRepository;
    @Autowired
    private TrainingProgramSemesterRepository trainingProgramSemesterRepository;
    @Autowired
    private UserSemesterRepository userSemesterRepository;

    @Override
    public List<CourseDTO> getPassedCoursesByUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Course> courses = userCourseRepository.findPassedCoursesByUser(user);
        return getCourseDTOS(courses);
    }

    @Override
    public List<CourseDTO> getPrerequisiteCourses(int courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        List<Course> courses = course.getPrerequisites();
        return getCourseDTOS(courses);
    }

    @Override
    public List<CourseDTO> getCourseForNextSemester(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        lấy học kỳ hiện tại
        LocalDate currentDate = LocalDate.now();
        String semesterId = semesterRepository.findCurrentSemesterIdsByDate(currentDate) + "";
//       lấy danh sách môn học đã qua
        List<Course> passedCourses = userCourseRepository.findPassedCoursesByUser(user);
//        lấy danh sách môn học sẽ mở trong học kỳ tiếp theo
        List<Course> nextSemesterCourses = new ArrayList<>();
        if (semesterId.endsWith("1")) {
            nextSemesterCourses = semesterCourseRepository.findCoursesBySemesterIdEndingWith("2", user.getTrainingProgram().getId());
        } else if (semesterId.endsWith("2") || semesterId.endsWith("3")) {
            nextSemesterCourses = semesterCourseRepository.findCoursesBySemesterIdEndingWith("1", user.getTrainingProgram().getId());
        }
//        lọc các môn chưa học và đã được hoàn thành các môn yêu cầu sẽ mở trong học kỳ tiếp theo
        List<Course> availableCourses = nextSemesterCourses.stream()
                .filter(course -> !passedCourses.contains(course) &&
                        passedCourses.containsAll(course.getPrerequisites()) &&
                        (course.getId() + "").length() == 6)
                .collect(Collectors.toList());
        return getCourseDTOS(availableCourses);
    }

    @Override
    public List<NotifyDTO> handleCourseSuggest(CourseSuggestDTO courseSuggestDTO) {
        List<NotifyDTO> notifyDTOS = new ArrayList<>();
        if (isDone(courseSuggestDTO.getUserId())) {
            NotifyDTO notifyDTO = new NotifyDTO().builder()
                    .title("Chúc mừng bạn đã hoàn thành chương trình đào tạo")
                    .message("Chúc mừng bạn đã hoàn thành hết chương trình đào tạo của mình. Hãy cố gắng trong các môn học cuối cùng để có được một chiếc bằng đại học thật đẹp nhé.")
                    .type("info")
                    .build();
            notifyDTOS.add(notifyDTO);
            return notifyDTOS;
        }
        User user = userRepository.findById(courseSuggestDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        int khoaLuanId = courseRepository.findCourseByNameAndTrainingProgramId("Khoa luận tốt nghiệp", user.getTrainingProgram().getId()).getId();
        int tieuLuanId = courseRepository.findCourseByNameAndTrainingProgramId("Tiểu luận tốt nghiệp", user.getTrainingProgram().getId()).getId();

//        lấy ra thông báo các môn cần học trong ki  tiếp theo
        List<NotifyDTO> notifyCourseNeed = checkCourseGroupNeedStudy(courseSuggestDTO.getUserId(), courseSuggestDTO);
        notifyDTOS.addAll(notifyCourseNeed);

        List<NotifyDTO> notifyNeedImprove = getNotifyForCoursesToImprove(courseSuggestDTO.getUserId());
        notifyDTOS.addAll(notifyNeedImprove);

        int year = Integer.parseInt("20" + user.getEmail().substring(0, 2));
        int currentYear = LocalDate.now().getYear();
        int totalCredits = 0;

        for (CourseDTO courseDTO : courseSuggestDTO.getListCourse()) {
            totalCredits += courseDTO.getCredits();
//            kiểm tra xem môn học có phù hợp với khả năng của sinh viên không
            if (courseDTO.getCourseId() == khoaLuanId) {
                if (currentYear - year < 3) {
                    NotifyDTO notifyDTO = new NotifyDTO().builder()
                            .title("Cảnh báo chọn môn học")
                            .message("Môn học có thể vượt quá khả năng của bạn. Bạn nên cân nhắc chọn môn học khác thay vì chọn làm Khóa luận tốt nghiệp.")
                            .type("danger")
                            .build();
                    if (notifyDTOS.isEmpty()) {
                        notifyDTOS.add(notifyDTO);
                    } else {
                        notifyDTOS.add(1, notifyDTO);
                    }
                }
            }
            if (courseDTO.getCourseId() == tieuLuanId) {
                if (currentYear - year < 3) {
                    NotifyDTO notifyDTO = new NotifyDTO().builder()
                            .title("Cảnh báo chọn môn học")
                            .message("Môn học có thể vượt quá khả năng của bạn. Bạn nên cân nhắc chọn môn học khác thay vì chọn làm Tiểu luận tốt nghiệp.")
                            .type("danger")
                            .build();
                    if (notifyDTOS.isEmpty()) {
                        notifyDTOS.add(notifyDTO);
                    } else {
                        notifyDTOS.add(1, notifyDTO);
                    }
                }
            }
//            kiểm tra xem môn học đã chọn có làm dư tín chỉ của các nhóm học phần không
            if (checkMinCreditsForCourseGroup(courseSuggestDTO.getUserId(), courseDTO.getCourseId()) == 1) {
                CourseGroup courseGroup = courseGroupCourseRepository.findCourseGroupByCourseId(courseDTO.getCourseId(), user.getTrainingProgram().getId());
                NotifyDTO notifyDTO = new NotifyDTO().builder()
                        .title(courseGroup.getName() + " đã đủ tín chỉ tối thiểu")
                        .message("Bạn đã đủ tín chỉ tối thiểu của " + courseGroup.getName() + ". Bạn nên chọn các môn học khác thuộc nhóm học phần mà mình đang còn thiếu tín chỉ.")
                        .type("warning")
                        .build();
                if (!notifyDTOS.contains(notifyDTO)) {
                    notifyDTOS.add(notifyDTO);
                }
            }
//            Kiểm tra xem môn học được chọn đã được học lại hay chưa nếu đã chọn thì xóa thông báo liên quan
            if (getFailedCourse(courseSuggestDTO.getUserId()).stream().anyMatch(course -> course.getId() == courseDTO.getCourseId())) {
                notifyDTOS.removeIf(notifyDTO -> notifyDTO.getTitle().contains("Cân nhắc học cải thiện môn " + courseDTO.getCourseName()));
            }
        }
        if (courseSuggestDTO.getTypeSuggestion() == 1) {
            int remainSemester = getRemainSemester(courseSuggestDTO.getUserId());
            int remainCredits = getRemainCredits(courseSuggestDTO.getUserId());

            if (remainSemester > 0) {
                int totalCreditsNeed = remainCredits / remainSemester;
                NotifyDTO notifyDTO;
                if (totalCreditsNeed > 25) {
                    notifyDTO = new NotifyDTO().builder()
                            .title("Cảnh báo không thể đề xuất môn học theo thời gian")
                            .message("Bạn có thể bạn sẽ không kịp hoàn thành chương trình đào tạo trong 4 năm. Bạn vui lòng chọn kiểu gợi ý theo học lực để tôi có thể gợi ý cho bạn các chỉ dẫn phù hợp.")
                            .type("danger")
                            .build();
                    notifyDTOS.add(0, notifyDTO);
                } else {
                    notifyDTO = new NotifyDTO().builder()
                            .title("Thông báo số tín chỉ cần đạt")
                            .message("Bạn cần học thêm <b style=\"color: var(--primary-lightred)\">" + totalCreditsNeed + "</b> tín chỉ mỗi học kỳ để hoàn thành chương trình đào tạo trong thời gian 4 năm.")
                            .type("info")
                            .build();
                    notifyDTOS.add(notifyDTO);
                }

            }
            if (remainSemester == 0) {
                NotifyDTO notifyDTO = new NotifyDTO().builder()
                        .title("Cảnh báo không thể đề xuất môn học theo thời gian")
                        .message("Bạn đã không thể hoàn thành chương trình đào tạo trong 4 năm. Bạn vui lòng chọn kiểu gợi ý theo học lực để có thể có được các chỉ dẫn phù hợp.")
                        .type("danger")
                        .build();
                notifyDTOS.add(0, notifyDTO);
            }
        }
        if (courseSuggestDTO.getTypeSuggestion() == 2) {
            notifyDTOS.removeIf(notifyDTO -> notifyDTO.getTitle().contains("Cảnh báo không thể đề xuất môn học theo thời gian"));
            String academicPerformance = getCurrentAcademicPerformance(courseSuggestDTO.getUserId());
            String disireAcademicPerformance = getDisireAcademicPerform(courseSuggestDTO.getUserId());
            if (academicPerformance.equals("Yếu")) {
                NotifyDTO notifyDTO = new NotifyDTO().builder()
                        .title("Đề xuất tín chỉ cần đạt")
                        .message("Học lực của bạn hiện tại đang ở mức <b>" + academicPerformance + "</b>. Bạn cần cải thiện học lực của mình để có thể hoàn thành chương trình đào tạo bằng cách chỉ nên học <b> 14</b> tín chỉ trong học kì này và tập trung vào việc học cải thiện các môn đã rớt trước đó.")
                        .type("danger")
                        .build();
                if (notifyDTOS.isEmpty()) {
                    notifyDTOS.add(notifyDTO);
                } else {
                    notifyDTOS.add(1, notifyDTO);
                }
            } else if (academicPerformance.equals("Trung bình")) {
                NotifyDTO notifyDTO = new NotifyDTO().builder()
                        .title("Đề xuất tín chỉ cần đạt")
                        .message("Học lực của bạn hiện tại đang ở mức <b>" + academicPerformance + "</b>. Có vẻ như bạn cần thêm thời gian để cải thiện và củng cố kiến thức của mình. Vì thế tôi khuyến nghị bạn chỉ nên học <b>14-17</b> tín chỉ trong học kì này và hãy tập trung củng cố lại các kiến thức cố lõi trong ngành " + user.getDepartment().getDepartmentName() + " để từ đó có thể phát triển hơn ở các kỳ sau.")
                        .type("warning")
                        .build();
                if (notifyDTOS.isEmpty()) {
                    notifyDTOS.add(notifyDTO);
                } else {
                    notifyDTOS.add(1, notifyDTO);
                }
            } else if (academicPerformance.equals("Khá")) {
                NotifyDTO notifyDTO = new NotifyDTO().builder()
                        .title("Đề xuất tín chỉ cần đạt")
                        .message("Học lực của bạn hiện tại đang ở mức <b>" + academicPerformance + "</b>. Có vẻ như học lực của đang khá ổn định. Tuy nhiên, bạn cũng cần tập trung học kĩ các nội dung và kiến thức cố lõi để có thể sử dụng tốt sau này. Vì thế nên tôi khuyến nghị bạn học <b>17-20</b> chỉ trong học kì này.")
                        .type("warning")
                        .build();
                if (notifyDTOS.isEmpty()) {
                    notifyDTOS.add(notifyDTO);
                } else {
                    notifyDTOS.add(1, notifyDTO);
                }
            } else if (academicPerformance.equals("Giỏi")) {
                if (disireAcademicPerformance.equals("Giỏi")) {
                    NotifyDTO notifyDTO = new NotifyDTO().builder()
                            .title("TĐề xuất tín chỉ cần đạt")
                            .message("Học lực của bạn hiện tại đang ở mức <b>" + academicPerformance + "</b>. Có vẻ như bạn đang muốn duy trì học lực của mình. Vì thế nên tôi khuyến nghị bạn chỉ nên học <b>16-21</b> tín chỉ trong học kì này.")
                            .type("info")
                            .build();
                    if (notifyDTOS.isEmpty()) {
                        notifyDTOS.add(notifyDTO);
                    } else {
                        notifyDTOS.add(1, notifyDTO);
                    }
                } else if (disireAcademicPerformance.equals("Xuất sắc")) {
                    NotifyDTO notifyDTO = new NotifyDTO().builder()
                            .title("Đề xuất tín chỉ cần đạt")
                            .message("Học lực của bạn hiện tại đang ở mức <b>" + academicPerformance + "</b>. Có vẻ như bạn đang muốn nâng cao học lực của chính mình. Vì thế nên trong học kì này tôi khuyến nghị bạn nên học từ <b>16-18</b> tín chỉ.")
                            .type("info")
                            .build();
                    if (notifyDTOS.isEmpty()) {
                        notifyDTOS.add(notifyDTO);
                    } else {
                        notifyDTOS.add(1, notifyDTO);
                    }
                } else {
                    NotifyDTO notifyDTO = new NotifyDTO().builder()
                            .title("Đề xuất tín chỉ cần đạt")
                            .message("Học lực của bạn hiện tại đang ở mức <b>" + academicPerformance + "</b>. Chúc mừng bạn vị sự tiến bộ của chính mình. Trong học kì tới thì tôi khuyến nghị rằng bạn nên đăng ký từ <b>20-23</b> tín chỉ.")
                            .type("info")
                            .build();
                    if (notifyDTOS.isEmpty()) {
                        notifyDTOS.add(notifyDTO);
                    } else {
                        notifyDTOS.add(1, notifyDTO);
                    }
                }
            } else {
                if (disireAcademicPerformance.equals("Xuất sắc")) {
                    NotifyDTO notifyDTO = new NotifyDTO().builder()
                            .title("Đề xuất tín chỉ cần đạt")
                            .message("Học lực của bạn hiện tại đang ở mức <b>" + academicPerformance + "</b>. Chúc mừng bạn vị sự tiến bộ của chính mình. Trong học kì tới thì để giữ vững sự tiến bộ đó và để có được điểm số mà bạn mong muốn thì tôi khuyến nghị rằng bạn nên đăng ký từ <b>16-18</b> tín chỉ.")
                            .type("info")
                            .build();
                    if (notifyDTOS.isEmpty()) {
                        notifyDTOS.add(notifyDTO);
                    } else {
                        notifyDTOS.add(1, notifyDTO);
                    }
                } else if (disireAcademicPerformance.equals("Giỏi")) {
                    NotifyDTO notifyDTO = new NotifyDTO().builder()
                            .title("Đề xuất tín chỉ cần đạt")
                            .message("Học lực của bạn hiện tại đang ở mức <b>" + academicPerformance + "</b>. Chúc mừng vì sự tiến bộ vượt ngoài mong đợi của chính bạn. Để duy trì được sự tiến bộ này và để tiếp tục phát triển thì tôi khuyến nghị là bạn nên ký từ <b>16-21</b> tín chỉ trong học kì sắp tới.")
                            .type("info")
                            .build();
                    if (notifyDTOS.isEmpty()) {
                        notifyDTOS.add(notifyDTO);
                    } else {
                        notifyDTOS.add(1, notifyDTO);
                    }
                } else {
                    NotifyDTO notifyDTO = new NotifyDTO().builder()
                            .title("Đề xuất tín chỉ cần đạt")
                            .message("Học lực của bạn hiện tại đang ở mức <b>" + academicPerformance + "</b>. Chúc mừng bạn vì sự tiến bộ của chính mình. Với khả năng học hỏi và siêng năng cùng với sự cố gắng của mình thì tôi khuyến nghị rằng bạn nên đăng ký từ <b>20-25</b> tín chỉ trong học kì sắp tới.")
                            .type("info")
                            .build();
                    if (notifyDTOS.isEmpty()) {
                        notifyDTOS.add(notifyDTO);
                    } else {
                        notifyDTOS.add(1, notifyDTO);
                    }
                }
            }
        }
//        Thông báo tổng số tín chỉ của các môn học đã chọn
        NotifyDTO notifyDTO = new NotifyDTO().builder()
                .title("Tổng số tín chỉ của các môn học")
                .message("Tổng số tín chỉ của các môn học mà bạn đã chọn là: <b style=\"color: var(--primary-lightred)\">" + totalCredits + "</b>.")
                .type("info")
                .build();
        notifyDTOS.add(0, notifyDTO);
        return notifyDTOS;
    }

    private String getDisireAcademicPerform(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        double gpa4 = user.getDesiredScore();
        return getAcademicPerformance(gpa4);
    }

    private String getAcademicPerformance(double gpa4) {
        if (gpa4 >= 2 && gpa4 < 2.5) {
            return "Trung bình";
        } else if (gpa4 >= 2.5 && gpa4 < 3.2) {
            return "Khá";
        } else if (gpa4 >= 3.2 && gpa4 < 3.6) {
            return "Giỏi";
        } else if (gpa4 >= 3.6) {
            return "Xuất sắc";
        }
        return "Yếu";
    }

    private List<Course> getCurrentCourse(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        LocalDate currentDate = LocalDate.now();
        int semesterId = semesterRepository.findCurrentSemesterIdsByDate(currentDate);
        return userCourseRepository.findCoursesBySemesterIdAndUserId(semesterId, user.getId());
    }

    private String getCurrentAcademicPerformance(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        LocalDate currentDate = LocalDate.now();
        int semesterId = semesterRepository.findCurrentSemesterIdsByDate(currentDate);
        UserSemesters userSemesters = userSemesterRepository.findByUserIdAndSemesterSemesterId(user.getId(), semesterId).orElseThrow(() -> new RuntimeException("User semester not found"));
        double gpa4 = userSemesters.getCumulativeGpa4();
        return getAcademicPerformance(gpa4);
    }

    private List<Course> getCoursesWillOpen(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        lấy học kỳ hiện tại
        LocalDate currentDate = LocalDate.now();
        String semesterId = semesterRepository.findCurrentSemesterIdsByDate(currentDate) + "";
//        lấy danh sách môn học đã qua
        List<Course> passedCourses = userCourseRepository.findPassedCoursesByUser(user);
//        lấy danh sách môn học sẽ mở trong học kỳ tiếp theo
        List<Course> nextSemesterCourses = new ArrayList<>();
        if (semesterId.endsWith("1")) {
            nextSemesterCourses = semesterCourseRepository.findCoursesBySemesterIdEndingWith("2", user.getTrainingProgram().getId());
        } else if (semesterId.endsWith("2") || semesterId.endsWith("3")) {
            nextSemesterCourses = semesterCourseRepository.findCoursesBySemesterIdEndingWith("1", user.getTrainingProgram().getId());
        }
//        lọc các môn chưa học và đã được hoàn thành các môn yêu cầu sẽ mở trong học kỳ tiếp theo
        return nextSemesterCourses.stream()
                .filter(course -> !passedCourses.contains(course) &&
                        passedCourses.containsAll(course.getPrerequisites()) &&
                        (course.getId() + "").length() == 6)
                .collect(Collectors.toList());
    }

    private List<CourseDTO> getCourseDTOS(List<Course> courses) {
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course c : courses) {
            CourseDTO courseDTO = new CourseDTO().builder()
                    .courseId(c.getId())
                    .courseName(c.getCourseName())
                    .credits(c.getCredits())
                    .isMandatory(c.isMandatory())
                    .practiceHours(c.getPracticeHours())
                    .theoryHours(c.getTheoryHours())
                    .build();
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    @Override
    public List<NotifyDTO> getNotifyForCoursesToImprove(long userId) {
        List<NotifyDTO> notifyDTOS = new ArrayList<>();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (isDone(user.getId())) {
            NotifyDTO notifyDTO = new NotifyDTO().builder()
                    .title("Chúc mừng bạn đã hoàn thành chương trình đào tạo")
                    .message("Chúc mừng bạn đã hoàn thành hết chương trình đào tạo của mình. Hãy cố gắng trong các môn học cuối cùng để có được một chiếc bằng đại học thật đẹp nhé.")
                    .type("info")
                    .build();
            notifyDTOS.add(notifyDTO);
            return notifyDTOS;
        }
        List<Course> failedCourses = getFailedCourse(user.getId());
        List<Course> coursesWillOpen = getCoursesWillOpen(user.getId());
        coursesWillOpen.forEach(course -> {
            if (failedCourses.contains(course)) {
                NotifyDTO notifyDTO = new NotifyDTO().builder()
                        .title("Cân nhắc học cải thiện môn " + course.getCourseName())
                        .message("Bạn có môn học <b>" + course.getCourseName() + "</b> đã bị trượt trước đó. Bạn nên cân học lại môn học này để cải thiện điểm số của mình.")
                        .type("danger")
                        .build();
                notifyDTOS.add(notifyDTO);
            }
        });
        return notifyDTOS;
    }

    private List<Course> getFailedCourse(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        List<Course> failedCoures = userCourseRepository.findFailCoursesByUser(user);
        List<Course> passedCourses = userCourseRepository.findPassedCoursesByUser(user);
        List<Course> coursesWillOpen = getCoursesWillOpen(user.getId());
        List<Course> currentCourses = getCurrentCourse(user.getId());

        // Lọc các môn học đã trượt mà chưa học lại
        failedCoures = failedCoures.stream()
                .filter(course -> !passedCourses.contains(course))
                .collect(Collectors.toList());
        // Lọc các môn học đã trượt mà sẽ mở trong học kỳ tiếp theo
        failedCoures = failedCoures.stream()
                .filter(course -> coursesWillOpen.contains(course))
                .collect(Collectors.toList());
        // Lọc các môn học đã đang học ở kì hiện tại
        failedCoures = failedCoures.stream()
                .filter(course -> !currentCourses.contains(course))
                .collect(Collectors.toList());
        if (failedCoures.isEmpty()) {
            System.out.println("Không có môn học nào cần học lại");
        } else {
            failedCoures.forEach(course -> {
                System.out.println(course.getCourseName());
            });
        }
        return failedCoures;
    }

    private List<NotifyDTO> checkCourseGroupNeedStudy(long userId, CourseSuggestDTO courseSuggestDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<NotifyDTO> notifyDTOS = new ArrayList<>();

//        Lấy danh sách môn học sẽ mở trong học kỳ tiếp theo
        List<Course> openedCourses = getCoursesWillOpen(user.getId());

        // lấy danh sách id của các môn học đã chọn
        List<Integer> courseIds = courseSuggestDTO.getListCourse().stream()
                .map(CourseDTO::getCourseId)
                .collect(Collectors.toList());

        // lọc các môn học sẽ mở trong học kỳ tiếp theo mà chưa chọn
        openedCourses = openedCourses.stream()
                .filter(course -> !courseIds.contains(course.getId()))
                .collect(Collectors.toList());

//        kiểm tra xem các môn học đã chọn có đủ tín chỉ tối thiểu của các nhóm học phần không
        for (Course course : openedCourses) {
            if (checkMinCreditsForCourseGroup(userId, course.getId()) == 0) {
                CourseGroup courseGroup = courseGroupCourseRepository.findCourseGroupByCourseId(course.getId(), user.getTrainingProgram().getId());
                NotifyDTO notifyDTO = new NotifyDTO().builder()
                        .title("Bạn cần học thêm tín chỉ của " + courseGroup.getName())
                        .message("Bạn cần học thêm môn <b>" + course.getCourseName() + "</b> thuộc <b>" + courseGroup.getName() + "</b> để đạt <b>" + courseGroup.getMinCredits() + "</b> tín chỉ tối thiểu.")
                        .type("warning")
                        .build();
                if (!notifyDTOS.contains(notifyDTO)) {
                    notifyDTOS.add(notifyDTO);
                }
            }
        }
        return notifyDTOS;
    }

    private int getCurrentCredits(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        LocalDate currentDate = LocalDate.now();
        int semesterId = semesterRepository.findCurrentSemesterIdsByDate(currentDate);
        List<Course> courses = userCourseRepository.findCoursesBySemesterIdAndUserId(semesterId, user.getId());
        int totalCredits = 0;
        for (Course course : courses) {
            totalCredits += course.getCredits();
        }
        return totalCredits;
    }

    private int getRemainSemester(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<TrainingProgramSemesters> trainingProgramSemesters = trainingProgramSemesterRepository.findTrainingProgramSemestersByTrainingProgramId(user.getTrainingProgram().getId());
        // lấy học kỳ hiện tại
        LocalDate currentDate = LocalDate.now();
        String curentSemesterId = semesterRepository.findCurrentSemesterIdsByDate(currentDate) + "";
        int currentYear = Integer.parseInt(curentSemesterId.substring(0, 4));
        int currentSemester = Integer.parseInt(curentSemesterId.substring(4));
        int remainSemester = 0;
        // lấy số học kỳ còn lại (chỉ tính học kỳ 1 và học kỳ 2)
        for (TrainingProgramSemesters trainingProgramSemester : trainingProgramSemesters) {
            if ((trainingProgramSemester.getId().getSemesterId() + "").endsWith("1") || (trainingProgramSemester.getId().getSemesterId() + "").endsWith("2")) {
                int year = Integer.parseInt((trainingProgramSemester.getId().getSemesterId() + "").substring(0, 4));
                int semester = Integer.parseInt((trainingProgramSemester.getId().getSemesterId() + "").substring(4));
                if (year > currentYear) {
                    remainSemester++;
                } else if (year == currentYear) {
                    if (semester > currentSemester) {
                        remainSemester++;
                    }
                }
            }
        }
        return remainSemester;
    }

    private int getRemainCredits(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        int remainCredits = 0;
        List<CourseGroup> userCourseGroups = courseGroupRepository.findCourseGroupByTrainingProgramId(user.getTrainingProgram().getId());
        List<Course> passedCourses = userCourseRepository.findPassedCoursesByUser(user);
        LocalDate currentDate = LocalDate.now();
        int semesterId = semesterRepository.findCurrentSemesterIdsByDate(currentDate);
        List<Course> currentCourses = userCourseRepository.findCoursesBySemesterIdAndUserId(semesterId, userId);
        // lấy ra số tính chỉ còn lại của mỗi nhóm học phần
        for (CourseGroup courseGroup : userCourseGroups) {
            int minCredits = courseGroup.getMinCredits();
            int credits = 0;
            // tính tổng số tín chỉ đã học của các môn thuộc nhóm học phần
            for (Course course : passedCourses) {
                boolean isCourseInGroup = course.getCourseGroupCourses().stream()
                        .anyMatch(cgc -> cgc.getGroup().equals(courseGroup));
                if (isCourseInGroup) {
                    credits += course.getCredits();
                }
            }
            // tính tổng số tín chỉ đã đăng ký của các môn thuộc nhóm học phần
            for (Course course : currentCourses) {
                boolean isCourseInGroup = course.getCourseGroupCourses().stream()
                        .anyMatch(cgc -> cgc.getGroup().equals(courseGroup));
                if (isCourseInGroup) {
                    credits += course.getCredits();
                }
            }
            if (credits < minCredits) {
                remainCredits += minCredits - credits;
            }
        }
        System.out.println("Số tín chỉ còn lại: " + remainCredits);
        return remainCredits;
    }

    private int checkMinCreditsForCourseGroup(long userId, int courseId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        CourseGroup courseGroup = courseGroupCourseRepository.findCourseGroupByCourseId(courseId, user.getTrainingProgram().getId());
        List<Course> passedCourses = userCourseRepository.findPassedCoursesByUser(user);
        List<Course> currentCourses = getCurrentCourse(user.getId());
        passedCourses.addAll(currentCourses);
        int totalCredits = 0;

//        Tính tổng số tín chỉ đã học của các môn thuộc nhóm học phần
        for (Course course : passedCourses) {
            boolean isCourseInGroup = course.getCourseGroupCourses().stream()
                    .anyMatch(cgc -> cgc.getGroup().equals(courseGroup));
            if (isCourseInGroup) {
                totalCredits += course.getCredits();
            }
        }
//        So sánh tổng số tín chỉ đã học với tín chỉ tối thiểu của nhóm học phần
        if (totalCredits > courseGroup.getMinCredits()) {
            return 1;
        }
        if (totalCredits < courseGroup.getMinCredits()) {
            return 0;
        }
        return -1;
    }

    private boolean isDone(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (getRemainCredits(user.getId()) == 0) {
            return true;
        }
        return false;
    }
}
