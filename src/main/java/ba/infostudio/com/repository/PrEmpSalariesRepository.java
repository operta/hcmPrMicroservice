package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrEmpSalaries;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrEmpSalaries entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrEmpSalariesRepository extends JpaRepository<PrEmpSalaries, Long> {
    PrEmpSalaries findByEmployeeIdAndPayrollSettingsId(Integer id1, Long id2);
    List<PrEmpSalaries> findByEmployeeId(Integer id1);
}
