package vn.edu.hcmuaf.FocusAppProject.service.Imp;

public interface AchievementServiceImp {
    long getTotalTime(long userId);
    long updateTotalTime(long userId, long totalTime);
}
