package ba.infostudio.com.service.dto;


import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the PrEmpSalaryItems entity.
 */
public class PrEmpSalaryItemsDTO implements Serializable {

    private Long id;

    private Double amount;

    private Integer numberOfHours;

    private Integer numberOfDays;

    private Double hourlyRate;

    private Double standardHourlyRate;

    private Long employeeSalaryId;

    private Long salaryItemId;

    private String salaryItemName;

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

    public Long getEmployeeSalaryId() {
        return employeeSalaryId;
    }

    public void setEmployeeSalaryId(Long prEmpSalariesId) {
        this.employeeSalaryId = prEmpSalariesId;
    }

    public Long getSalaryItemId() {
        return salaryItemId;
    }

    public void setSalaryItemId(Long prSalaryItemsId) {
        this.salaryItemId = prSalaryItemsId;
    }

    public String getSalaryItemName() {
        return salaryItemName;
    }

    public void setSalaryItemName(String prSalaryItemsName) {
        this.salaryItemName = prSalaryItemsName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PrEmpSalaryItemsDTO prEmpSalaryItemsDTO = (PrEmpSalaryItemsDTO) o;
        if(prEmpSalaryItemsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prEmpSalaryItemsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrEmpSalaryItemsDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", numberOfHours=" + getNumberOfHours() +
            ", numberOfDays=" + getNumberOfDays() +
            ", hourlyRate=" + getHourlyRate() +
            ", standardHourlyRate=" + getStandardHourlyRate() +
            "}";
    }
}
