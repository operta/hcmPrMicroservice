package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrSalaryItems;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrSalaryItems entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrSalaryItemsRepository extends JpaRepository<PrSalaryItems, Long> {

}
