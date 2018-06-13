package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrTaxLevelPayments;

import ba.infostudio.com.repository.PrTaxLevelPaymentsRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrTaxLevelPaymentsDTO;
import ba.infostudio.com.service.mapper.PrTaxLevelPaymentsMapper;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PrTaxLevelPayments.
 */
@RestController
@RequestMapping("/api")
public class PrTaxLevelPaymentsResource {

    private final Logger log = LoggerFactory.getLogger(PrTaxLevelPaymentsResource.class);

    private static final String ENTITY_NAME = "prTaxLevelPayments";

    private final PrTaxLevelPaymentsRepository prTaxLevelPaymentsRepository;

    private final PrTaxLevelPaymentsMapper prTaxLevelPaymentsMapper;

    public PrTaxLevelPaymentsResource(PrTaxLevelPaymentsRepository prTaxLevelPaymentsRepository, PrTaxLevelPaymentsMapper prTaxLevelPaymentsMapper) {
        this.prTaxLevelPaymentsRepository = prTaxLevelPaymentsRepository;
        this.prTaxLevelPaymentsMapper = prTaxLevelPaymentsMapper;
    }

    /**
     * POST  /pr-tax-level-payments : Create a new prTaxLevelPayments.
     *
     * @param prTaxLevelPaymentsDTO the prTaxLevelPaymentsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prTaxLevelPaymentsDTO, or with status 400 (Bad Request) if the prTaxLevelPayments has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-tax-level-payments")
    @Timed
    public ResponseEntity<PrTaxLevelPaymentsDTO> createPrTaxLevelPayments(@Valid @RequestBody PrTaxLevelPaymentsDTO prTaxLevelPaymentsDTO) throws URISyntaxException {
        log.debug("REST request to save PrTaxLevelPayments : {}", prTaxLevelPaymentsDTO);
        if (prTaxLevelPaymentsDTO.getId() != null) {
            throw new BadRequestAlertException("A new prTaxLevelPayments cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrTaxLevelPayments prTaxLevelPayments = prTaxLevelPaymentsMapper.toEntity(prTaxLevelPaymentsDTO);
        prTaxLevelPayments = prTaxLevelPaymentsRepository.save(prTaxLevelPayments);
        PrTaxLevelPaymentsDTO result = prTaxLevelPaymentsMapper.toDto(prTaxLevelPayments);
        return ResponseEntity.created(new URI("/api/pr-tax-level-payments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-tax-level-payments : Updates an existing prTaxLevelPayments.
     *
     * @param prTaxLevelPaymentsDTO the prTaxLevelPaymentsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prTaxLevelPaymentsDTO,
     * or with status 400 (Bad Request) if the prTaxLevelPaymentsDTO is not valid,
     * or with status 500 (Internal Server Error) if the prTaxLevelPaymentsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-tax-level-payments")
    @Timed
    public ResponseEntity<PrTaxLevelPaymentsDTO> updatePrTaxLevelPayments(@Valid @RequestBody PrTaxLevelPaymentsDTO prTaxLevelPaymentsDTO) throws URISyntaxException {
        log.debug("REST request to update PrTaxLevelPayments : {}", prTaxLevelPaymentsDTO);
        if (prTaxLevelPaymentsDTO.getId() == null) {
            return createPrTaxLevelPayments(prTaxLevelPaymentsDTO);
        }
        PrTaxLevelPayments prTaxLevelPayments = prTaxLevelPaymentsMapper.toEntity(prTaxLevelPaymentsDTO);
        prTaxLevelPayments = prTaxLevelPaymentsRepository.save(prTaxLevelPayments);
        PrTaxLevelPaymentsDTO result = prTaxLevelPaymentsMapper.toDto(prTaxLevelPayments);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prTaxLevelPaymentsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-tax-level-payments : get all the prTaxLevelPayments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prTaxLevelPayments in body
     */
    @GetMapping("/pr-tax-level-payments")
    @Timed
    public ResponseEntity<List<PrTaxLevelPaymentsDTO>> getAllPrTaxLevelPayments(Pageable pageable) {
        log.debug("REST request to get a page of PrTaxLevelPayments");
        Page<PrTaxLevelPayments> page = prTaxLevelPaymentsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-tax-level-payments");
        return new ResponseEntity<>(prTaxLevelPaymentsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /pr-tax-level-payments/:id : get the "id" prTaxLevelPayments.
     *
     * @param id the id of the prTaxLevelPaymentsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prTaxLevelPaymentsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-tax-level-payments/{id}")
    @Timed
    public ResponseEntity<PrTaxLevelPaymentsDTO> getPrTaxLevelPayments(@PathVariable Long id) {
        log.debug("REST request to get PrTaxLevelPayments : {}", id);
        PrTaxLevelPayments prTaxLevelPayments = prTaxLevelPaymentsRepository.findOne(id);
        PrTaxLevelPaymentsDTO prTaxLevelPaymentsDTO = prTaxLevelPaymentsMapper.toDto(prTaxLevelPayments);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prTaxLevelPaymentsDTO));
    }

    /**
     * DELETE  /pr-tax-level-payments/:id : delete the "id" prTaxLevelPayments.
     *
     * @param id the id of the prTaxLevelPaymentsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-tax-level-payments/{id}")
    @Timed
    public ResponseEntity<Void> deletePrTaxLevelPayments(@PathVariable Long id) {
        log.debug("REST request to delete PrTaxLevelPayments : {}", id);
        prTaxLevelPaymentsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
