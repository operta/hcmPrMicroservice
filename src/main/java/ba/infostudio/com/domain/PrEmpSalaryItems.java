package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PrEmpSalaryItems.
 */
@Entity
@Table(name = "pr_emp_salary_items")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PrEmpSalaryItems extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "number_of_hours")
    private Integer numberOfHours;

    @Column(name = "number_of_days")
    private Integer numberOfDays;

    @Column(name = "hourly_rate")
    private Double hourlyRate;

    @Column(name = "standard_hourly_rate")
    private Double standardHourlyRate;

    @OneToOne
    @JoinColumn(name = "employee_salary_id")
    private PrEmpSalaries employeeSalary;

    @OneToOne
    @JoinColumn(name = "salary_item_id")
    private PrSalaryItems salaryItem;

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

    public PrEmpSalaryItems amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getNumberOfHours() {
        return numberOfHours;
    }

    public PrEmpSalaryItems numberOfHours(Integer numberOfHours) {
        this.numberOfHours = numberOfHours;
        return this;
    }

    public void setNumberOfHours(Integer numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public PrEmpSalaryItems numberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
        return this;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public PrEmpSalaryItems hourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
        return this;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Double getStandardHourlyRate() {
        return standardHourlyRate;
    }

    public PrEmpSalaryItems standardHourlyRate(Double standardHourlyRate) {
        this.standardHourlyRate = standardHourlyRate;
        return this;
    }

    public void setStandardHourlyRate(Double standardHourlyRate) {
        this.standardHourlyRate = standardHourlyRate;
    }

    public PrEmpSalaries getEmployeeSalary() {
        return employeeSalary;
    }

    public PrEmpSalaryItems employeeSalary(PrEmpSalaries prEmpSalaries) {
        this.employeeSalary = prEmpSalaries;
        return this;
    }

    public void setEmployeeSalary(PrEmpSalaries prEmpSalaries) {
        this.employeeSalary = prEmpSalaries;
    }

    public PrSalaryItems getSalaryItem() {
        return salaryItem;
    }

    public PrEmpSalaryItems salaryItem(PrSalaryItems prSalaryItems) {
        this.salaryItem = prSalaryItems;
        return this;
    }

    public void setSalaryItem(PrSalaryItems prSalaryItems) {
        this.salaryItem = prSalaryItems;
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
        PrEmpSalaryItems prEmpSalaryItems = (PrEmpSalaryItems) o;
        if (prEmpSalaryItems.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prEmpSalaryItems.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrEmpSalaryItems{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", numberOfHours=" + getNumberOfHours() +
            ", numberOfDays=" + getNumberOfDays() +
            ", hourlyRate=" + getHourlyRate() +
            ", standardHourlyRate=" + getStandardHourlyRate() +
            "}";
    }
}
