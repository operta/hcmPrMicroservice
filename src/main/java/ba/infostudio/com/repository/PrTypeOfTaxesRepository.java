package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrTypeOfTaxes;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrTypeOfTaxes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrTypeOfTaxesRepository extends JpaRepository<PrTypeOfTaxes, Long> {

}
