package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.UserScheduleDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.WeekDTO;
import vn.edu.hcmuaf.FocusAppProject.exception.DataNotFoundException;
import vn.edu.hcmuaf.FocusAppProject.response.CourseScheduleResponse;
import vn.edu.hcmuaf.FocusAppProject.response.WeekResponse;

import java.util.List;

public interface CourseScheduleServiceImp {
    void createUserSchedule(UserScheduleDTO userScheduleDTO) throws Exception;
    WeekResponse getWeeksByCurrentSemester() throws DataNotFoundException;
    CourseScheduleResponse getCourseSchedulesByWeekIdAndUserId(Long weekId, Long userId) throws DataNotFoundException;
}
