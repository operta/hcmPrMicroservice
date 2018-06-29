package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrEmpSalaries;

import ba.infostudio.com.repository.PrEmpSalariesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrEmpSalariesDTO;
import ba.infostudio.com.service.mapper.PrEmpSalariesMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PrEmpSalaries.
 */
@RestController
@RequestMapping("/api")
public class PrEmpSalariesResource {

    private final Logger log = LoggerFactory.getLogger(PrEmpSalariesResource.class);

    private static final String ENTITY_NAME = "prEmpSalaries";

    private final PrEmpSalariesRepository prEmpSalariesRepository;

    private final PrEmpSalariesMapper prEmpSalariesMapper;

    public PrEmpSalariesResource(PrEmpSalariesRepository prEmpSalariesRepository, PrEmpSalariesMapper prEmpSalariesMapper) {
        this.prEmpSalariesRepository = prEmpSalariesRepository;
        this.prEmpSalariesMapper = prEmpSalariesMapper;
    }

    /**
     * POST  /pr-emp-salaries : Create a new prEmpSalaries.
     *
     * @param prEmpSalariesDTO the prEmpSalariesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prEmpSalariesDTO, or with status 400 (Bad Request) if the prEmpSalaries has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-emp-salaries")
    @Timed
    public ResponseEntity<PrEmpSalariesDTO> createPrEmpSalaries(@Valid @RequestBody PrEmpSalariesDTO prEmpSalariesDTO) throws URISyntaxException {
        log.debug("REST request to save PrEmpSalaries : {}", prEmpSalariesDTO);
        if (prEmpSalariesDTO.getId() != null) {
            throw new BadRequestAlertException("A new prEmpSalaries cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrEmpSalaries prEmpSalaries = prEmpSalariesMapper.toEntity(prEmpSalariesDTO);
        prEmpSalaries = prEmpSalariesRepository.save(prEmpSalaries);
        PrEmpSalariesDTO result = prEmpSalariesMapper.toDto(prEmpSalaries);
        return ResponseEntity.created(new URI("/api/pr-emp-salaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-emp-salaries : Updates an existing prEmpSalaries.
     *
     * @param prEmpSalariesDTO the prEmpSalariesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prEmpSalariesDTO,
     * or with status 400 (Bad Request) if the prEmpSalariesDTO is not valid,
     * or with status 500 (Internal Server Error) if the prEmpSalariesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-emp-salaries")
    @Timed
    public ResponseEntity<PrEmpSalariesDTO> updatePrEmpSalaries(@Valid @RequestBody PrEmpSalariesDTO prEmpSalariesDTO) throws URISyntaxException {
        log.debug("REST request to update PrEmpSalaries : {}", prEmpSalariesDTO);
        if (prEmpSalariesDTO.getId() == null) {
            return createPrEmpSalaries(prEmpSalariesDTO);
        }
        PrEmpSalaries prEmpSalaries = prEmpSalariesMapper.toEntity(prEmpSalariesDTO);
        prEmpSalaries = prEmpSalariesRepository.save(prEmpSalaries);
        PrEmpSalariesDTO result = prEmpSalariesMapper.toDto(prEmpSalaries);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prEmpSalariesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-emp-salaries : get all the prEmpSalaries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prEmpSalaries in body
     */
    @GetMapping("/pr-emp-salaries")
    @Timed
    public ResponseEntity<List<PrEmpSalariesDTO>> getAllPrEmpSalaries(Pageable pageable) {
        log.debug("REST request to get a page of PrEmpSalaries");
        Page<PrEmpSalaries> page = prEmpSalariesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-emp-salaries");
        return new ResponseEntity<>(prEmpSalariesMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /pr-emp-salaries/:id : get the "id" prEmpSalaries.
     *
     * @param id the id of the prEmpSalariesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prEmpSalariesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-emp-salaries/{id}")
    @Timed
    public ResponseEntity<PrEmpSalariesDTO> getPrEmpSalaries(@PathVariable Long id) {
        log.debug("REST request to get PrEmpSalaries : {}", id);
        PrEmpSalaries prEmpSalaries = prEmpSalariesRepository.findOne(id);
        PrEmpSalariesDTO prEmpSalariesDTO = prEmpSalariesMapper.toDto(prEmpSalaries);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prEmpSalariesDTO));
    }

