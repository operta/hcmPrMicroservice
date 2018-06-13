package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A PrEmployeeSuspensions.
 */
@Entity
@Table(name = "pr_employee_suspensions")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PrEmployeeSuspensions extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "creditor_id")
    private Integer creditorId;

    @NotNull
    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "total_debt")
    private Double totalDebt;

    @Column(name = "rest_debt")
    private Double restDebt;

    @Column(name = "amount_part_debt")
    private Double amountPartDebt;

    @Column(name = "total_number_of_parts")
    private Integer totalNumberOfParts;

    @Column(name = "rest_number_of_parts")
    private Integer restNumberOfParts;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @OneToOne
    @JoinColumn(name = "suspension_id")
    private PrSuspensions suspension;

    @OneToOne
    @JoinColumn(name = "suspension_type_id")
    private PrSuspensionTypes suspensionType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCreditorId() {
        return creditorId;
    }

    public PrEmployeeSuspensions creditorId(Integer creditorId) {
        this.creditorId = creditorId;
        return this;
    }

    public void setCreditorId(Integer creditorId) {
        this.creditorId = creditorId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public PrEmployeeSuspensions employeeId(Integer employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public PrEmployeeSuspensions accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getTotalDebt() {
        return totalDebt;
    }

    public PrEmployeeSuspensions totalDebt(Double totalDebt) {
        this.totalDebt = totalDebt;
        return this;
    }

    public void setTotalDebt(Double totalDebt) {
        this.totalDebt = totalDebt;
    }

    public Double getRestDebt() {
        return restDebt;
    }

    public PrEmployeeSuspensions restDebt(Double restDebt) {
        this.restDebt = restDebt;
        return this;
    }

    public void setRestDebt(Double restDebt) {
        this.restDebt = restDebt;
    }

    public Double getAmountPartDebt() {
        return amountPartDebt;
    }

    public PrEmployeeSuspensions amountPartDebt(Double amountPartDebt) {
        this.amountPartDebt = amountPartDebt;
        return this;
    }

    public void setAmountPartDebt(Double amountPartDebt) {
        this.amountPartDebt = amountPartDebt;
    }

    public Integer getTotalNumberOfParts() {
        return totalNumberOfParts;
    }

    public PrEmployeeSuspensions totalNumberOfParts(Integer totalNumberOfParts) {
        this.totalNumberOfParts = totalNumberOfParts;
        return this;
    }

    public void setTotalNumberOfParts(Integer totalNumberOfParts) {
        this.totalNumberOfParts = totalNumberOfParts;
    }

    public Integer getRestNumberOfParts() {
        return restNumberOfParts;
    }

    public PrEmployeeSuspensions restNumberOfParts(Integer restNumberOfParts) {
        this.restNumberOfParts = restNumberOfParts;
        return this;
    }

    public void setRestNumberOfParts(Integer restNumberOfParts) {
        this.restNumberOfParts = restNumberOfParts;
    }

    public String getStatus() {
        return status;
    }

    public PrEmployeeSuspensions status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public PrEmployeeSuspensions description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public PrEmployeeSuspensions dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public PrEmployeeSuspensions dateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public PrSuspensions getSuspension() {
        return suspension;
    }

    public PrEmployeeSuspensions suspension(PrSuspensions prSuspensions) {
        this.suspension = prSuspensions;
        return this;
    }

    public void setSuspension(PrSuspensions prSuspensions) {
        this.suspension = prSuspensions;
    }

    public PrSuspensionTypes getSuspensionType() {
        return suspensionType;
    }

    public PrEmployeeSuspensions suspensionType(PrSuspensionTypes prSuspensionTypes) {
        this.suspensionType = prSuspensionTypes;
        return this;
    }

    public void setSuspensionType(PrSuspensionTypes prSuspensionTypes) {
        this.suspensionType = prSuspensionTypes;
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
        PrEmployeeSuspensions prEmployeeSuspensions = (PrEmployeeSuspensions) o;
        if (prEmployeeSuspensions.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prEmployeeSuspensions.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrEmployeeSuspensions{" +
            "id=" + getId() +
            ", creditorId=" + getCreditorId() +
            ", employeeId=" + getEmployeeId() +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", totalDebt=" + getTotalDebt() +
            ", restDebt=" + getRestDebt() +
            ", amountPartDebt=" + getAmountPartDebt() +
            ", totalNumberOfParts=" + getTotalNumberOfParts() +
            ", restNumberOfParts=" + getRestNumberOfParts() +
            ", status='" + getStatus() + "'" +
            ", description='" + getDescription() + "'" +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            "}";
    }
}
