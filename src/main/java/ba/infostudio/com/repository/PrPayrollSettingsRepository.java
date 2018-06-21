package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrPayrollSettings;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrPayrollSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrPayrollSettingsRepository extends JpaRepository<PrPayrollSettings, Long> {
    List<PrPayrollSettings> findByMonthAndYear(Integer month, Integer year);
    List<PrPayrollSettings> findBySalaryTypeId(Long id);

}
