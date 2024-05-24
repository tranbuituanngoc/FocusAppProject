package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.LinkToDKMHDTO;
import vn.edu.hcmuaf.FocusAppProject.exception.DataNotFoundException;
import vn.edu.hcmuaf.FocusAppProject.models.LinkToDKMH;
import vn.edu.hcmuaf.FocusAppProject.response.LinkToDKMHResponse;

public interface LinkToDKMHImp {
    LinkToDKMH createOrUpdateLinkToDKMH(LinkToDKMHDTO linkToDKMHDTO) throws DataNotFoundException, Exception;
    LinkToDKMHResponse getExpire(Long userId) throws DataNotFoundException;
    boolean isLinked(Long userId) throws DataNotFoundException;
}
