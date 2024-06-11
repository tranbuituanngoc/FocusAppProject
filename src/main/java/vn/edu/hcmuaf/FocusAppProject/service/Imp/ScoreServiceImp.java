package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.UserSemesterDTO;
import vn.edu.hcmuaf.FocusAppProject.exception.ScoreNotValidException;
import vn.edu.hcmuaf.FocusAppProject.response.SemesterScoreResponse;

import java.util.List;

public interface ScoreServiceImp {
    void createScore(UserSemesterDTO userSemesterDTO) ;
    List<SemesterScoreResponse> getScore(long userId);
    SemesterScoreResponse caculateRequireScore(long userId) throws ScoreNotValidException;
}
