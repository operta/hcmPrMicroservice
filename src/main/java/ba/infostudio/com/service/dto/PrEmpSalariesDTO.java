package ba.infostudio.com.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the PrEmpSalaries entity.
 */
public class PrEmpSalariesDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer year;

    @NotNull
    private Integer month;

    @NotNull
    private Integer calculationNumber;

    @NotNull
    private Integer employeeId;

    private Double grossAmount;

    private Double netAmount;

    private Double taxRelief;

    private Double taxBaseAmount;

    private Double paymentAmount;

    private Integer totalDays;

    private Integer totalHours;

    private Integer numberOfWorkingDays;

    private Integer numberOfWorkingHours;

    private Integer organizationId;

    private Integer workPlaceId;

    private Integer contractTypeId;

    private Integer employeeResidenceId;

    private Double contractSalaryAmount;

    private Double contractSalaryCoefficient;

    private Double contractWorkHistoryCoeff;

    private Integer yearsOfExperience;

    private Long payrollSettingsId;

    private Long salaryTypeId;

    private String salaryTypeName;

    private String createdBy;

    private Instant createdAt;

    private String updatedBy;

    private Instant updatedAt;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getCalculationNumber() {
        return calculationNumber;
    }

    public void setCalculationNumber(Integer calculationNumber) {
        this.calculationNumber = calculationNumber;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(Double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public Double getTaxRelief() {
        return taxRelief;
    }

    public void setTaxRelief(Double taxRelief) {
        this.taxRelief = taxRelief;
    }

    public Double getTaxBaseAmount() {
        return taxBaseAmount;
    }

    public void setTaxBaseAmount(Double taxBaseAmount) {
        this.taxBaseAmount = taxBaseAmount;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public Integer getNumberOfWorkingDays() {
        return numberOfWorkingDays;
    }

    public void setNumberOfWorkingDays(Integer numberOfWorkingDays) {
        this.numberOfWorkingDays = numberOfWorkingDays;
    }

    public Integer getNumberOfWorkingHours() {
        return numberOfWorkingHours;
    }

    public void setNumberOfWorkingHours(Integer numberOfWorkingHours) {
        this.numberOfWorkingHours = numberOfWorkingHours;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getWorkPlaceId() {
        return workPlaceId;
    }

    public void setWorkPlaceId(Integer workPlaceId) {
        this.workPlaceId = workPlaceId;
    }

    public Integer getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(Integer contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    public Integer getEmployeeResidenceId() {
        return employeeResidenceId;
    }

    public void setEmployeeResidenceId(Integer employeeResidenceId) {
        this.employeeResidenceId = employeeResidenceId;
    }

    public Double getContractSalaryAmount() {
        return contractSalaryAmount;
    }

    public void setContractSalaryAmount(Double contractSalaryAmount) {
        this.contractSalaryAmount = contractSalaryAmount;
    }

    public Double getContractSalaryCoefficient() {
        return contractSalaryCoefficient;
    }

    public void setContractSalaryCoefficient(Double contractSalaryCoefficient) {
        this.contractSalaryCoefficient = contractSalaryCoefficient;
    }

    public Double getContractWorkHistoryCoeff() {
        return contractWorkHistoryCoeff;
    }

    public void setContractWorkHistoryCoeff(Double contractWorkHistoryCoeff) {
        this.contractWorkHistoryCoeff = contractWorkHistoryCoeff;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Long getPayrollSettingsId() {
        return payrollSettingsId;
    }

    public void setPayrollSettingsId(Long prPayrollSettingsId) {
        this.payrollSettingsId = prPayrollSettingsId;
    }

    public Long getSalaryTypeId() {
        return salaryTypeId;
    }

    public void setSalaryTypeId(Long prSalaryTypesId) {
        this.salaryTypeId = prSalaryTypesId;
    }

    public String getSalaryTypeName() {
        return salaryTypeName;
    }

    public void setSalaryTypeName(String prSalaryTypesName) {
        this.salaryTypeName = prSalaryTypesName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PrEmpSalariesDTO prEmpSalariesDTO = (PrEmpSalariesDTO) o;
        if(prEmpSalariesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prEmpSalariesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrEmpSalariesDTO{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", calculationNumber=" + getCalculationNumber() +
            ", employeeId=" + getEmployeeId() +
            ", grossAmount=" + getGrossAmount() +
            ", netAmount=" + getNetAmount() +
            ", taxRelief=" + getTaxRelief() +
            ", taxBaseAmount=" + getTaxBaseAmount() +
            ", paymentAmount=" + getPaymentAmount() +
            ", totalDays=" + getTotalDays() +
            ", totalHours=" + getTotalHours() +
            ", numberOfWorkingDays=" + getNumberOfWorkingDays() +
            ", numberOfWorkingHours=" + getNumberOfWorkingHours() +
            ", organizationId=" + getOrganizationId() +
            ", workPlaceId=" + getWorkPlaceId() +
            ", contractTypeId=" + getContractTypeId() +
            ", employeeResidenceId=" + getEmployeeResidenceId() +
            ", contractSalaryAmount=" + getContractSalaryAmount() +
            ", contractSalaryCoefficient=" + getContractSalaryCoefficient() +
            ", contractWorkHistoryCoeff=" + getContractWorkHistoryCoeff() +
            ", yearsOfExperience=" + getYearsOfExperience() +
            "}";
    }
}
