package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PrTaxes.
 */
@Entity
@Table(name = "pr_taxes")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PrTaxes extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "base")
    private Double base;

    @Column(name = "status")
    private String status;

    @Column(name = "type_of_income")
    private String typeOfIncome;

    @OneToOne
    @JoinColumn(name = "tax_base_id")
    private PrTaxBase taxBase;

    @OneToOne
    @JoinColumn(name = "tax_level_id")
    private PrTaxLevelPayments taxLevel;

    @OneToOne
    @JoinColumn(name = "tax_type_id")
    private PrTypeOfTaxes taxType;

    @OneToOne
    @JoinColumn(name = "tax_link_id")
    private PrTaxLink taxLink;

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

    public PrTaxes code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public PrTaxes name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public PrTaxes description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBase() {
        return base;
    }

    public PrTaxes base(Double base) {
        this.base = base;
        return this;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    public String getStatus() {
        return status;
    }

    public PrTaxes status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeOfIncome() {
        return typeOfIncome;
    }

    public PrTaxes typeOfIncome(String typeOfIncome) {
        this.typeOfIncome = typeOfIncome;
        return this;
    }

    public void setTypeOfIncome(String typeOfIncome) {
        this.typeOfIncome = typeOfIncome;
    }

    public PrTaxBase getTaxBase() {
        return taxBase;
    }

    public PrTaxes taxBase(PrTaxBase prTaxBase) {
        this.taxBase = prTaxBase;
        return this;
    }

    public void setTaxBase(PrTaxBase prTaxBase) {
        this.taxBase = prTaxBase;
    }

    public PrTaxLevelPayments getTaxLevel() {
        return taxLevel;
    }

    public PrTaxes taxLevel(PrTaxLevelPayments prTaxLevelPayments) {
        this.taxLevel = prTaxLevelPayments;
        return this;
    }

    public void setTaxLevel(PrTaxLevelPayments prTaxLevelPayments) {
        this.taxLevel = prTaxLevelPayments;
    }

    public PrTypeOfTaxes getTaxType() {
        return taxType;
    }

    public PrTaxes taxType(PrTypeOfTaxes prTypeOfTaxes) {
        this.taxType = prTypeOfTaxes;
        return this;
    }

    public void setTaxType(PrTypeOfTaxes prTypeOfTaxes) {
        this.taxType = prTypeOfTaxes;
    }

    public PrTaxLink getTaxLink() {
        return taxLink;
    }

    public PrTaxes taxLink(PrTaxLink prTaxLink) {
        this.taxLink = prTaxLink;
        return this;
    }

    public void setTaxLink(PrTaxLink prTaxLink) {
        this.taxLink = prTaxLink;
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
        PrTaxes prTaxes = (PrTaxes) o;
        if (prTaxes.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prTaxes.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrTaxes{" +
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
