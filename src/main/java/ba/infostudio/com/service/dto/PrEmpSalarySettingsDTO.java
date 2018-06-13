package ba.infostudio.com.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the PrEmpSalarySettings entity.
 */
public class PrEmpSalarySettingsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer year;

    @NotNull
    private Integer month;

    @NotNull
    private Integer calculationNumber;

    @NotNull
    private Integer employeeId;

    private Double amount;

    private Integer numberOfHours;

    private Integer numberOfDays;

    private Double hourlyRate;

    private Double standardHourlyRate;

    private Long salaryTypeId;

    private String salaryTypeName;

    private Long payrollSettingsId;

    private Long salaryItemId;

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(Integer numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Double getStandardHourlyRate() {
        return standardHourlyRate;
    }

    public void setStandardHourlyRate(Double standardHourlyRate) {
        this.standardHourlyRate = standardHourlyRate;
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

    public Long getPayrollSettingsId() {
        return payrollSettingsId;
    }

    public void setPayrollSettingsId(Long prPayrollSettingsId) {
        this.payrollSettingsId = prPayrollSettingsId;
    }

    public Long getSalaryItemId() {
        return salaryItemId;
    }

    public void setSalaryItemId(Long prSalaryItemsId) {
        this.salaryItemId = prSalaryItemsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PrEmpSalarySettingsDTO prEmpSalarySettingsDTO = (PrEmpSalarySettingsDTO) o;
        if(prEmpSalarySettingsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prEmpSalarySettingsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrEmpSalarySettingsDTO{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", calculationNumber=" + getCalculationNumber() +
            ", employeeId=" + getEmployeeId() +
            ", amount=" + getAmount() +
            ", numberOfHours=" + getNumberOfHours() +
            ", numberOfDays=" + getNumberOfDays() +
            ", hourlyRate=" + getHourlyRate() +
            ", standardHourlyRate=" + getStandardHourlyRate() +
            "}";
    }
}
