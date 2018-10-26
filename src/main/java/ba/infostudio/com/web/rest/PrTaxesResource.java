package ba.infostudio.com.web.rest;

import org.apache.commons.lang.RandomStringUtils;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrTaxes;

import ba.infostudio.com.repository.PrTaxesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrTaxesDTO;
import ba.infostudio.com.service.mapper.PrTaxesMapper;
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
 * REST controller for managing PrTaxes.
 */
@RestController
@RequestMapping("/api")
public class PrTaxesResource {

    private final Logger log = LoggerFactory.getLogger(PrTaxesResource.class);

    private static final String ENTITY_NAME = "prTaxes";

    private final PrTaxesRepository prTaxesRepository;

    private final PrTaxesMapper prTaxesMapper;

    public PrTaxesResource(PrTaxesRepository prTaxesRepository, PrTaxesMapper prTaxesMapper) {
        this.prTaxesRepository = prTaxesRepository;
        this.prTaxesMapper = prTaxesMapper;
    }

    /**
     * POST  /pr-taxes : Create a new prTaxes.
     *
     * @param prTaxesDTO the prTaxesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prTaxesDTO, or with status 400 (Bad Request) if the prTaxes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-taxes")
    @Timed
    public ResponseEntity<PrTaxesDTO> createPrTaxes(@Valid @RequestBody PrTaxesDTO prTaxesDTO) throws URISyntaxException {
        log.debug("REST request to save PrTaxes : {}", prTaxesDTO);
        if (prTaxesDTO.getId() != null) {
            throw new BadRequestAlertException("A new prTaxes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String newCode = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
        while(prTaxesRepository.findByCode(newCode) != null){
            newCode = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
        }
        prTaxesDTO.setCode(newCode);
        PrTaxes prTaxes = prTaxesMapper.toEntity(prTaxesDTO);
        prTaxes = prTaxesRepository.save(prTaxes);
        PrTaxesDTO result = prTaxesMapper.toDto(prTaxes);
        return ResponseEntity.created(new URI("/api/pr-taxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-taxes : Updates an existing prTaxes.
     *
     * @param prTaxesDTO the prTaxesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prTaxesDTO,
     * or with status 400 (Bad Request) if the prTaxesDTO is not valid,
     * or with status 500 (Internal Server Error) if the prTaxesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-taxes")
    @Timed
    public ResponseEntity<PrTaxesDTO> updatePrTaxes(@Valid @RequestBody PrTaxesDTO prTaxesDTO) throws URISyntaxException {
        log.debug("REST request to update PrTaxes : {}", prTaxesDTO);
        if (prTaxesDTO.getId() == null) {
            return createPrTaxes(prTaxesDTO);
        }
        PrTaxes prTaxes = prTaxesMapper.toEntity(prTaxesDTO);
        prTaxes = prTaxesRepository.save(prTaxes);
        PrTaxesDTO result = prTaxesMapper.toDto(prTaxes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prTaxesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-taxes : get all the prTaxes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prTaxes in body
     */
    @GetMapping("/pr-taxes")
    @Timed
    public ResponseEntity<List<PrTaxesDTO>> getAllPrTaxes(Pageable pageable) {
        log.debug("REST request to get a page of PrTaxes");
        Page<PrTaxes> page = prTaxesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-taxes");
        return new ResponseEntity<>(prTaxesMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /pr-taxes/:id : get the "id" prTaxes.
     *
     * @param id the id of the prTaxesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prTaxesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-taxes/{id}")
    @Timed
    public ResponseEntity<PrTaxesDTO> getPrTaxes(@PathVariable Long id) {
        log.debug("REST request to get PrTaxes : {}", id);
        PrTaxes prTaxes = prTaxesRepository.findOne(id);
        PrTaxesDTO prTaxesDTO = prTaxesMapper.toDto(prTaxes);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prTaxesDTO));
    }

    /**
     * DELETE  /pr-taxes/:id : delete the "id" prTaxes.
     *
     * @param id the id of the prTaxesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-taxes/{id}")
    @Timed
    public ResponseEntity<Void> deletePrTaxes(@PathVariable Long id) {
        log.debug("REST request to delete PrTaxes : {}", id);
        prTaxesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
