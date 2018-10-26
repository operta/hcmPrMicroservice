package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrSalaryTypes;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrSalaryTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrSalaryTypesRepository extends JpaRepository<PrSalaryTypes, Long> {
    PrSalaryTypes findByCode(String code);

}
