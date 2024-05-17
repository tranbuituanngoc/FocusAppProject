package vn.edu.hcmuaf.FocusAppProject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.dto.CourseDTO;
import vn.edu.hcmuaf.FocusAppProject.models.Course;
import vn.edu.hcmuaf.FocusAppProject.models.Department;
import vn.edu.hcmuaf.FocusAppProject.models.Semester;
import vn.edu.hcmuaf.FocusAppProject.models.SemesterCourse;
import vn.edu.hcmuaf.FocusAppProject.models.keys.KeySemesterCourse;
import vn.edu.hcmuaf.FocusAppProject.repository.CourseRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.SemesterCourseRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.SemesterRepository;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.SemesterCourseServiceImp;

@Service
public class SermesterCourseService implements SemesterCourseServiceImp {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private SemesterCourseRepository semesterCourseRepository;

    @Override
    @Transactional
    public void createSemesterCourse(CourseDTO courseDTO, int semesterId, Department department) throws Exception {
        Course course = courseRepository.save(new Course().builder()
                .id(courseDTO.getCourseId())
                .courseName(courseDTO.getCourseName())
                .isMandatory(courseDTO.isMandatory())
                .theoryHours(courseDTO.getTheoryHours())
                .practiceHours(courseDTO.getPracticeHours())
                .credits(courseDTO.getCredits())
                .department(department)
                .build());
        Semester semester = semesterRepository.findById(semesterId).orElseThrow(() -> new Exception("Semester not found"));
//        semesterCourseRepository.save(new SemesterCourse().builder()
//                .course(course)
//                .semester(semester)
//                .build());
        KeySemesterCourse id = new KeySemesterCourse();
        id.setCourseId(course.getId());
        id.setSemesterId(semester.getSemesterId());

        SemesterCourse semesterCourse = new SemesterCourse();
        semesterCourse.setId(id);
        semesterCourse.setCourse(course);
        semesterCourse.setSemester(semester);
        semesterCourseRepository.save(semesterCourse);
    }
}
