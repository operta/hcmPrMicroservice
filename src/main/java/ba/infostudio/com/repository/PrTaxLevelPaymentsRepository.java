package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrTaxLevelPayments;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrTaxLevelPayments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrTaxLevelPaymentsRepository extends JpaRepository<PrTaxLevelPayments, Long> {

}
