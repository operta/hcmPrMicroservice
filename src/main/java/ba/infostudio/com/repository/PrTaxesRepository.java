package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrTaxes;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrTaxes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrTaxesRepository extends JpaRepository<PrTaxes, Long> {

}
