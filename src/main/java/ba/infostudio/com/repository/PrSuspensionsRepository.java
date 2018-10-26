package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrSuspensions;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrSuspensions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrSuspensionsRepository extends JpaRepository<PrSuspensions, Long> {
    PrSuspensions findByCode(String code);

}
