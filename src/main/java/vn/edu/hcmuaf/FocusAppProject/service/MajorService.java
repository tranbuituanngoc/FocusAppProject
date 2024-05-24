package vn.edu.hcmuaf.FocusAppProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.dto.MajorDTO;
import vn.edu.hcmuaf.FocusAppProject.models.Major;
import vn.edu.hcmuaf.FocusAppProject.repository.MajorRepository;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.MajorServiceImp;

import java.util.ArrayList;
import java.util.List;

@Service
public class MajorService implements MajorServiceImp {
    @Autowired
    private MajorRepository majorRepository;

    @Override
    public List<MajorDTO> getAllMajorsByDepartmentId(int departmentId) {
        List<MajorDTO> majorDTOS = new ArrayList<>();
        List<Major> majors = majorRepository.findAllByDepartmentId(departmentId);
        for (Major major : majors) {
            MajorDTO majorDTO = MajorDTO.builder()
                    .majorID(String.valueOf(major.getId()))
                    .majorName(major.getMajorName())
                    .build();
            majorDTOS.add(majorDTO);
        }
        return majorDTOS;
    }
}
