package ba.infostudio.com.service.dto;


import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the PrEmpSalaryTaxItems entity.
 */
public class PrEmpSalaryTaxItemsDTO implements Serializable {

    private Long id;

    private Double amount;

    private Integer taxBase;

    private String typeOfIncome;

    private Integer taxRegionId;

    private Long employeeSalaryId;

    private Long taxId;

    private String taxName;

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getTaxBase() {
        return taxBase;
    }

    public void setTaxBase(Integer taxBase) {
        this.taxBase = taxBase;
    }

    public String getTypeOfIncome() {
        return typeOfIncome;
    }

    public void setTypeOfIncome(String typeOfIncome) {
        this.typeOfIncome = typeOfIncome;
    }

    public Integer getTaxRegionId() {
        return taxRegionId;
    }

    public void setTaxRegionId(Integer taxRegionId) {
        this.taxRegionId = taxRegionId;
    }

    public Long getEmployeeSalaryId() {
        return employeeSalaryId;
    }

    public void setEmployeeSalaryId(Long prEmpSalariesId) {
        this.employeeSalaryId = prEmpSalariesId;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long prTaxesId) {
        this.taxId = prTaxesId;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String prTaxesName) {
        this.taxName = prTaxesName;
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

        PrEmpSalaryTaxItemsDTO prEmpSalaryTaxItemsDTO = (PrEmpSalaryTaxItemsDTO) o;
        if(prEmpSalaryTaxItemsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prEmpSalaryTaxItemsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrEmpSalaryTaxItemsDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", taxBase=" + getTaxBase() +
            ", typeOfIncome='" + getTypeOfIncome() + "'" +
            ", taxRegionId=" + getTaxRegionId() +
            "}";
    }
}
