package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.LinkToDKMHDTO;
import vn.edu.hcmuaf.FocusAppProject.exception.DataNotFoundException;
import vn.edu.hcmuaf.FocusAppProject.models.LinkToDKMH;

public interface LinkToDKMHImp {
    LinkToDKMH createOrUpdateLinkToDKMH(LinkToDKMHDTO linkToDKMHDTO) throws DataNotFoundException, Exception;
    String getExpire(Long userId) throws DataNotFoundException;
}
