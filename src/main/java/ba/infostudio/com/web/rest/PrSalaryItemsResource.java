package ba.infostudio.com.web.rest;

import ba.infostudio.com.domain.Action;
import ba.infostudio.com.web.rest.util.AuditUtil;
import org.apache.commons.lang.RandomStringUtils;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrSalaryItems;

import ba.infostudio.com.repository.PrSalaryItemsRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrSalaryItemsDTO;
import ba.infostudio.com.service.mapper.PrSalaryItemsMapper;
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
 * REST controller for managing PrSalaryItems.
 */
@RestController
@RequestMapping("/api")
public class PrSalaryItemsResource {

    private final Logger log = LoggerFactory.getLogger(PrSalaryItemsResource.class);

    private static final String ENTITY_NAME = "prSalaryItems";

    private final PrSalaryItemsRepository prSalaryItemsRepository;

    private final PrSalaryItemsMapper prSalaryItemsMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    public PrSalaryItemsResource(PrSalaryItemsRepository prSalaryItemsRepository,
                                 PrSalaryItemsMapper prSalaryItemsMapper,
                                 ApplicationEventPublisher applicationEventPublisher) {
        this.prSalaryItemsRepository = prSalaryItemsRepository;
        this.prSalaryItemsMapper = prSalaryItemsMapper;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * POST  /pr-salary-items : Create a new prSalaryItems.
     *
     * @param prSalaryItemsDTO the prSalaryItemsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prSalaryItemsDTO, or with status 400 (Bad Request) if the prSalaryItems has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-salary-items")
    @Timed
    public ResponseEntity<PrSalaryItemsDTO> createPrSalaryItems(@Valid @RequestBody PrSalaryItemsDTO prSalaryItemsDTO) throws URISyntaxException {
        log.debug("REST request to save PrSalaryItems : {}", prSalaryItemsDTO);
        if (prSalaryItemsDTO.getId() != null) {
            throw new BadRequestAlertException("A new prSalaryItems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String newCode = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
        while(prSalaryItemsRepository.findByCode(newCode) != null){
            newCode = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
        }
        prSalaryItemsDTO.setCode(newCode);
        PrSalaryItems prSalaryItems = prSalaryItemsMapper.toEntity(prSalaryItemsDTO);
        prSalaryItems = prSalaryItemsRepository.save(prSalaryItems);
        PrSalaryItemsDTO result = prSalaryItemsMapper.toDto(prSalaryItems);
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                result.getCreatedBy(),
                "payroll",
                ENTITY_NAME,
                result.getId().toString(),
                Action.POST
            )
        );

        return ResponseEntity.created(new URI("/api/pr-salary-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-salary-items : Updates an existing prSalaryItems.
     *
     * @param prSalaryItemsDTO the prSalaryItemsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prSalaryItemsDTO,
     * or with status 400 (Bad Request) if the prSalaryItemsDTO is not valid,
     * or with status 500 (Internal Server Error) if the prSalaryItemsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-salary-items")
    @Timed
    public ResponseEntity<PrSalaryItemsDTO> updatePrSalaryItems(@Valid @RequestBody PrSalaryItemsDTO prSalaryItemsDTO) throws URISyntaxException {
        log.debug("REST request to update PrSalaryItems : {}", prSalaryItemsDTO);
        if (prSalaryItemsDTO.getId() == null) {
            return createPrSalaryItems(prSalaryItemsDTO);
        }
        PrSalaryItems prSalaryItems = prSalaryItemsMapper.toEntity(prSalaryItemsDTO);
        prSalaryItems = prSalaryItemsRepository.save(prSalaryItems);
        PrSalaryItemsDTO result = prSalaryItemsMapper.toDto(prSalaryItems);
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
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prSalaryItemsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-salary-items : get all the prSalaryItems.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prSalaryItems in body
     */
    @GetMapping("/pr-salary-items")
    @Timed
    public ResponseEntity<List<PrSalaryItemsDTO>> getAllPrSalaryItems(Pageable pageable) {
        log.debug("REST request to get a page of PrSalaryItems");
        Page<PrSalaryItems> page = prSalaryItemsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-salary-items");
        return new ResponseEntity<>(prSalaryItemsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /pr-salary-items/:id : get the "id" prSalaryItems.
     *
     * @param id the id of the prSalaryItemsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prSalaryItemsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-salary-items/{id}")
    @Timed
    public ResponseEntity<PrSalaryItemsDTO> getPrSalaryItems(@PathVariable Long id) {
        log.debug("REST request to get PrSalaryItems : {}", id);
        PrSalaryItems prSalaryItems = prSalaryItemsRepository.findOne(id);
        PrSalaryItemsDTO prSalaryItemsDTO = prSalaryItemsMapper.toDto(prSalaryItems);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prSalaryItemsDTO));
    }

    /**
     * DELETE  /pr-salary-items/:id : delete the "id" prSalaryItems.
     *
     * @param id the id of the prSalaryItemsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-salary-items/{id}")
    @Timed
    public ResponseEntity<Void> deletePrSalaryItems(@PathVariable Long id) {
        log.debug("REST request to delete PrSalaryItems : {}", id);
        PrSalaryItems salaryItems = prSalaryItemsRepository.findOne(id);
        PrSalaryItemsDTO salaryItemsDTO = prSalaryItemsMapper.toDto(salaryItems);
        prSalaryItemsRepository.delete(id);
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                salaryItemsDTO.getUpdatedBy(),
                "payroll",
                ENTITY_NAME,
                id.toString(),
                Action.DELETE
            )
        );
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
