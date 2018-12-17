package ba.infostudio.com.service;

import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;

@Service
public class PrPayrollSettingsService {
    @PersistenceContext
    private EntityManager entityManager;

    private EntityManagerFactory entityManagerFactory;

    public String obracunPlata(Integer year, Integer month,
                               Long salaryTypeId, String calculationNumber,
                               String userId) {
        String procedureName = "PKG_OBRACUN_PLATA_FBIH.p_obracun_plata";

        return executeProcedure(procedureName, year, month, salaryTypeId,
            calculationNumber, userId);
    }

    public String prijedlogPlata(Integer year, Integer month,
                                 Long salaryTypeId, String calculatioNumber,
                                 String userId){
        String procedureName = "PKG_PRIJEDLOG_PLATA.p_prijedlog_plata";

        return executeProcedure(procedureName, year, month, salaryTypeId,
            calculatioNumber, userId);
    }

    public String obrisiPlate(Integer year, Integer month,
                                 Long salaryTypeId, String calculatioNumber,
                                 String userId){
        String procedureName = "PKG_OBRACUN_PLATA_FBIH.p_obrisi_obracun_plata";

        return executeProcedure(procedureName, year, month, salaryTypeId,
            calculatioNumber, userId);
    }

    private String executeProcedure(String procedureName,
                                    Integer year,
                                    Integer month,
                                    Long salaryTypeId,
                                    String calculationNumber,
                                    String userId){
//        EntityManager localManager = entityManagerFactory.createEntityManager();
//        localManager.getTransaction().begin();

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(procedureName);
//        entityManager.getTransaction();

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(6, String.class, ParameterMode.OUT);
        System.out.println(year);
        System.out.println(month);
        System.out.println(salaryTypeId);
        System.out.println(calculationNumber);
        System.out.println(userId);

        query.setParameter(1, year);
        query.setParameter(2, month);
        query.setParameter(3, salaryTypeId);
        query.setParameter(4, calculationNumber);
        query.setParameter(5, userId);
        query.execute();

//        entityManager.getTransaction().commit();
//        entityManager.close();
        return (String) query.getOutputParameterValue(6);
    }
}
