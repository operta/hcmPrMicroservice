package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PrEmpSalaryTaxItems.
 */
@Entity
@Table(name = "pr_emp_salary_tax_items")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PrEmpSalaryTaxItems extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "tax_base")
    private Integer taxBase;

    @Column(name = "type_of_income")
    private String typeOfIncome;

    @Column(name = "tax_region_id")
    private Integer taxRegionId;

    @OneToOne
    @JoinColumn(name = "employee_salary_id")
    private PrEmpSalaries employeeSalary;

    @OneToOne
    @JoinColumn(name = "tax_id")
    private PrTaxes tax;

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

    public Double getAmount() {
        return amount;
    }

    public PrEmpSalaryTaxItems amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getTaxBase() {
        return taxBase;
    }

    public PrEmpSalaryTaxItems taxBase(Integer taxBase) {
        this.taxBase = taxBase;
        return this;
    }

    public void setTaxBase(Integer taxBase) {
        this.taxBase = taxBase;
    }

    public String getTypeOfIncome() {
        return typeOfIncome;
    }

    public PrEmpSalaryTaxItems typeOfIncome(String typeOfIncome) {
        this.typeOfIncome = typeOfIncome;
        return this;
    }

    public void setTypeOfIncome(String typeOfIncome) {
        this.typeOfIncome = typeOfIncome;
    }

    public Integer getTaxRegionId() {
        return taxRegionId;
    }

    public PrEmpSalaryTaxItems taxRegionId(Integer taxRegionId) {
        this.taxRegionId = taxRegionId;
        return this;
    }

    public void setTaxRegionId(Integer taxRegionId) {
        this.taxRegionId = taxRegionId;
    }

    public PrEmpSalaries getEmployeeSalary() {
        return employeeSalary;
    }

    public PrEmpSalaryTaxItems employeeSalary(PrEmpSalaries prEmpSalaries) {
        this.employeeSalary = prEmpSalaries;
        return this;
    }

    public void setEmployeeSalary(PrEmpSalaries prEmpSalaries) {
        this.employeeSalary = prEmpSalaries;
    }

    public PrTaxes getTax() {
        return tax;
    }

    public PrEmpSalaryTaxItems tax(PrTaxes prTaxes) {
        this.tax = prTaxes;
        return this;
    }

    public void setTax(PrTaxes prTaxes) {
        this.tax = prTaxes;
    }

    public PrTaxLink getTaxLink() {
        return taxLink;
    }

    public PrEmpSalaryTaxItems taxLink(PrTaxLink prTaxLink) {
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
        PrEmpSalaryTaxItems prEmpSalaryTaxItems = (PrEmpSalaryTaxItems) o;
        if (prEmpSalaryTaxItems.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prEmpSalaryTaxItems.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrEmpSalaryTaxItems{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", taxBase=" + getTaxBase() +
            ", typeOfIncome='" + getTypeOfIncome() + "'" +
            ", taxRegionId=" + getTaxRegionId() +
            "}";
    }
}
