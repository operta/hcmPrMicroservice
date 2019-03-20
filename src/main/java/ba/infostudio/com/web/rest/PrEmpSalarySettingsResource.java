package ba.infostudio.com.web.rest;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.security.SecurityUtils;
import ba.infostudio.com.web.rest.util.AuditUtil;
import com.codahale.metrics.annotation.Timed;

import ba.infostudio.com.repository.EmEmpSalariesRepository;
import ba.infostudio.com.repository.PrEmpSalarySettingsRepository;
import ba.infostudio.com.repository.PrPayrollSettingsRepository;
import ba.infostudio.com.repository.PrSalaryItemsRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrEmpSalarySettingsDTO;
import ba.infostudio.com.service.mapper.PrEmpSalarySettingsMapper;
import io.github.jhipster.web.util.ResponseUtil;

import org.dom4j.dom.DOMAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PrEmpSalarySettings.
 */
@RestController
@RequestMapping("/api")
public class PrEmpSalarySettingsResource {

    private final Logger log = LoggerFactory.getLogger(PrEmpSalarySettingsResource.class);

    private static final String ENTITY_NAME = "prEmpSalarySettings";

    private final PrEmpSalarySettingsRepository prEmpSalarySettingsRepository;

    private final EmEmpSalariesRepository salariesRepository;

    private final PrSalaryItemsRepository salaryItemsRepository;

    private final PrPayrollSettingsRepository payrollSettingsRepository;

