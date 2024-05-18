package vn.edu.hcmuaf.FocusAppProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.dto.SemesterDetailDTO;
import vn.edu.hcmuaf.FocusAppProject.models.Semester;
import vn.edu.hcmuaf.FocusAppProject.repository.SemesterRepository;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.SemesterServiceImp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class SemesterService implements SemesterServiceImp {
    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public Semester createOrUpdateSemester(SemesterDetailDTO semesterDetailDTO) {
        Optional<Semester> existingSemester = semesterRepository.findById(semesterDetailDTO.getSemesterId());
        if (existingSemester.isPresent()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.parse(semesterDetailDTO.getStartDate(), formatter);
            LocalDate endDate = LocalDate.parse(semesterDetailDTO.getEndDate(), formatter);

            Semester semester = existingSemester.get();
            semester.setSemesterName(semesterDetailDTO.getSemesterName());
            semester.setStartDate(startDate);
            semester.setEndDate(endDate);
            return semesterRepository.save(semester);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.parse(semesterDetailDTO.getStartDate(), formatter);
            LocalDate endDate = LocalDate.parse(semesterDetailDTO.getEndDate(), formatter);

            return semesterRepository.save(Semester.builder()
                    .semesterId(semesterDetailDTO.getSemesterId())
                    .semesterName(semesterDetailDTO.getSemesterName())
                    .startDate(startDate)
                    .endDate(endDate)
                    .build());
        }
    }
}
