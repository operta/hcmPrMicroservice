package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrPayrollSettings;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrPayrollSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrPayrollSettingsRepository extends JpaRepository<PrPayrollSettings, Long> {

}
