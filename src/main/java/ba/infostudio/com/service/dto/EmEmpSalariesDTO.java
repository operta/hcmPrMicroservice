package ba.infostudio.com.service.dto;


import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the EmEmpSalaries entity.
 */
public class EmEmpSalariesDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate dateFrom;

    private LocalDate dateTo;

    private Double salaryAmount;

    private Double salaryCoefficient;

    private Double workHistoryCoefficient;

    private String createdBy;

    private Instant createdAt;

    private String updatedBy;

    private Instant updatedAt;

    private Integer idWorkPlace;

    private Long idEmployeeId;

    private Long idContractTypeId;

    private String idContractTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Double getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(Double salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public Double getSalaryCoefficient() {
        return salaryCoefficient;
    }

    public void setSalaryCoefficient(Double salaryCoefficient) {
        this.salaryCoefficient = salaryCoefficient;
    }

    public Double getWorkHistoryCoefficient() {
        return workHistoryCoefficient;
    }

    public void setWorkHistoryCoefficient(Double workHistoryCoefficient) {
        this.workHistoryCoefficient = workHistoryCoefficient;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getIdWorkPlace() {
        return idWorkPlace;
    }

    public void setIdWorkPlace(Integer idWorkPlace) {
        this.idWorkPlace = idWorkPlace;
    }

    public Long getIdEmployeeId() {
        return idEmployeeId;
    }

    public void setIdEmployeeId(Long emEmployeesId) {
        this.idEmployeeId = emEmployeesId;
    }

    public Long getIdContractTypeId() {
        return idContractTypeId;
    }

    public void setIdContractTypeId(Long emContractTypesId) {
        this.idContractTypeId = emContractTypesId;
    }

    public String getIdContractTypeName() {
        return idContractTypeName;
    }

    public void setIdContractTypeName(String emContractTypesName) {
        this.idContractTypeName = emContractTypesName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmEmpSalariesDTO emEmpSalariesDTO = (EmEmpSalariesDTO) o;
        if(emEmpSalariesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emEmpSalariesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmEmpSalariesDTO{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", salaryAmount=" + getSalaryAmount() +
            ", salaryCoefficient=" + getSalaryCoefficient() +
            ", workHistoryCoefficient=" + getWorkHistoryCoefficient() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", idWorkPlace=" + getIdWorkPlace() +
            "}";
    }
}
