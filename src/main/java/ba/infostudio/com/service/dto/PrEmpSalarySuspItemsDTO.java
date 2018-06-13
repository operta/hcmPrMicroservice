package ba.infostudio.com.service.dto;


import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the PrEmpSalarySuspItems entity.
 */
public class PrEmpSalarySuspItemsDTO implements Serializable {

    private Long id;

    private Double amount;

    private Integer restDebt;

    private Integer partNumber;

    private Integer restNumberOfParts;

    private Long employeeSalaryId;

    private Long empSuspensionId;

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

    public Integer getRestDebt() {
        return restDebt;
    }

    public void setRestDebt(Integer restDebt) {
        this.restDebt = restDebt;
    }

    public Integer getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(Integer partNumber) {
        this.partNumber = partNumber;
    }

    public Integer getRestNumberOfParts() {
        return restNumberOfParts;
    }

    public void setRestNumberOfParts(Integer restNumberOfParts) {
        this.restNumberOfParts = restNumberOfParts;
    }

    public Long getEmployeeSalaryId() {
        return employeeSalaryId;
    }

    public void setEmployeeSalaryId(Long prEmpSalariesId) {
        this.employeeSalaryId = prEmpSalariesId;
    }

    public Long getEmpSuspensionId() {
        return empSuspensionId;
    }

    public void setEmpSuspensionId(Long prEmployeeSuspensionsId) {
        this.empSuspensionId = prEmployeeSuspensionsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PrEmpSalarySuspItemsDTO prEmpSalarySuspItemsDTO = (PrEmpSalarySuspItemsDTO) o;
        if(prEmpSalarySuspItemsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prEmpSalarySuspItemsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrEmpSalarySuspItemsDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", restDebt=" + getRestDebt() +
            ", partNumber=" + getPartNumber() +
            ", restNumberOfParts=" + getRestNumberOfParts() +
            "}";
    }
}
