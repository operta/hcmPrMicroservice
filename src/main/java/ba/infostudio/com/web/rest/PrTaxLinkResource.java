package ba.infostudio.com.web.rest;

import org.apache.commons.lang.RandomStringUtils;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrTaxLink;

import ba.infostudio.com.repository.PrTaxLinkRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrTaxLinkDTO;
import ba.infostudio.com.service.mapper.PrTaxLinkMapper;
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
 * REST controller for managing PrTaxLink.
 */
@RestController
@RequestMapping("/api")
public class PrTaxLinkResource {

    private final Logger log = LoggerFactory.getLogger(PrTaxLinkResource.class);

    private static final String ENTITY_NAME = "prTaxLink";

    private final PrTaxLinkRepository prTaxLinkRepository;

    private final PrTaxLinkMapper prTaxLinkMapper;

    public PrTaxLinkResource(PrTaxLinkRepository prTaxLinkRepository, PrTaxLinkMapper prTaxLinkMapper) {
        this.prTaxLinkRepository = prTaxLinkRepository;
        this.prTaxLinkMapper = prTaxLinkMapper;
    }

    /**
     * POST  /pr-tax-links : Create a new prTaxLink.
     *
     * @param prTaxLinkDTO the prTaxLinkDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prTaxLinkDTO, or with status 400 (Bad Request) if the prTaxLink has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-tax-links")
    @Timed
    public ResponseEntity<PrTaxLinkDTO> createPrTaxLink(@Valid @RequestBody PrTaxLinkDTO prTaxLinkDTO) throws URISyntaxException {
        log.debug("REST request to save PrTaxLink : {}", prTaxLinkDTO);
        if (prTaxLinkDTO.getId() != null) {
            throw new BadRequestAlertException("A new prTaxLink cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String newCode = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
        while(prTaxLinkRepository.findByCode(newCode) != null){
            newCode = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
        }
        prTaxLinkDTO.setCode(newCode);
        PrTaxLink prTaxLink = prTaxLinkMapper.toEntity(prTaxLinkDTO);
        prTaxLink = prTaxLinkRepository.save(prTaxLink);
        PrTaxLinkDTO result = prTaxLinkMapper.toDto(prTaxLink);
        return ResponseEntity.created(new URI("/api/pr-tax-links/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-tax-links : Updates an existing prTaxLink.
     *
     * @param prTaxLinkDTO the prTaxLinkDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prTaxLinkDTO,
     * or with status 400 (Bad Request) if the prTaxLinkDTO is not valid,
     * or with status 500 (Internal Server Error) if the prTaxLinkDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-tax-links")
    @Timed
    public ResponseEntity<PrTaxLinkDTO> updatePrTaxLink(@Valid @RequestBody PrTaxLinkDTO prTaxLinkDTO) throws URISyntaxException {
        log.debug("REST request to update PrTaxLink : {}", prTaxLinkDTO);
        if (prTaxLinkDTO.getId() == null) {
            return createPrTaxLink(prTaxLinkDTO);
        }
        PrTaxLink prTaxLink = prTaxLinkMapper.toEntity(prTaxLinkDTO);
        prTaxLink = prTaxLinkRepository.save(prTaxLink);
        PrTaxLinkDTO result = prTaxLinkMapper.toDto(prTaxLink);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prTaxLinkDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-tax-links : get all the prTaxLinks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prTaxLinks in body
     */
    @GetMapping("/pr-tax-links")
    @Timed
    public ResponseEntity<List<PrTaxLinkDTO>> getAllPrTaxLinks(Pageable pageable) {
        log.debug("REST request to get a page of PrTaxLinks");
        Page<PrTaxLink> page = prTaxLinkRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-tax-links");
        return new ResponseEntity<>(prTaxLinkMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /pr-tax-links/:id : get the "id" prTaxLink.
     *
     * @param id the id of the prTaxLinkDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prTaxLinkDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-tax-links/{id}")
    @Timed
    public ResponseEntity<PrTaxLinkDTO> getPrTaxLink(@PathVariable Long id) {
        log.debug("REST request to get PrTaxLink : {}", id);
        PrTaxLink prTaxLink = prTaxLinkRepository.findOne(id);
        PrTaxLinkDTO prTaxLinkDTO = prTaxLinkMapper.toDto(prTaxLink);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prTaxLinkDTO));
    }

    /**
     * DELETE  /pr-tax-links/:id : delete the "id" prTaxLink.
     *
     * @param id the id of the prTaxLinkDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-tax-links/{id}")
    @Timed
    public ResponseEntity<Void> deletePrTaxLink(@PathVariable Long id) {
        log.debug("REST request to delete PrTaxLink : {}", id);
        prTaxLinkRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
