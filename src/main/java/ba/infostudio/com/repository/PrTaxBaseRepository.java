package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrTaxBase;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrTaxBase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrTaxBaseRepository extends JpaRepository<PrTaxBase, Long> {
    PrTaxBase findByCode(String code);

}
