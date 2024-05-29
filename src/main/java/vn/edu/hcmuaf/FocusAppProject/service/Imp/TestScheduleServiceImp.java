package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.UserTestScheduleDTO;
import vn.edu.hcmuaf.FocusAppProject.response.TestScheduleResponse;

import java.util.List;

public interface TestScheduleServiceImp {
    public void createTestSchedules(UserTestScheduleDTO userTestScheduleDTO) throws Exception;
    List<TestScheduleResponse> getCurrentTestSchedulesByUserId(long userId) throws Exception;
}
