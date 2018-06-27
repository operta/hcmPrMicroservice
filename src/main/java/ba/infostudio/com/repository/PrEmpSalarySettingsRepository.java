package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrEmpSalarySettings;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrEmpSalarySettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrEmpSalarySettingsRepository extends JpaRepository<PrEmpSalarySettings, Long> {
    PrEmpSalarySettings findByEmployeeIdAndPayrollSettingsId(Integer id1, Long id2);
}