    private final PrEmpSalarySettingsMapper prEmpSalarySettingsMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    public PrEmpSalarySettingsResource(
        PrEmpSalarySettingsRepository prEmpSalarySettingsRepository,
        PrEmpSalarySettingsMapper prEmpSalarySettingsMapper,
        EmEmpSalariesRepository salariesRepository,
        PrSalaryItemsRepository salaryItemsRepository,
        PrPayrollSettingsRepository payrollSettingsRepository,
        ApplicationEventPublisher applicationEventPublisher
    ) {
        this.prEmpSalarySettingsRepository = prEmpSalarySettingsRepository;
        this.prEmpSalarySettingsMapper = prEmpSalarySettingsMapper;
        this.salariesRepository = salariesRepository;
        this.salaryItemsRepository = salaryItemsRepository;
        this.payrollSettingsRepository = payrollSettingsRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * POST  /pr-emp-salary-settings : Create a new prEmpSalarySettings.
     *
     * @param prEmpSalarySettingsDTO the prEmpSalarySettingsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prEmpSalarySettingsDTO, or with status 400 (Bad Request) if the prEmpSalarySettings has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-emp-salary-settings")
    @Timed
    public ResponseEntity<PrEmpSalarySettingsDTO> createPrEmpSalarySettings(@Valid @RequestBody PrEmpSalarySettingsDTO prEmpSalarySettingsDTO) throws URISyntaxException {
        log.debug("REST request to save PrEmpSalarySettings : {}", prEmpSalarySettingsDTO);
        if (prEmpSalarySettingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new prEmpSalarySettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (prEmpSalarySettingsDTO.getSalaryItemId() == null) {
            throw new BadRequestAlertException("No Salary item for prEmpSalarySettings", ENTITY_NAME,
                "nosalaryitem");
        }
        if (prEmpSalarySettingsDTO.getPayrollSettingsId() == null) {
            throw new BadRequestAlertException("No Payroll settings for PrEmpSalarySettings", ENTITY_NAME,
                "nopayrollsettings");
        }
        PrEmpSalarySettings prEmpSalarySettings = prEmpSalarySettingsMapper.toEntity(prEmpSalarySettingsDTO);

        PrSalaryItems salaryItem = salaryItemsRepository.findOne(prEmpSalarySettings.getSalaryItem().getId());
        PrPayrollSettings payrollSettings = payrollSettingsRepository.findOne(prEmpSalarySettings.getPayrollSettings().getId());

        checkIfExistsSalaryItemAndPayrollSettings(salaryItem, payrollSettings);

        if (salaryItem.getPercentagePayment().equals("Y")) {
            prEmpSalarySettings.setAmount(this.generateSalary(prEmpSalarySettings, salaryItem, payrollSettings));
        }

        prEmpSalarySettings = prEmpSalarySettingsRepository.save(prEmpSalarySettings);
        PrEmpSalarySettingsDTO result = prEmpSalarySettingsMapper.toDto(prEmpSalarySettings);

        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                "payroll",
                ENTITY_NAME,
                result.getId().toString(),
                Action.POST
            )
        );
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                prEmpSalarySettings.getEmployeeId().toString(),
                "employee",
                ENTITY_NAME,
                result.getId().toString(),
                Action.POST
            )
        );
        return ResponseEntity.created(new URI("/api/pr-emp-salary-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    private void checkIfExistsSalaryItemAndPayrollSettings(PrSalaryItems salaryItem, PrPayrollSettings payrollSettings) {
        if (salaryItem == null) {
            throw new BadRequestAlertException("Salary item does not exist", ENTITY_NAME, "nosalaryitem");
        }
        if (payrollSettings == null) {
            throw new BadRequestAlertException("Payroll settings does not exist", ENTITY_NAME, "nopayrollsettings");
        }
    }

    private Double generateSalary(PrEmpSalarySettings salarySettings, PrSalaryItems salaryItem, PrPayrollSettings payrollSettings) {
        List<EmEmpSalaries> employeeSalaries = salariesRepository.findByIdEmployeeId(salarySettings.getEmployeeId().longValue());

        EmEmpSalaries lastSalary = employeeSalaries.stream()
            .max(Comparator.comparing(EmEmpSalaries::getDateFrom))
            .orElse(null);

        double result = 100 * (salarySettings.getNumberOfHours().doubleValue() / payrollSettings.getNumberOfWorkingHours().doubleValue()) * (salaryItem.getBase() / 100);

        if (lastSalary != null && lastSalary.getSalaryAmount() != null) {
            result = lastSalary.getSalaryAmount() * (salarySettings.getNumberOfHours().doubleValue() / payrollSettings.getNumberOfWorkingHours().doubleValue()) * (salaryItem.getBase() / 100);
        }

        return result;
    }


    /**
     * PUT  /pr-emp-salary-settings : Updates an existing prEmpSalarySettings.
     *
     * @param prEmpSalarySettingsDTO the prEmpSalarySettingsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prEmpSalarySettingsDTO,
     * or with status 400 (Bad Request) if the prEmpSalarySettingsDTO is not valid,
     * or with status 500 (Internal Server Error) if the prEmpSalarySettingsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-emp-salary-settings")
    @Timed
    public ResponseEntity<PrEmpSalarySettingsDTO> updatePrEmpSalarySettings(@Valid @RequestBody PrEmpSalarySettingsDTO prEmpSalarySettingsDTO) throws URISyntaxException {
        log.debug("REST request to update PrEmpSalarySettings : {}", prEmpSalarySettingsDTO);
        if (prEmpSalarySettingsDTO.getId() == null) {
            return createPrEmpSalarySettings(prEmpSalarySettingsDTO);
        }
        PrEmpSalarySettings prEmpSalarySettings = prEmpSalarySettingsMapper.toEntity(prEmpSalarySettingsDTO);
        PrSalaryItems salaryItem = salaryItemsRepository.findOne(prEmpSalarySettings.getSalaryItem().getId());
        PrPayrollSettings settings = payrollSettingsRepository.findOne(prEmpSalarySettings.getPayrollSettings().getId());
        if (salaryItem.getPercentagePayment().equals("Y")) {
            prEmpSalarySettings.setAmount(this.generateSalary(prEmpSalarySettings, salaryItem, settings));
        }
        prEmpSalarySettings = prEmpSalarySettingsRepository.save(prEmpSalarySettings);
        PrEmpSalarySettingsDTO result = prEmpSalarySettingsMapper.toDto(prEmpSalarySettings);

        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                "payroll",
                ENTITY_NAME,
                result.getId().toString(),
                Action.PUT
            )
        );
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                prEmpSalarySettings.getEmployeeId().toString(),
                "employee",
                ENTITY_NAME,
                result.getId().toString(),
                Action.PUT
            )
        );
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prEmpSalarySettingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-emp-salary-settings : get all the prEmpSalarySettings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prEmpSalarySettings in body
     */
    @GetMapping("/pr-emp-salary-settings")
    @Timed
    public ResponseEntity<List<PrEmpSalarySettingsDTO>> getAllPrEmpSalarySettings(Pageable pageable) {
        log.debug("REST request to get a page of PrEmpSalarySettings");
        Page<PrEmpSalarySettings> page = prEmpSalarySettingsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-emp-salary-settings");
        return new ResponseEntity<>(prEmpSalarySettingsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /pr-emp-salary-settings/employee/:employeeId/payroll/:payrollId : get emp salary settings by emp and payroll
     *
     * @param employeeId the id of employee
     * @param payrollId  the id of payroll
     * @return the ResponseEntity with status 200 (OK) and with body the prEmpSalarySettingsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-emp-salary-settings/employee/{employeeId}/payroll/{payrollId}")
    @Timed
    public ResponseEntity<List<PrEmpSalarySettingsDTO>> getEmpSalariesByEmployeeAndPayroll(
        @PathVariable Integer employeeId,
        @PathVariable Integer payrollId
    ) {
        log.debug("REST request to get PrEmpSalarySettings by empId and payrollId : {}", employeeId);
        List<PrEmpSalarySettings> prEmpSalarySettings = prEmpSalarySettingsRepository.findByEmployeeIdAndPayrollSettingsId(employeeId, payrollId.longValue());
        List<PrEmpSalarySettingsDTO> prEmpSalarySettingsDTO = new ArrayList<PrEmpSalarySettingsDTO>();
        for (PrEmpSalarySettings settings : prEmpSalarySettings) {
            prEmpSalarySettingsDTO.add(prEmpSalarySettingsMapper.toDto(settings));
        }
        return new ResponseEntity<List<PrEmpSalarySettingsDTO>>(prEmpSalarySettingsDTO, HttpStatus.OK);
    }

    /**
     * GET  /pr-emp-salary-settings/payroll/:payrollId : get emp salary settings by payroll
     *
     * @param payrollId the id of payroll
     * @return the ResponseEntity with status 200 (OK) and with body the prEmpSalarySettingsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-emp-salary-settings/payroll/{payrollId}")
    @Timed
    public ResponseEntity<List<PrEmpSalarySettingsDTO>> getEmpSalariesByPayroll(
        @PathVariable Integer payrollId
    ) {
        log.debug("REST request to get PrEmpSalarySettings by payrollId : {}", payrollId);
        List<PrEmpSalarySettings> prEmpSalarySettings = prEmpSalarySettingsRepository.findByPayrollSettingsId(payrollId.longValue());
        List<PrEmpSalarySettingsDTO> prEmpSalarySettingsDTO = new ArrayList<PrEmpSalarySettingsDTO>();
        for (PrEmpSalarySettings settings : prEmpSalarySettings) {
            prEmpSalarySettingsDTO.add(prEmpSalarySettingsMapper.toDto(settings));
        }
        return new ResponseEntity<List<PrEmpSalarySettingsDTO>>(prEmpSalarySettingsDTO, HttpStatus.OK);
    }

    /**
     * GET  /pr-emp-salary-settings/:id : get the "id" prEmpSalarySettings.
     *
     * @param id the id of the prEmpSalarySettingsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prEmpSalarySettingsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-emp-salary-settings/{id}")
    @Timed
    public ResponseEntity<PrEmpSalarySettingsDTO> getPrEmpSalarySettings(@PathVariable Long id) {
        log.debug("REST request to get PrEmpSalarySettings : {}", id);
        PrEmpSalarySettings prEmpSalarySettings = prEmpSalarySettingsRepository.findOne(id);
        PrEmpSalarySettingsDTO prEmpSalarySettingsDTO = prEmpSalarySettingsMapper.toDto(prEmpSalarySettings);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prEmpSalarySettingsDTO));
    }

    /**
     * GET  /pr-emp-salary-settings/employee/:id : get true or false depending on if there are employees.
     *
     * @param id the id of the prEmpSalarySettingsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prEmpSalarySettingsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-emp-salary-settings/employee/{id}")
    @Timed
    public ResponseEntity<Boolean> getIfExistsEmployeeWithDateToNotExpired(@PathVariable Long id) {
        log.debug("REST request to get if there exists an employee with dateto not expired!");
        Integer numOfEmpsWithDateToNotExpired =
            prEmpSalarySettingsRepository.findNumOfEmpsWithDateToNotExpired(id, LocalDate.now());
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(numOfEmpsWithDateToNotExpired > 0));
    }

    /**
     * DELETE  /pr-emp-salary-settings/:id : delete the "id" prEmpSalarySettings.
     *
     * @param id the id of the prEmpSalarySettingsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-emp-salary-settings/{id}")
    @Timed
    public ResponseEntity<Void> deletePrEmpSalarySettings(@PathVariable Long id) {
        log.debug("REST request to delete PrEmpSalarySettings : {}", id);
        PrEmpSalarySettings empSalarySettings = prEmpSalarySettingsRepository.findOne(id);
        prEmpSalarySettingsRepository.delete(id);
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                "payroll",
                ENTITY_NAME,
                id.toString(),
                Action.DELETE
            )
        );
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                empSalarySettings.getEmployeeId().toString(),
                "employee",
                ENTITY_NAME,
                id.toString(),
                Action.DELETE
            )
        );
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
