package ba.infostudio.com.service.dto;


import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the PrEmployeeSuspensions entity.
 */
public class PrEmployeeSuspensionsDTO implements Serializable {

    private Long id;

    private Integer creditorId;

    @NotNull
    private Integer employeeId;

    private String accountNumber;

    private Double totalDebt;

    private Double restDebt;

    private Double amountPartDebt;

    private Integer totalNumberOfParts;

    private Integer restNumberOfParts;

    private String status;

    private String description;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private Long suspensionId;

    private String suspensionName;

    private Long suspensionTypeId;

    private String suspensionTypeName;

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

    public Integer getCreditorId() {
        return creditorId;
    }

    public void setCreditorId(Integer creditorId) {
        this.creditorId = creditorId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(Double totalDebt) {
        this.totalDebt = totalDebt;
    }

    public Double getRestDebt() {
        return restDebt;
    }

    public void setRestDebt(Double restDebt) {
        this.restDebt = restDebt;
    }

    public Double getAmountPartDebt() {
        return amountPartDebt;
    }

    public void setAmountPartDebt(Double amountPartDebt) {
        this.amountPartDebt = amountPartDebt;
    }

    public Integer getTotalNumberOfParts() {
        return totalNumberOfParts;
    }

    public void setTotalNumberOfParts(Integer totalNumberOfParts) {
        this.totalNumberOfParts = totalNumberOfParts;
    }

    public Integer getRestNumberOfParts() {
        return restNumberOfParts;
    }

    public void setRestNumberOfParts(Integer restNumberOfParts) {
        this.restNumberOfParts = restNumberOfParts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getSuspensionId() {
        return suspensionId;
    }

    public void setSuspensionId(Long prSuspensionsId) {
        this.suspensionId = prSuspensionsId;
    }

    public String getSuspensionName() {
        return suspensionName;
    }

    public void setSuspensionName(String prSuspensionsName) {
        this.suspensionName = prSuspensionsName;
    }

    public Long getSuspensionTypeId() {
        return suspensionTypeId;
    }

    public void setSuspensionTypeId(Long prSuspensionTypesId) {
        this.suspensionTypeId = prSuspensionTypesId;
    }

    public String getSuspensionTypeName() {
        return suspensionTypeName;
    }

    public void setSuspensionTypeName(String prSuspensionTypesName) {
        this.suspensionTypeName = prSuspensionTypesName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PrEmployeeSuspensionsDTO prEmployeeSuspensionsDTO = (PrEmployeeSuspensionsDTO) o;
        if(prEmployeeSuspensionsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prEmployeeSuspensionsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrEmployeeSuspensionsDTO{" +
            "id=" + getId() +
            ", creditorId=" + getCreditorId() +
            ", employeeId=" + getEmployeeId() +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", totalDebt=" + getTotalDebt() +
            ", restDebt=" + getRestDebt() +
            ", amountPartDebt=" + getAmountPartDebt() +
            ", totalNumberOfParts=" + getTotalNumberOfParts() +
            ", restNumberOfParts=" + getRestNumberOfParts() +
            ", status='" + getStatus() + "'" +
            ", description='" + getDescription() + "'" +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            "}";
    }
}
