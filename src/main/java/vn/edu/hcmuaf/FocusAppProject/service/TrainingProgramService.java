package vn.edu.hcmuaf.FocusAppProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.dto.TrainingProgramDTO;
import vn.edu.hcmuaf.FocusAppProject.models.Department;
import vn.edu.hcmuaf.FocusAppProject.models.TrainingProgram;
import vn.edu.hcmuaf.FocusAppProject.repository.CourseRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.DepartmentRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.SemesterRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.TrainingProgramRepository;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.TrainingProgramServiceImp;

@Service
public class TrainingProgramService implements TrainingProgramServiceImp {
    @Autowired
    private TrainingProgramRepository trainingProgramRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public TrainingProgram createTrainingProgram(TrainingProgramDTO trainingProgramDTO, Department department) {
        return trainingProgramRepository.save(new TrainingProgram().builder()
                .year(trainingProgramDTO.getYear())
                .department(departmentRepository.findById(Long.parseLong(trainingProgramDTO.getDepartment().getDepartmentID())).get())
                .build());

    }

    @Override
    public boolean isExist(int year, String departmentID) {
        if (departmentID.equals("DT22") || departmentID.equals("7480201")) departmentID = "52480201";
        if (year == 2021) year = 2020;
        if (year >= 2022) year = 2022;
        return trainingProgramRepository.existsByYearAndDepartmentId(year, Long.parseLong(departmentID));
    }

    @Override
    public TrainingProgram findTrainingProgram(int year, String departmentID) {
        if (departmentID.equals("DT22") || departmentID.equals("7480201")) departmentID = "52480201";
        if (year == 2021) year = 2020;
        if (year >= 2022) year = 2022;
        return trainingProgramRepository.findByYearAndDepartmentId(year, Long.parseLong(departmentID));
    }
}
