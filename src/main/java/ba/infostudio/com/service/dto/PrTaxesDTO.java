package ba.infostudio.com.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the PrTaxes entity.
 */
public class PrTaxesDTO implements Serializable {

    private Long id;

    private String code;

    @NotNull
    private String name;

    private String description;

    private Double base;

    private String status;

    private String typeOfIncome;

    private Long taxBaseId;

    private String taxBaseName;

    private Long taxLevelId;

    private String taxLevelName;

    private Long taxTypeId;

    private String taxTypeName;

    private Long taxLinkId;

    private String taxLinkName;

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

    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeOfIncome() {
        return typeOfIncome;
    }

    public void setTypeOfIncome(String typeOfIncome) {
        this.typeOfIncome = typeOfIncome;
    }

    public Long getTaxBaseId() {
        return taxBaseId;
    }

    public void setTaxBaseId(Long prTaxBaseId) {
        this.taxBaseId = prTaxBaseId;
    }

    public String getTaxBaseName() {
        return taxBaseName;
    }

    public void setTaxBaseName(String prTaxBaseName) {
        this.taxBaseName = prTaxBaseName;
    }

    public Long getTaxLevelId() {
        return taxLevelId;
    }

    public void setTaxLevelId(Long prTaxLevelPaymentsId) {
        this.taxLevelId = prTaxLevelPaymentsId;
    }

    public String getTaxLevelName() {
        return taxLevelName;
    }

    public void setTaxLevelName(String prTaxLevelPaymentsName) {
        this.taxLevelName = prTaxLevelPaymentsName;
    }

    public Long getTaxTypeId() {
        return taxTypeId;
    }

    public void setTaxTypeId(Long prTypeOfTaxesId) {
        this.taxTypeId = prTypeOfTaxesId;
    }

    public String getTaxTypeName() {
        return taxTypeName;
    }

    public void setTaxTypeName(String prTypeOfTaxesName) {
        this.taxTypeName = prTypeOfTaxesName;
    }

    public Long getTaxLinkId() {
        return taxLinkId;
    }

    public void setTaxLinkId(Long prTaxLinkId) {
        this.taxLinkId = prTaxLinkId;
    }

    public String getTaxLinkName() {
        return taxLinkName;
    }

    public void setTaxLinkName(String prTaxLinkName) {
        this.taxLinkName = prTaxLinkName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PrTaxesDTO prTaxesDTO = (PrTaxesDTO) o;
        if(prTaxesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prTaxesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrTaxesDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", base=" + getBase() +
            ", status='" + getStatus() + "'" +
            ", typeOfIncome='" + getTypeOfIncome() + "'" +
            "}";
    }
}
