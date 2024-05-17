package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.CourseGroup;

@Repository
public interface CourseGroupRepository extends JpaRepository<CourseGroup,Long> {
}
