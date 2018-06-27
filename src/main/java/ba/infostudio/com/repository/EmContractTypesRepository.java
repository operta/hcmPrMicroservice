package ba.infostudio.com.repository;

import ba.infostudio.com.domain.EmContractTypes;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the EmContractTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmContractTypesRepository extends JpaRepository<EmContractTypes, Long> {

}
