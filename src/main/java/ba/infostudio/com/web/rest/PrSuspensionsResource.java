package ba.infostudio.com.web.rest;

import ba.infostudio.com.domain.Action;
import ba.infostudio.com.web.rest.util.AuditUtil;
import org.apache.commons.lang.RandomStringUtils;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrSuspensions;

import ba.infostudio.com.repository.PrSuspensionsRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrSuspensionsDTO;
import ba.infostudio.com.service.mapper.PrSuspensionsMapper;
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
 * REST controller for managing PrSuspensions.
 */
@RestController
@RequestMapping("/api")
public class PrSuspensionsResource {

    private final Logger log = LoggerFactory.getLogger(PrSuspensionsResource.class);

    private static final String ENTITY_NAME = "prSuspensions";

    private final PrSuspensionsRepository prSuspensionsRepository;

    private final PrSuspensionsMapper prSuspensionsMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    public PrSuspensionsResource(PrSuspensionsRepository prSuspensionsRepository,
                                 PrSuspensionsMapper prSuspensionsMapper,
                                 ApplicationEventPublisher applicationEventPublisher) {
        this.prSuspensionsRepository = prSuspensionsRepository;
        this.prSuspensionsMapper = prSuspensionsMapper;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * POST  /pr-suspensions : Create a new prSuspensions.
     *
     * @param prSuspensionsDTO the prSuspensionsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prSuspensionsDTO, or with status 400 (Bad Request) if the prSuspensions has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-suspensions")
    @Timed
    public ResponseEntity<PrSuspensionsDTO> createPrSuspensions(@Valid @RequestBody PrSuspensionsDTO prSuspensionsDTO) throws URISyntaxException {
        log.debug("REST request to save PrSuspensions : {}", prSuspensionsDTO);
        if (prSuspensionsDTO.getId() != null) {
            throw new BadRequestAlertException("A new prSuspensions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String newCode = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
        while(prSuspensionsRepository.findByCode(newCode) != null){
            newCode = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
        }
        prSuspensionsDTO.setCode(newCode);
        PrSuspensions prSuspensions = prSuspensionsMapper.toEntity(prSuspensionsDTO);
        prSuspensions = prSuspensionsRepository.save(prSuspensions);
        PrSuspensionsDTO result = prSuspensionsMapper.toDto(prSuspensions);
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                result.getCreatedBy(),
                "payroll",
                ENTITY_NAME,
                result.getId().toString(),
                Action.POST
            )
        );
        return ResponseEntity.created(new URI("/api/pr-suspensions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-suspensions : Updates an existing prSuspensions.
     *
     * @param prSuspensionsDTO the prSuspensionsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prSuspensionsDTO,
     * or with status 400 (Bad Request) if the prSuspensionsDTO is not valid,
     * or with status 500 (Internal Server Error) if the prSuspensionsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-suspensions")
    @Timed
    public ResponseEntity<PrSuspensionsDTO> updatePrSuspensions(@Valid @RequestBody PrSuspensionsDTO prSuspensionsDTO) throws URISyntaxException {
        log.debug("REST request to update PrSuspensions : {}", prSuspensionsDTO);
        if (prSuspensionsDTO.getId() == null) {
            return createPrSuspensions(prSuspensionsDTO);
        }
        PrSuspensions prSuspensions = prSuspensionsMapper.toEntity(prSuspensionsDTO);
        prSuspensions = prSuspensionsRepository.save(prSuspensions);
        PrSuspensionsDTO result = prSuspensionsMapper.toDto(prSuspensions);
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                result.getUpdatedBy(),
                "payroll",
                ENTITY_NAME,
                result.getId().toString(),
                Action.PUT
            )
        );
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prSuspensionsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-suspensions : get all the prSuspensions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prSuspensions in body
     */
    @GetMapping("/pr-suspensions")
    @Timed
    public ResponseEntity<List<PrSuspensionsDTO>> getAllPrSuspensions(Pageable pageable) {
        log.debug("REST request to get a page of PrSuspensions");
        Page<PrSuspensions> page = prSuspensionsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-suspensions");
        return new ResponseEntity<>(prSuspensionsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /pr-suspensions/:id : get the "id" prSuspensions.
     *
     * @param id the id of the prSuspensionsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prSuspensionsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-suspensions/{id}")
    @Timed
    public ResponseEntity<PrSuspensionsDTO> getPrSuspensions(@PathVariable Long id) {
        log.debug("REST request to get PrSuspensions : {}", id);
        PrSuspensions prSuspensions = prSuspensionsRepository.findOne(id);
        PrSuspensionsDTO prSuspensionsDTO = prSuspensionsMapper.toDto(prSuspensions);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prSuspensionsDTO));
    }

    /**
     * DELETE  /pr-suspensions/:id : delete the "id" prSuspensions.
     *
     * @param id the id of the prSuspensionsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-suspensions/{id}")
    @Timed
    public ResponseEntity<Void> deletePrSuspensions(@PathVariable Long id) {
        log.debug("REST request to delete PrSuspensions : {}", id);
        PrSuspensions suspensions = prSuspensionsRepository.findOne(id);
        PrSuspensionsDTO suspensionsDTO = prSuspensionsMapper.toDto(suspensions);
        prSuspensionsRepository.delete(id);
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                suspensionsDTO.getUpdatedBy(),
                "payroll",
                ENTITY_NAME,
                id.toString(),
                Action.DELETE
            )
        );
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
