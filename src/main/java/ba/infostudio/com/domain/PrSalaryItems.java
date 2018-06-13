package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PrSalaryItems.
 */
@Entity
@Table(name = "pr_salary_items")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PrSalaryItems extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "taxable")
    private String taxable;

    @Column(name = "base")
    private Double base;

    @Column(name = "percentage_payment")
    private String percentagePayment;

    @Column(name = "mip_number")
    private Integer mipNumber;

    @Column(name = "absence_type_id")
    private Integer absenceTypeId;

    @Column(name = "status")
    private String status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public PrSalaryItems code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public PrSalaryItems name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public PrSalaryItems description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaxable() {
        return taxable;
    }

    public PrSalaryItems taxable(String taxable) {
        this.taxable = taxable;
        return this;
    }

    public void setTaxable(String taxable) {
        this.taxable = taxable;
    }

    public Double getBase() {
        return base;
    }

    public PrSalaryItems base(Double base) {
        this.base = base;
        return this;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    public String getPercentagePayment() {
        return percentagePayment;
    }

    public PrSalaryItems percentagePayment(String percentagePayment) {
        this.percentagePayment = percentagePayment;
        return this;
    }

    public void setPercentagePayment(String percentagePayment) {
        this.percentagePayment = percentagePayment;
    }

    public Integer getMipNumber() {
        return mipNumber;
    }

    public PrSalaryItems mipNumber(Integer mipNumber) {
        this.mipNumber = mipNumber;
        return this;
    }

    public void setMipNumber(Integer mipNumber) {
        this.mipNumber = mipNumber;
    }

    public Integer getAbsenceTypeId() {
        return absenceTypeId;
    }

    public PrSalaryItems absenceTypeId(Integer absenceTypeId) {
        this.absenceTypeId = absenceTypeId;
        return this;
    }

    public void setAbsenceTypeId(Integer absenceTypeId) {
        this.absenceTypeId = absenceTypeId;
    }

    public String getStatus() {
        return status;
    }

    public PrSalaryItems status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PrSalaryItems prSalaryItems = (PrSalaryItems) o;
        if (prSalaryItems.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prSalaryItems.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrSalaryItems{" +
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
