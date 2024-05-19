package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.UserScheduleDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.WeekDTO;

import java.util.List;

public interface CourseScheduleServiceImp {
    void createOrUpdateUserSchedule(UserScheduleDTO userScheduleDTO) throws Exception;
}
