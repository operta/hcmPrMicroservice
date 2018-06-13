package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrEmployeeSuspensions;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrEmployeeSuspensions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrEmployeeSuspensionsRepository extends JpaRepository<PrEmployeeSuspensions, Long> {

}
