package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PrEmpSalaries.
 */
@Entity
@Table(name = "pr_emp_salaries")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PrEmpSalaries extends AbstractAuditingEntity implements Serializable {

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

    @Column(name = "gross_amount")
    private Double grossAmount;

    @Column(name = "net_amount")
    private Double netAmount;

    @Column(name = "tax_relief")
    private Double taxRelief;

    @Column(name = "tax_base_amount")
    private Double taxBaseAmount;

    @Column(name = "payment_amount")
    private Double paymentAmount;

    @Column(name = "total_days")
    private Integer totalDays;

    @Column(name = "total_hours")
    private Integer totalHours;

    @Column(name = "number_of_working_days")
    private Integer numberOfWorkingDays;

    @Column(name = "number_of_workig_hours")
    private Integer numberOfWorkingHours;

    @Column(name = "organization_id")
    private Integer organizationId;

    @Column(name = "work_place_id")
    private Integer workPlaceId;

    @Column(name = "contract_type_id")
    private Integer contractTypeId;

    @Column(name = "employee_residence_id")
    private Integer employeeResidenceId;

    @Column(name = "contract_salary_amount")
    private Double contractSalaryAmount;

    @Column(name = "contract_salary_coefficient")
    private Double contractSalaryCoefficient;

    @Column(name = "contract_work_history_coeff")
    private Double contractWorkHistoryCoeff;

    @Column(name = "years_of_expirience")
    private Integer yearsOfExperience;

    @OneToOne
    @JoinColumn(name = "payroll_settings_id")
    private PrPayrollSettings payrollSettings;

    @OneToOne
    @JoinColumn(name = "salary_type_id")
    private PrSalaryTypes salaryType;

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

    public PrEmpSalaries year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public PrEmpSalaries month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getCalculationNumber() {
        return calculationNumber;
    }

    public PrEmpSalaries calculationNumber(String calculationNumber) {
        this.calculationNumber = calculationNumber;
        return this;
    }

    public void setCalculationNumber(String calculationNumber) {
        this.calculationNumber = calculationNumber;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public PrEmpSalaries employeeId(Integer employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Double getGrossAmount() {
        return grossAmount;
    }

    public PrEmpSalaries grossAmount(Double grossAmount) {
        this.grossAmount = grossAmount;
        return this;
    }

    public void setGrossAmount(Double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public PrEmpSalaries netAmount(Double netAmount) {
        this.netAmount = netAmount;
        return this;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public Double getTaxRelief() {
        return taxRelief;
    }

    public PrEmpSalaries taxRelief(Double taxRelief) {
        this.taxRelief = taxRelief;
        return this;
    }

    public void setTaxRelief(Double taxRelief) {
        this.taxRelief = taxRelief;
    }

    public Double getTaxBaseAmount() {
        return taxBaseAmount;
    }

    public PrEmpSalaries taxBaseAmount(Double taxBaseAmount) {
        this.taxBaseAmount = taxBaseAmount;
        return this;
    }

    public void setTaxBaseAmount(Double taxBaseAmount) {
        this.taxBaseAmount = taxBaseAmount;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public PrEmpSalaries paymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public PrEmpSalaries totalDays(Integer totalDays) {
        this.totalDays = totalDays;
        return this;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public PrEmpSalaries totalHours(Integer totalHours) {
        this.totalHours = totalHours;
        return this;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public Integer getNumberOfWorkingDays() {
        return numberOfWorkingDays;
    }

    public PrEmpSalaries numberOfWorkingDays(Integer numberOfWorkingDays) {
        this.numberOfWorkingDays = numberOfWorkingDays;
        return this;
    }

    public void setNumberOfWorkingDays(Integer numberOfWorkingDays) {
        this.numberOfWorkingDays = numberOfWorkingDays;
    }

    public Integer getNumberOfWorkingHours() {
        return numberOfWorkingHours;
    }

    public PrEmpSalaries numberOfWorkingHours(Integer numberOfWorkingHours) {
        this.numberOfWorkingHours = numberOfWorkingHours;
        return this;
    }

    public void setNumberOfWorkingHours(Integer numberOfWorkingHours) {
        this.numberOfWorkingHours = numberOfWorkingHours;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public PrEmpSalaries organizationId(Integer organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getWorkPlaceId() {
        return workPlaceId;
    }

    public PrEmpSalaries workPlaceId(Integer workPlaceId) {
        this.workPlaceId = workPlaceId;
        return this;
    }

    public void setWorkPlaceId(Integer workPlaceId) {
        this.workPlaceId = workPlaceId;
    }

    public Integer getContractTypeId() {
        return contractTypeId;
    }

    public PrEmpSalaries contractTypeId(Integer contractTypeId) {
        this.contractTypeId = contractTypeId;
        return this;
    }

    public void setContractTypeId(Integer contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    public Integer getEmployeeResidenceId() {
        return employeeResidenceId;
    }

    public PrEmpSalaries employeeResidenceId(Integer employeeResidenceId) {
        this.employeeResidenceId = employeeResidenceId;
        return this;
    }

    public void setEmployeeResidenceId(Integer employeeResidenceId) {
        this.employeeResidenceId = employeeResidenceId;
    }

    public Double getContractSalaryAmount() {
        return contractSalaryAmount;
    }

    public PrEmpSalaries contractSalaryAmount(Double contractSalaryAmount) {
        this.contractSalaryAmount = contractSalaryAmount;
        return this;
    }

    public void setContractSalaryAmount(Double contractSalaryAmount) {
        this.contractSalaryAmount = contractSalaryAmount;
    }

    public Double getContractSalaryCoefficient() {
        return contractSalaryCoefficient;
    }

    public PrEmpSalaries contractSalaryCoefficient(Double contractSalaryCoefficient) {
        this.contractSalaryCoefficient = contractSalaryCoefficient;
        return this;
    }

    public void setContractSalaryCoefficient(Double contractSalaryCoefficient) {
        this.contractSalaryCoefficient = contractSalaryCoefficient;
    }

    public Double getContractWorkHistoryCoeff() {
        return contractWorkHistoryCoeff;
    }

    public PrEmpSalaries contractWorkHistoryCoeff(Double contractWorkHistoryCoeff) {
        this.contractWorkHistoryCoeff = contractWorkHistoryCoeff;
        return this;
    }

    public void setContractWorkHistoryCoeff(Double contractWorkHistoryCoeff) {
        this.contractWorkHistoryCoeff = contractWorkHistoryCoeff;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public PrEmpSalaries yearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
        return this;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public PrPayrollSettings getPayrollSettings() {
        return payrollSettings;
    }

    public PrEmpSalaries payrollSettings(PrPayrollSettings prPayrollSettings) {
        this.payrollSettings = prPayrollSettings;
        return this;
    }

    public void setPayrollSettings(PrPayrollSettings prPayrollSettings) {
        this.payrollSettings = prPayrollSettings;
    }

    public PrSalaryTypes getSalaryType() {
        return salaryType;
    }

    public PrEmpSalaries salaryType(PrSalaryTypes prSalaryTypes) {
        this.salaryType = prSalaryTypes;
        return this;
    }

    public void setSalaryType(PrSalaryTypes prSalaryTypes) {
        this.salaryType = prSalaryTypes;
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
        PrEmpSalaries prEmpSalaries = (PrEmpSalaries) o;
        if (prEmpSalaries.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prEmpSalaries.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrEmpSalaries{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", calculationNumber=" + getCalculationNumber() +
            ", employeeId=" + getEmployeeId() +
            ", grossAmount=" + getGrossAmount() +
            ", netAmount=" + getNetAmount() +
            ", taxRelief=" + getTaxRelief() +
            ", taxBaseAmount=" + getTaxBaseAmount() +
            ", paymentAmount=" + getPaymentAmount() +
            ", totalDays=" + getTotalDays() +
            ", totalHours=" + getTotalHours() +
            ", numberOfWorkingDays=" + getNumberOfWorkingDays() +
            ", numberOfWorkingHours=" + getNumberOfWorkingHours() +
            ", organizationId=" + getOrganizationId() +
            ", workPlaceId=" + getWorkPlaceId() +
            ", contractTypeId=" + getContractTypeId() +
            ", employeeResidenceId=" + getEmployeeResidenceId() +
            ", contractSalaryAmount=" + getContractSalaryAmount() +
            ", contractSalaryCoefficient=" + getContractSalaryCoefficient() +
            ", contractWorkHistoryCoeff=" + getContractWorkHistoryCoeff() +
            ", yearsOfExperience=" + getYearsOfExperience() +
            "}";
    }
}
