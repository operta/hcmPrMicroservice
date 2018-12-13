package ba.infostudio.com.repository;

import ba.infostudio.com.domain.PrPayrollSettings;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PrPayrollSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrPayrollSettingsRepository extends JpaRepository<PrPayrollSettings, Long> {
    List<PrPayrollSettings> findByMonthAndYear(Integer month, Integer year);
    List<PrPayrollSettings> findBySalaryTypeId(Long id);

    /*
    @Procedure(name = "p_obracun_plata")
    void obracunPlata(@Param("p_godina")Integer year, @Param("p_mjesec")Integer month,
                      @Param("p_tip_isplate")String salaryTypeId, @Param("p_broj_racuna")String calculationNumber,
                      @Param("p_korisnik")String userId, @Param("p_out")String output);
    */
}
