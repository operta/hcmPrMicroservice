package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrEmpSalarySuspItems;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrEmpSalarySuspItems entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrEmpSalarySuspItemsRepository extends JpaRepository<PrEmpSalarySuspItems, Long> {
    List<PrEmpSalarySuspItems> findByEmployeeSalaryId(Long id);
    List<PrEmpSalarySuspItems> findByEmpSuspensionId(Long id);
}
