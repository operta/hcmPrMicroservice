package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PrEmpSalarySuspItems.
 */
@Entity
@Table(name = "pr_emp_salary_susp_items")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PrEmpSalarySuspItems extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "rest_debt")
    private Integer restDebt;

    @Column(name = "part_number")
    private Integer partNumber;

    @Column(name = "rest_number_of_parts")
    private Integer restNumberOfParts;

    @OneToOne
    @JoinColumn(name = "employee_salary_id")
    private PrEmpSalaries employeeSalary;

    @OneToOne
    @JoinColumn(name = "emp_suspension_id")
    private PrEmployeeSuspensions empSuspension;

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

    public PrEmpSalarySuspItems amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getRestDebt() {
        return restDebt;
    }

    public PrEmpSalarySuspItems restDebt(Integer restDebt) {
        this.restDebt = restDebt;
        return this;
    }

    public void setRestDebt(Integer restDebt) {
        this.restDebt = restDebt;
    }

    public Integer getPartNumber() {
        return partNumber;
    }

    public PrEmpSalarySuspItems partNumber(Integer partNumber) {
        this.partNumber = partNumber;
        return this;
    }

    public void setPartNumber(Integer partNumber) {
        this.partNumber = partNumber;
    }

    public Integer getRestNumberOfParts() {
        return restNumberOfParts;
    }

    public PrEmpSalarySuspItems restNumberOfParts(Integer restNumberOfParts) {
        this.restNumberOfParts = restNumberOfParts;
        return this;
    }

    public void setRestNumberOfParts(Integer restNumberOfParts) {
        this.restNumberOfParts = restNumberOfParts;
    }

    public PrEmpSalaries getEmployeeSalary() {
        return employeeSalary;
    }

    public PrEmpSalarySuspItems employeeSalary(PrEmpSalaries prEmpSalaries) {
        this.employeeSalary = prEmpSalaries;
        return this;
    }

    public void setEmployeeSalary(PrEmpSalaries prEmpSalaries) {
        this.employeeSalary = prEmpSalaries;
    }

    public PrEmployeeSuspensions getEmpSuspension() {
        return empSuspension;
    }

    public PrEmpSalarySuspItems empSuspension(PrEmployeeSuspensions prEmployeeSuspensions) {
        this.empSuspension = prEmployeeSuspensions;
        return this;
    }

    public void setEmpSuspension(PrEmployeeSuspensions prEmployeeSuspensions) {
        this.empSuspension = prEmployeeSuspensions;
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
        PrEmpSalarySuspItems prEmpSalarySuspItems = (PrEmpSalarySuspItems) o;
        if (prEmpSalarySuspItems.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prEmpSalarySuspItems.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrEmpSalarySuspItems{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", restDebt=" + getRestDebt() +
            ", partNumber=" + getPartNumber() +
            ", restNumberOfParts=" + getRestNumberOfParts() +
            "}";
    }
}
