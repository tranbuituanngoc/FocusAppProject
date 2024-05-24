package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.CourseDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.CourseSuggestDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.NotifyDTO;
import vn.edu.hcmuaf.FocusAppProject.models.Course;

import java.util.List;

public interface CourseServiceImp {
    List<CourseDTO> getPassedCoursesByUser(long userId);
    List<CourseDTO> getPrerequisiteCourses(int courseId);
    List<CourseDTO> getCourseForNextSemester(long userId);
    List<NotifyDTO> handleCourseSuggest(CourseSuggestDTO courseSuggestDTO);
    List<NotifyDTO> getNotifyForCoursesToImprove(long userId);
}
