package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.Major;

import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long>{
    List<Major> findAllByDepartmentId(int departmentId);
}
