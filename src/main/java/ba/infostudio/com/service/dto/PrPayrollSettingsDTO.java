package ba.infostudio.com.service.dto;


import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the PrPayrollSettings entity.
 */
public class PrPayrollSettingsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer year;

    @NotNull
    private Integer month;

    @NotNull
    private String calculationNumber;

    private LocalDate paymentDate;

    private LocalDate calculationDate;

    private Integer numberOfWorkingDays;

    private Integer numberOfWorkingHours;

    private String calculated;

    private String paid;

    private Integer companyId;

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

    public String getCalculationNumber() {
        return calculationNumber;
    }

    public void setCalculationNumber(String calculationNumber) {
        this.calculationNumber = calculationNumber;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getCalculationDate() {
        return calculationDate;
    }

    public void setCalculationDate(LocalDate calculationDate) {
        this.calculationDate = calculationDate;
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

    public void setNumberOfWorkingHours(Integer numberOfWOrkingHourse) {
        this.numberOfWorkingHours = numberOfWOrkingHourse;
    }

    public String getCalculated() {
        return calculated;
    }

    public void setCalculated(String calculated) {
        this.calculated = calculated;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

        PrPayrollSettingsDTO prPayrollSettingsDTO = (PrPayrollSettingsDTO) o;
        if(prPayrollSettingsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prPayrollSettingsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrPayrollSettingsDTO{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", calculationNumber=" + getCalculationNumber() +
            ", paymentDate='" + getPaymentDate() + "'" +
            ", calculationDate='" + getCalculationDate() + "'" +
            ", numberOfWorkingDays=" + getNumberOfWorkingDays() +
            ", calculated='" + getCalculated() + "'" +
            ", paid='" + getPaid() + "'" +
            ", companyId=" + getCompanyId() +
            "}";
    }
}
