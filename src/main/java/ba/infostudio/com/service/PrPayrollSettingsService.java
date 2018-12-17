package ba.infostudio.com.service;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Service
public class PrPayrollSettingsService {
    @PersistenceContext
    private EntityManager entityManager;


    public String obracunPlata(Integer year, Integer month,
                               Long salaryTypeId, String calculationNumber,
                               Long userId) {
        String procedureName = "PKG_OBRACUN_PLATA_FBIH.p_obracun_plata";

        return executeProcedure(procedureName, year, month, salaryTypeId,
            calculationNumber, userId);
    }

    public String prijedlogPlata(Integer year, Integer month,
                                 Long salaryTypeId, String calculatioNumber,
                                 Long userId){
        String procedureName = "PKG_PRIJEDLOG_PLATA.p_prijedlog_plata";

        return executeProcedure(procedureName, year, month, salaryTypeId,
            calculatioNumber, userId);
    }

    public String obrisiPlate(Integer year, Integer month,
                                 Long salaryTypeId, String calculatioNumber,
                                 Long userId){
        String procedureName = "PKG_PRIJEDLOG_PLATA.p_obrisi_obracun_plata";

        return executeProcedure(procedureName, year, month, salaryTypeId,
            calculatioNumber, userId);
    }

    private String executeProcedure(String procedureName,
                                    Integer year,
                                    Integer month,
                                    Long salaryTypeId,
                                    String calculationNumber,
                                    Long userId){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(procedureName);

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(6, String.class, ParameterMode.OUT);


        query.setParameter(1, year);
        query.setParameter(2, month);
        query.setParameter(3, salaryTypeId);
        query.setParameter(4, calculationNumber);
        query.setParameter(5, userId);

        return (String) query.getOutputParameterValue(6);
    }
}
