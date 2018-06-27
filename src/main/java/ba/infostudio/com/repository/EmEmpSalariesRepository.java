package ba.infostudio.com.repository;

import ba.infostudio.com.domain.EmEmpSalaries;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the EmEmpSalaries entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmEmpSalariesRepository extends JpaRepository<EmEmpSalaries, Long> {
    List<EmEmpSalaries> findByIdEmployeeId(Long id);
}
