package ba.infostudio.com.repository;

import ba.infostudio.com.domain.EmEmployees;
import ba.infostudio.com.domain.PrEmployeeSuspensions;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the PrEmployeeSuspensions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrEmployeeSuspensionsRepository extends JpaRepository<PrEmployeeSuspensions, Long> {
    List<PrEmployeeSuspensions> findByEmployeeId(Integer id);

}
