package vn.edu.hcmuaf.FocusAppProject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.dto.SemesterDTO;
import vn.edu.hcmuaf.FocusAppProject.exception.DataNotFoundException;
import vn.edu.hcmuaf.FocusAppProject.models.Semester;
import vn.edu.hcmuaf.FocusAppProject.models.TrainingProgram;
import vn.edu.hcmuaf.FocusAppProject.models.TrainingProgramSemesters;
import vn.edu.hcmuaf.FocusAppProject.models.keys.KeyTrainingProgramSemester;
import vn.edu.hcmuaf.FocusAppProject.repository.SemesterRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.TrainingProgramRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.TrainingProgramSemesterRepository;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.TrainingProgramSemesterServiceImp;

@Service
public class TrainingProgramSemesterService implements TrainingProgramSemesterServiceImp {
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private TrainingProgramRepository trainingProgramRepository;
    @Autowired
    private TrainingProgramSemesterRepository trainingProgramSemesterRepository;

    @Override
    @Transactional // This annotation is used to indicate that the method should be run in a transaction.
    public void createTrainingProgramSemester(SemesterDTO semesterDTO, long trainingProgramID) throws DataNotFoundException {
        Semester semester = semesterRepository.save(new Semester().builder()
                .semesterName(semesterDTO.getSemesterName())
                .semesterId(semesterDTO.getSemesterId())
                .build());
        TrainingProgram trainingProgram = trainingProgramRepository.findById(trainingProgramID).orElseThrow(() -> new DataNotFoundException("Training Program not found"));

        KeyTrainingProgramSemester id = new KeyTrainingProgramSemester();
        id.setTrainingProgramId(trainingProgramID);
        id.setSemesterId(semester.getSemesterId());

        TrainingProgramSemesters trainingProgramSemesters = new TrainingProgramSemesters();
        trainingProgramSemesters.setId(id);
        trainingProgramSemesters.setTrainingProgram(trainingProgram);
        trainingProgramSemesters.setSemester(semester);

        trainingProgramSemesterRepository.save(trainingProgramSemesters);

    }
}
