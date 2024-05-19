package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.UserTestScheduleDTO;

public interface TestScheduleServiceImp {
    public void createOrUpdateTestSchedules(UserTestScheduleDTO userTestScheduleDTO) throws Exception;
}
