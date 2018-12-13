package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A PrPayrollSettings.
 */
@Entity
@Table(name = "pr_payroll_settings")
/*
@NamedStoredProcedureQueries({
 @NamedStoredProcedureQuery(
     name = "p_obracun_plata",
     procedureName = "PKG_OBRACUN_PLATA_FBIH.p_obracun_plata",
     parameters = {
         @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_godina", type = Integer.class),
         @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mjesec", type = Integer.class),
         @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_tip_isplate", type = String.class),
         @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_broj_racuna", type = String.class),
         @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_korisnik", type = String.class),
         @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_out", type = String.class)
     }
 )
})*/
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PrPayrollSettings extends AbstractAuditingEntity implements Serializable {

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

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "calculation_date")
    private LocalDate calculationDate;

    @Column(name = "number_of_working_days")
    private Integer numberOfWorkingDays;

    @Column(name = "number_of_working_hours")
    private Integer numberOfWorkingHours;

    @Column(name = "calculated")
    private String calculated;

    @Column(name = "paid")
    private String paid;

    @Column(name = "company_id")
    private Integer companyId;

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

    public PrPayrollSettings year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public PrPayrollSettings month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getCalculationNumber() {
        return calculationNumber;
    }

    public PrPayrollSettings calculationNumber(String calculationNumber) {
        this.calculationNumber = calculationNumber;
        return this;
    }

    public void setCalculationNumber(String calculationNumber) {
        this.calculationNumber = calculationNumber;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public PrPayrollSettings paymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getCalculationDate() {
        return calculationDate;
    }

    public PrPayrollSettings calculationDate(LocalDate calculationDate) {
        this.calculationDate = calculationDate;
        return this;
    }

    public void setCalculationDate(LocalDate calculationDate) {
        this.calculationDate = calculationDate;
    }

    public Integer getNumberOfWorkingDays() {
        return numberOfWorkingDays;
    }

    public PrPayrollSettings numberOfWorkingDays(Integer numberOfWorkingDays) {
        this.numberOfWorkingDays = numberOfWorkingDays;
        return this;
    }

    public void setNumberOfWorkingDays(Integer numberOfWorkingDays) {
        this.numberOfWorkingDays = numberOfWorkingDays;
    }

    public Integer getNumberOfWorkingHours() {
        return numberOfWorkingHours;
    }

    public void setNumberOfWorkingHours(Integer numberOfWorkingHours) {
        this.numberOfWorkingHours = numberOfWorkingHours;
    }

    public String getCalculated() {
        return calculated;
    }

    public PrPayrollSettings calculated(String calculated) {
        this.calculated = calculated;
        return this;
    }

    public void setCalculated(String calculated) {
        this.calculated = calculated;
    }

    public String getPaid() {
        return paid;
    }

    public PrPayrollSettings paid(String paid) {
        this.paid = paid;
        return this;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public PrPayrollSettings companyId(Integer companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public PrSalaryTypes getSalaryType() {
        return salaryType;
    }

    public PrPayrollSettings salaryType(PrSalaryTypes prSalaryTypes) {
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
        PrPayrollSettings prPayrollSettings = (PrPayrollSettings) o;
        if (prPayrollSettings.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prPayrollSettings.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrPayrollSettings{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", calculationNumber=" + getCalculationNumber() +
            ", paymentDate='" + getPaymentDate() + "'" +
            ", calculationDate='" + getCalculationDate() + "'" +
            ", numberOfWorkingDays=" + getNumberOfWorkingDays() +
            ", numberOfWorkingHours=" + getNumberOfWorkingHours() +
            ", calculated='" + getCalculated() + "'" +
            ", paid='" + getPaid() + "'" +
            ", companyId=" + getCompanyId() +
            "}";
    }
}
