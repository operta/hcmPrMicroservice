package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrTypeOfTaxes;

import ba.infostudio.com.repository.PrTypeOfTaxesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrTypeOfTaxesDTO;
import ba.infostudio.com.service.mapper.PrTypeOfTaxesMapper;
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
 * REST controller for managing PrTypeOfTaxes.
 */
@RestController
@RequestMapping("/api")
public class PrTypeOfTaxesResource {

    private final Logger log = LoggerFactory.getLogger(PrTypeOfTaxesResource.class);

    private static final String ENTITY_NAME = "prTypeOfTaxes";

    private final PrTypeOfTaxesRepository prTypeOfTaxesRepository;

    private final PrTypeOfTaxesMapper prTypeOfTaxesMapper;

    public PrTypeOfTaxesResource(PrTypeOfTaxesRepository prTypeOfTaxesRepository, PrTypeOfTaxesMapper prTypeOfTaxesMapper) {
        this.prTypeOfTaxesRepository = prTypeOfTaxesRepository;
        this.prTypeOfTaxesMapper = prTypeOfTaxesMapper;
    }

    /**
     * POST  /pr-type-of-taxes : Create a new prTypeOfTaxes.
     *
     * @param prTypeOfTaxesDTO the prTypeOfTaxesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prTypeOfTaxesDTO, or with status 400 (Bad Request) if the prTypeOfTaxes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-type-of-taxes")
    @Timed
    public ResponseEntity<PrTypeOfTaxesDTO> createPrTypeOfTaxes(@Valid @RequestBody PrTypeOfTaxesDTO prTypeOfTaxesDTO) throws URISyntaxException {
        log.debug("REST request to save PrTypeOfTaxes : {}", prTypeOfTaxesDTO);
        if (prTypeOfTaxesDTO.getId() != null) {
            throw new BadRequestAlertException("A new prTypeOfTaxes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrTypeOfTaxes prTypeOfTaxes = prTypeOfTaxesMapper.toEntity(prTypeOfTaxesDTO);
        prTypeOfTaxes = prTypeOfTaxesRepository.save(prTypeOfTaxes);
        PrTypeOfTaxesDTO result = prTypeOfTaxesMapper.toDto(prTypeOfTaxes);
        return ResponseEntity.created(new URI("/api/pr-type-of-taxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-type-of-taxes : Updates an existing prTypeOfTaxes.
     *
     * @param prTypeOfTaxesDTO the prTypeOfTaxesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prTypeOfTaxesDTO,
     * or with status 400 (Bad Request) if the prTypeOfTaxesDTO is not valid,
     * or with status 500 (Internal Server Error) if the prTypeOfTaxesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-type-of-taxes")
    @Timed
    public ResponseEntity<PrTypeOfTaxesDTO> updatePrTypeOfTaxes(@Valid @RequestBody PrTypeOfTaxesDTO prTypeOfTaxesDTO) throws URISyntaxException {
        log.debug("REST request to update PrTypeOfTaxes : {}", prTypeOfTaxesDTO);
        if (prTypeOfTaxesDTO.getId() == null) {
            return createPrTypeOfTaxes(prTypeOfTaxesDTO);
        }
        PrTypeOfTaxes prTypeOfTaxes = prTypeOfTaxesMapper.toEntity(prTypeOfTaxesDTO);
        prTypeOfTaxes = prTypeOfTaxesRepository.save(prTypeOfTaxes);
        PrTypeOfTaxesDTO result = prTypeOfTaxesMapper.toDto(prTypeOfTaxes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prTypeOfTaxesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-type-of-taxes : get all the prTypeOfTaxes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prTypeOfTaxes in body
     */
    @GetMapping("/pr-type-of-taxes")
    @Timed
    public ResponseEntity<List<PrTypeOfTaxesDTO>> getAllPrTypeOfTaxes(Pageable pageable) {
        log.debug("REST request to get a page of PrTypeOfTaxes");
        Page<PrTypeOfTaxes> page = prTypeOfTaxesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-type-of-taxes");
        return new ResponseEntity<>(prTypeOfTaxesMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /pr-type-of-taxes/:id : get the "id" prTypeOfTaxes.
     *
     * @param id the id of the prTypeOfTaxesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prTypeOfTaxesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-type-of-taxes/{id}")
    @Timed
    public ResponseEntity<PrTypeOfTaxesDTO> getPrTypeOfTaxes(@PathVariable Long id) {
        log.debug("REST request to get PrTypeOfTaxes : {}", id);
        PrTypeOfTaxes prTypeOfTaxes = prTypeOfTaxesRepository.findOne(id);
        PrTypeOfTaxesDTO prTypeOfTaxesDTO = prTypeOfTaxesMapper.toDto(prTypeOfTaxes);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prTypeOfTaxesDTO));
    }

    /**
     * DELETE  /pr-type-of-taxes/:id : delete the "id" prTypeOfTaxes.
     *
     * @param id the id of the prTypeOfTaxesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-type-of-taxes/{id}")
    @Timed
    public ResponseEntity<Void> deletePrTypeOfTaxes(@PathVariable Long id) {
        log.debug("REST request to delete PrTypeOfTaxes : {}", id);
        prTypeOfTaxesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
