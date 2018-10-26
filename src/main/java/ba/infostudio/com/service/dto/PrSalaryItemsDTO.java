package ba.infostudio.com.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the PrSalaryItems entity.
 */
public class PrSalaryItemsDTO implements Serializable {

    private Long id;

    private String code;

    @NotNull
    private String name;

    private String description;

    private String taxable;

    private Double base;

    private String percentagePayment;

    private Integer mipNumber;

    private Integer absenceTypeId;

    private String status;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaxable() {
        return taxable;
    }

    public void setTaxable(String taxable) {
        this.taxable = taxable;
    }

    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    public String getPercentagePayment() {
        return percentagePayment;
    }

    public void setPercentagePayment(String percentagePayment) {
        this.percentagePayment = percentagePayment;
    }

    public Integer getMipNumber() {
        return mipNumber;
    }

    public void setMipNumber(Integer mipNumber) {
        this.mipNumber = mipNumber;
    }

    public Integer getAbsenceTypeId() {
        return absenceTypeId;
    }

    public void setAbsenceTypeId(Integer absenceTypeId) {
        this.absenceTypeId = absenceTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PrSalaryItemsDTO prSalaryItemsDTO = (PrSalaryItemsDTO) o;
        if(prSalaryItemsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prSalaryItemsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrSalaryItemsDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", taxable='" + getTaxable() + "'" +
            ", base=" + getBase() +
            ", percentagePayment='" + getPercentagePayment() + "'" +
            ", mipNumber=" + getMipNumber() +
            ", absenceTypeId=" + getAbsenceTypeId() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