    /**
     * GET  /pr-emp-salaries/employee/:employeeId/payroll/:payrollId : get emp salary by emp and payroll
     *
     * @param employeeId the id of employee
     * @param payrollId the id of payroll
     * @return the ResponseEntity with status 200 (OK) and with body the prEmpSalariesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-emp-salaries/employee/{employeeId}/payroll/{payrollId}")
    @Timed
    public ResponseEntity<PrEmpSalariesDTO> getEmpSalariesByEmployeeAndPayroll(
        @PathVariable Integer employeeId,
        @PathVariable Integer payrollId
    ) {
        log.debug("REST request to get PrEmpSalaries by empId and payrollId : {}", employeeId);
        PrEmpSalaries prEmpSalaries = prEmpSalariesRepository.findByEmployeeIdAndPayrollSettingsId(employeeId, payrollId.longValue());
        PrEmpSalariesDTO prEmpSalariesDTO = prEmpSalariesMapper.toDto(prEmpSalaries);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prEmpSalariesDTO));
    }

        /**
     * GET  /pr-emp-salaries/employee/:employeeId : get emp salary by emp
     *
     * @param employeeId the id of employee
     * @return the ResponseEntity with status 200 (OK) and with body the prEmpSalariesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-emp-salaries/employee/{employeeId}")
    @Timed
    public ResponseEntity<List<PrEmpSalariesDTO>> getEmpSalariesByEmployee(
        @PathVariable Integer employeeId
    ) {
        log.debug("REST request to get PrEmpSalaries by empId : {}", employeeId);
        List<PrEmpSalaries> prEmpSalaries = prEmpSalariesRepository.findByEmployeeId(employeeId);
        List<PrEmpSalariesDTO> dtoitems = new ArrayList<PrEmpSalariesDTO>();
        for (PrEmpSalaries item : prEmpSalaries) {
            dtoitems.add(prEmpSalariesMapper.toDto(item));
        }
        return new ResponseEntity<>(dtoitems, null, HttpStatus.OK);
    }

        /**
     * GET  /pr-emp-salaries/check/employee/:employeeId/payroll/:payrollId : get emp salary by emp and payroll
     *
     * @param employeeId the id of employee
     * @param payrollId the id of payroll
     * @return the ResponseEntity with status 200 (OK) and with body the prEmpSalariesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-emp-salaries/check/employee/{employeeId}/payroll/{payrollId}")
    @Timed
    public ResponseEntity<PrEmpSalariesDTO> checckEmpSalariesByEmployeeAndPayroll(
        @PathVariable Integer employeeId,
        @PathVariable Integer payrollId
    ) {
        log.debug("REST request to get PrEmpSalaries by empId and payrollId : {}", employeeId);
        PrEmpSalaries prEmpSalaries = prEmpSalariesRepository.findByEmployeeIdAndPayrollSettingsId(employeeId, payrollId.longValue());
        PrEmpSalariesDTO prEmpSalariesDTO = prEmpSalariesMapper.toDto(prEmpSalaries);
        return new ResponseEntity<PrEmpSalariesDTO>(prEmpSalariesDTO, null, HttpStatus.OK);
    }


    /**
     * DELETE  /pr-emp-salaries/:id : delete the "id" prEmpSalaries.
     *
     * @param id the id of the prEmpSalariesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-emp-salaries/{id}")
    @Timed
    public ResponseEntity<Void> deletePrEmpSalaries(@PathVariable Long id) {
        log.debug("REST request to delete PrEmpSalaries : {}", id);
        prEmpSalariesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
