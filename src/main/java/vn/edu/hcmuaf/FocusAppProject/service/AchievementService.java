package vn.edu.hcmuaf.FocusAppProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.models.Achivement;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import vn.edu.hcmuaf.FocusAppProject.repository.AchievementRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.UserRepository;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.AchievementServiceImp;

import java.time.LocalDate;

@Service
public class AchievementService implements AchievementServiceImp {
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public long getTotalTime(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        LocalDate date = LocalDate.now();
        Achivement achivement = achievementRepository.findAchievementByUserIdAndDate(userId, date);
        if (achivement != null)
            return achivement.getTotalTime();
        else {
            Achivement newAchivement = Achivement.builder().totalTime(0).date(date).user(user).build();
            achievementRepository.save(newAchivement);
            return 0;
        }

    }

    @Override
    public long updateTotalTime(long userId, long totalTime) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("1: "+totalTime);
        LocalDate date = LocalDate.now();
        Achivement achivement = achievementRepository.findAchievementByUserIdAndDate(user.getId(), date);
        totalTime = achivement.getTotalTime() + totalTime;
        System.out.println("2: "+totalTime);
        achivement.setTotalTime(totalTime);
        achievementRepository.save(achivement);
        return totalTime;
    }
}
