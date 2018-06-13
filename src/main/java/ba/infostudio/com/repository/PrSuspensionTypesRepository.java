package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrSuspensionTypes;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrSuspensionTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrSuspensionTypesRepository extends JpaRepository<PrSuspensionTypes, Long> {

}
