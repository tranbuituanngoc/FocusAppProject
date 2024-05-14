package vn.edu.hcmuaf.FocusAppProject.repository;

import vn.edu.hcmuaf.FocusAppProject.models.LinkToDKMH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkToDKMHRepository extends JpaRepository<LinkToDKMH,Long> {
    boolean existsByMssv(String mssv);

    Optional<LinkToDKMH> findByUserId(long userId);
}
