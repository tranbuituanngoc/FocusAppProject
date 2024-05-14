package vn.edu.hcmuaf.FocusAppProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.dto.LinkToDKMHDTO;
import vn.edu.hcmuaf.FocusAppProject.exception.DataNotFoundException;
import vn.edu.hcmuaf.FocusAppProject.models.LinkToDKMH;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import vn.edu.hcmuaf.FocusAppProject.repository.LinkToDKMHRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.UserRepository;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.LinkToDKMHImp;

import java.util.Optional;
@Service
public class LinkToDKMHService implements LinkToDKMHImp {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LinkToDKMHRepository linkToDKMHRepository;

    @Override
    public LinkToDKMH createOrUpdateLinkToDKMH(LinkToDKMHDTO linkToDKMHDTO) throws Exception {
        Optional<LinkToDKMH> existingLink = linkToDKMHRepository.findByUserId(linkToDKMHDTO.getUserId());
        if (existingLink.isPresent()) {
            LinkToDKMH linkToDKMH = existingLink.get();
            linkToDKMH.setMssv(linkToDKMHDTO.getMssv());
            linkToDKMH.setRefreshToken(linkToDKMHDTO.getRefreshToken());
            linkToDKMH.setAccessToken(linkToDKMHDTO.getAccessToken());
            return linkToDKMHRepository.save(linkToDKMH);
        }else {
            User user = userRepository.findById(linkToDKMHDTO.getUserId()).orElseThrow(() -> new DataNotFoundException("User not found!"));
            return linkToDKMHRepository.save(LinkToDKMH.builder()
                    .mssv(linkToDKMHDTO.getMssv())
                    .refreshToken(linkToDKMHDTO.getRefreshToken())
                    .accessToken(linkToDKMHDTO.getAccessToken())
                    .tokenType(linkToDKMHDTO.getTokenType())
                    .user(user)
                    .build());
        }
    }

    @Override
    public String getExpire(Long userId) throws DataNotFoundException {
        LinkToDKMH linkToDKMH = linkToDKMHRepository.findByUserId(userId).orElseThrow(() -> new DataNotFoundException("Unaffiliated user!"));
        return String.valueOf(linkToDKMH.getExpires());
    }
}
