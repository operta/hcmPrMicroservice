package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrEmpSalaryItems;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrEmpSalaryItems entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrEmpSalaryItemsRepository extends JpaRepository<PrEmpSalaryItems, Long> {

}
