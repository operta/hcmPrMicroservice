package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrTaxLink;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrTaxLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrTaxLinkRepository extends JpaRepository<PrTaxLink, Long> {
    PrTaxLink findByCode(String code);

}
