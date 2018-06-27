package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A EmEmpSalaries.
 */
@Entity
@Table(name = "em_emp_salaries")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmEmpSalaries implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "date_from", nullable = false)
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @Column(name = "salary_amount")
    private Double salaryAmount;

    @Column(name = "salary_coefficient")
    private Double salaryCoefficient;

    @Column(name = "work_history_coefficient")
    private Double workHistoryCoefficient;

    @Column(name = "id_work_place")
    private Integer idWorkPlace;

    @OneToOne
    @JoinColumn(name ="id_employee")
    private EmEmployees idEmployee;

    @OneToOne
    @JoinColumn(name ="id_contract_type")
    private EmContractTypes idContractType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public EmEmpSalaries dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public EmEmpSalaries dateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Double getSalaryAmount() {
        return salaryAmount;
    }

    public EmEmpSalaries salaryAmount(Double salaryAmount) {
        this.salaryAmount = salaryAmount;
        return this;
    }

    public void setSalaryAmount(Double salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public Double getSalaryCoefficient() {
        return salaryCoefficient;
    }

    public EmEmpSalaries salaryCoefficient(Double salaryCoefficient) {
        this.salaryCoefficient = salaryCoefficient;
        return this;
    }

    public void setSalaryCoefficient(Double salaryCoefficient) {
        this.salaryCoefficient = salaryCoefficient;
    }

    public Double getWorkHistoryCoefficient() {
        return workHistoryCoefficient;
    }

    public EmEmpSalaries workHistoryCoefficient(Double workHistoryCoefficient) {
        this.workHistoryCoefficient = workHistoryCoefficient;
        return this;
    }

    public void setWorkHistoryCoefficient(Double workHistoryCoefficient) {
        this.workHistoryCoefficient = workHistoryCoefficient;
    }


    public Integer getIdWorkPlace() {
        return idWorkPlace;
    }

    public EmEmpSalaries idWorkPlace(Integer idWorkPlace) {
        this.idWorkPlace = idWorkPlace;
        return this;
    }

    public void setIdWorkPlace(Integer idWorkPlace) {
        this.idWorkPlace = idWorkPlace;
    }

    public EmEmployees getIdEmployee() {
        return idEmployee;
    }

    public EmEmpSalaries idEmployee(EmEmployees emEmployees) {
        this.idEmployee = emEmployees;
        return this;
    }

    public void setIdEmployee(EmEmployees emEmployees) {
        this.idEmployee = emEmployees;
    }

    public EmContractTypes getIdContractType() {
        return idContractType;
    }

    public EmEmpSalaries idContractType(EmContractTypes emContractTypes) {
        this.idContractType = emContractTypes;
        return this;
    }

    public void setIdContractType(EmContractTypes emContractTypes) {
        this.idContractType = emContractTypes;
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
        EmEmpSalaries emEmpSalaries = (EmEmpSalaries) o;
        if (emEmpSalaries.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emEmpSalaries.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmEmpSalaries{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", salaryAmount=" + getSalaryAmount() +
            ", salaryCoefficient=" + getSalaryCoefficient() +
            ", workHistoryCoefficient=" + getWorkHistoryCoefficient() +
            ", idWorkPlace=" + getIdWorkPlace() +
            "}";
    }
}
