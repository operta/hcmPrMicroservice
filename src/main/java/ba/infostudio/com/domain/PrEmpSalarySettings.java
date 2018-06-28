package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PrEmpSalarySettings.
 */
@Entity
@Table(name = "pr_emp_salary_settings")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PrEmpSalarySettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "year", nullable = false)
    private Integer year;

    @NotNull
    @Column(name = "month", nullable = false)
    private Integer month;

    @NotNull
    @Column(name = "calculation_number", nullable = false)
    private String calculationNumber;

    @NotNull
    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "number_of_hours")
    private Integer numberOfHours;

    @Column(name = "number_of_days")
    private Integer numberOfDays;

    @Column(name = "hourly_rate")
    private Double hourlyRate;

    @Column(name = "standard_horly_rate")
    private Double standardHourlyRate;

    @OneToOne
    @JoinColumn(name = "salary_type_id")
    private PrSalaryTypes salaryType;

    @OneToOne
    @JoinColumn(name = "payroll_settings_id")
    private PrPayrollSettings payrollSettings;

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

    public Integer getYear() {
        return year;
    }

    public PrEmpSalarySettings year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public PrEmpSalarySettings month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getCalculationNumber() {
        return calculationNumber;
    }

    public PrEmpSalarySettings calculationNumber(String calculationNumber) {
        this.calculationNumber = calculationNumber;
        return this;
    }

    public void setCalculationNumber(String calculationNumber) {
        this.calculationNumber = calculationNumber;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public PrEmpSalarySettings employeeId(Integer employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Double getAmount() {
        return amount;
    }

    public PrEmpSalarySettings amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getNumberOfHours() {
        return numberOfHours;
    }

    public PrEmpSalarySettings numberOfHours(Integer numberOfHours) {
        this.numberOfHours = numberOfHours;
        return this;
    }

    public void setNumberOfHours(Integer numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public PrEmpSalarySettings numberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
        return this;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public PrEmpSalarySettings hourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
        return this;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Double getStandardHourlyRate() {
        return standardHourlyRate;
    }

    public PrEmpSalarySettings standardHourlyRate(Double standardHourlyRate) {
        this.standardHourlyRate = standardHourlyRate;
        return this;
    }

    public void setStandardHourlyRate(Double standardHourlyRate) {
        this.standardHourlyRate = standardHourlyRate;
    }

    public PrSalaryTypes getSalaryType() {
        return salaryType;
    }

    public PrEmpSalarySettings salaryType(PrSalaryTypes prSalaryTypes) {
        this.salaryType = prSalaryTypes;
        return this;
    }

    public void setSalaryType(PrSalaryTypes prSalaryTypes) {
        this.salaryType = prSalaryTypes;
    }

    public PrPayrollSettings getPayrollSettings() {
        return payrollSettings;
    }

    public PrEmpSalarySettings payrollSettings(PrPayrollSettings prPayrollSettings) {
        this.payrollSettings = prPayrollSettings;
        return this;
    }

    public void setPayrollSettings(PrPayrollSettings prPayrollSettings) {
        this.payrollSettings = prPayrollSettings;
    }

    public PrSalaryItems getSalaryItem() {
        return salaryItem;
    }

    public PrEmpSalarySettings salaryItem(PrSalaryItems prSalaryItems) {
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
        PrEmpSalarySettings prEmpSalarySettings = (PrEmpSalarySettings) o;
        if (prEmpSalarySettings.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prEmpSalarySettings.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrEmpSalarySettings{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", calculationNumber=" + getCalculationNumber() +
            ", employeeId=" + getEmployeeId() +
            ", amount=" + getAmount() +
            ", numberOfHours=" + getNumberOfHours() +
            ", numberOfDays=" + getNumberOfDays() +
            ", hourlyRate=" + getHourlyRate() +
            ", standardHourlyRate=" + getStandardHourlyRate() +
            ", salaryItemId = " + getSalaryItem().getId() +
            "}";
    }
}
