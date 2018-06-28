package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrEmpSalarySettings;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.time.LocalDate;


/**
 * Spring Data JPA repository for the PrEmpSalarySettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrEmpSalarySettingsRepository extends JpaRepository<PrEmpSalarySettings, Long> {
    PrEmpSalarySettings findByEmployeeIdAndPayrollSettingsId(Integer id1, Long id2);

    @Query("SELECT COUNT(id) FROM EmEmpSalaries WHERE idEmployee.id=:empId AND (dateTo > :dateTo OR dateTo IS NULL)")
    Integer findNumOfEmpsWithDateToNotExpired(@Param("empId") Long empId, @Param("dateTo") LocalDate dateTo);
}
