package ba.infostudio.com.web.rest;

import ba.infostudio.com.domain.Action;
import ba.infostudio.com.domain.EmEmployees;
import ba.infostudio.com.security.SecurityUtils;
import ba.infostudio.com.service.dto.EmEmployeesDTO;
import ba.infostudio.com.service.mapper.EmEmployeesMapper;
import ba.infostudio.com.web.rest.util.AuditUtil;
import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrEmployeeSuspensions;

import ba.infostudio.com.repository.PrEmployeeSuspensionsRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrEmployeeSuspensionsDTO;
import ba.infostudio.com.service.mapper.PrEmployeeSuspensionsMapper;
import io.github.jhipster.web.util.ResponseUtil;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PrEmployeeSuspensions.
 */
@RestController
@RequestMapping("/api")
public class PrEmployeeSuspensionsResource {

    private final Logger log = LoggerFactory.getLogger(PrEmployeeSuspensionsResource.class);

    private static final String ENTITY_NAME = "prEmployeeSuspensions";

    private final PrEmployeeSuspensionsRepository prEmployeeSuspensionsRepository;

    private final PrEmployeeSuspensionsMapper prEmployeeSuspensionsMapper;

    private final ApplicationEventPublisher applicationEventPublisher;


    public PrEmployeeSuspensionsResource(PrEmployeeSuspensionsRepository prEmployeeSuspensionsRepository, PrEmployeeSuspensionsMapper prEmployeeSuspensionsMapper,
                                         ApplicationEventPublisher applicationEventPublisher) {
        this.prEmployeeSuspensionsRepository = prEmployeeSuspensionsRepository;
        this.prEmployeeSuspensionsMapper = prEmployeeSuspensionsMapper;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * POST  /pr-employee-suspensions : Create a new prEmployeeSuspensions.
     *
     * @param prEmployeeSuspensionsDTO the prEmployeeSuspensionsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prEmployeeSuspensionsDTO, or with status 400 (Bad Request) if the prEmployeeSuspensions has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-employee-suspensions")
    @Timed
    public ResponseEntity<PrEmployeeSuspensionsDTO> createPrEmployeeSuspensions(@Valid @RequestBody PrEmployeeSuspensionsDTO prEmployeeSuspensionsDTO) throws URISyntaxException {
        log.debug("REST request to save PrEmployeeSuspensions : {}", prEmployeeSuspensionsDTO);
        if (prEmployeeSuspensionsDTO.getId() != null) {
            throw new BadRequestAlertException("A new prEmployeeSuspensions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrEmployeeSuspensions prEmployeeSuspensions = prEmployeeSuspensionsMapper.toEntity(prEmployeeSuspensionsDTO);
        prEmployeeSuspensions = prEmployeeSuspensionsRepository.save(prEmployeeSuspensions);
        PrEmployeeSuspensionsDTO result = prEmployeeSuspensionsMapper.toDto(prEmployeeSuspensions);

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
                prEmployeeSuspensions.getEmployeeId().toString(),
                "employee",
                ENTITY_NAME,
                result.getId().toString(),
                Action.POST
            )
        );
        return ResponseEntity.created(new URI("/api/pr-employee-suspensions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-employee-suspensions : Updates an existing prEmployeeSuspensions.
     *
     * @param prEmployeeSuspensionsDTO the prEmployeeSuspensionsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prEmployeeSuspensionsDTO,
     * or with status 400 (Bad Request) if the prEmployeeSuspensionsDTO is not valid,
     * or with status 500 (Internal Server Error) if the prEmployeeSuspensionsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-employee-suspensions")
    @Timed
    public ResponseEntity<PrEmployeeSuspensionsDTO> updatePrEmployeeSuspensions(@Valid @RequestBody PrEmployeeSuspensionsDTO prEmployeeSuspensionsDTO) throws URISyntaxException {
        log.debug("REST request to update PrEmployeeSuspensions : {}", prEmployeeSuspensionsDTO);
        if (prEmployeeSuspensionsDTO.getId() == null) {
            return createPrEmployeeSuspensions(prEmployeeSuspensionsDTO);
        }
        PrEmployeeSuspensions prEmployeeSuspensions = prEmployeeSuspensionsMapper.toEntity(prEmployeeSuspensionsDTO);
        prEmployeeSuspensions = prEmployeeSuspensionsRepository.save(prEmployeeSuspensions);
        PrEmployeeSuspensionsDTO result = prEmployeeSuspensionsMapper.toDto(prEmployeeSuspensions);
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
                prEmployeeSuspensions.getEmployeeId().toString(),
                "employee",
                ENTITY_NAME,
                result.getId().toString(),
                Action.PUT
            )
        );
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prEmployeeSuspensionsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-employee-suspensions : get all the prEmployeeSuspensions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prEmployeeSuspensions in body
     */
    @GetMapping("/pr-employee-suspensions")
    @Timed
    public ResponseEntity<List<PrEmployeeSuspensionsDTO>> getAllPrEmployeeSuspensions(Pageable pageable) {
        log.debug("REST request to get a page of PrEmployeeSuspensions");
        Page<PrEmployeeSuspensions> page = prEmployeeSuspensionsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-employee-suspensions");
        return new ResponseEntity<>(prEmployeeSuspensionsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /pr-employee-suspensions/:id : get the "id" prEmployeeSuspensions.
     *
     * @param id the id of the prEmployeeSuspensionsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prEmployeeSuspensionsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-employee-suspensions/{id}")
    @Timed
    public ResponseEntity<PrEmployeeSuspensionsDTO> getPrEmployeeSuspensions(@PathVariable Long id) {
        log.debug("REST request to get PrEmployeeSuspensions : {}", id);
        PrEmployeeSuspensions prEmployeeSuspensions = prEmployeeSuspensionsRepository.findOne(id);
        PrEmployeeSuspensionsDTO prEmployeeSuspensionsDTO = prEmployeeSuspensionsMapper.toDto(prEmployeeSuspensions);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prEmployeeSuspensionsDTO));
    }

    /**
     * GET  /pr-employee-suspensions/employee/:id : get the "id" prEmployeeSuspensions.
     *
     * @param id the id of the EmployeeId to retrieve List<PrEmplyeeSuspensionsDTO>
     * @return the ResponseEntity with status 200 (OK) and with body the List<prEmployeeSuspensionsDTO>, or with status 404 (Not Found)
     */
    @GetMapping("/pr-employee-suspensions/employee/{id}")
    @Timed
    public ResponseEntity<List<PrEmployeeSuspensionsDTO>> getListOfSuspensionsByEmployeeId(@PathVariable Long id) {
        log.debug("REST request to get List of Employees : {}", id);
        List<PrEmployeeSuspensions> prEmployeeSuspensions =
            prEmployeeSuspensionsRepository.findByEmployeeId(id.intValue());
        List<PrEmployeeSuspensionsDTO> prEmployeeSuspensionsDTO = prEmployeeSuspensionsMapper.toDto(prEmployeeSuspensions);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prEmployeeSuspensionsDTO));
    }

    /**
     * DELETE  /pr-employee-suspensions/:id : delete the "id" prEmployeeSuspensions.
     *
     * @param id the id of the prEmployeeSuspensionsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-employee-suspensions/{id}")
    @Timed
    public ResponseEntity<Void> deletePrEmployeeSuspensions(@PathVariable Long id) {
        log.debug("REST request to delete PrEmployeeSuspensions : {}", id);
        PrEmployeeSuspensions suspensions = prEmployeeSuspensionsRepository.findOne(id);
        prEmployeeSuspensionsRepository.delete(id);
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
                suspensions.getEmployeeId().toString(),
                "employee",
                ENTITY_NAME,
                id.toString(),
                Action.DELETE
            )
        );
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
