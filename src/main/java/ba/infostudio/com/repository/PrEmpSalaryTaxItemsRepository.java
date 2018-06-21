package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrEmpSalaryTaxItems;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrEmpSalaryTaxItems entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrEmpSalaryTaxItemsRepository extends JpaRepository<PrEmpSalaryTaxItems, Long> {
    List<PrEmpSalaryTaxItems> findByEmployeeSalaryId(Long id);
}
