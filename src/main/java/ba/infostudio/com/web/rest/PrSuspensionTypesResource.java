package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrSuspensionTypes;

import ba.infostudio.com.repository.PrSuspensionTypesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrSuspensionTypesDTO;
import ba.infostudio.com.service.mapper.PrSuspensionTypesMapper;
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
 * REST controller for managing PrSuspensionTypes.
 */
@RestController
@RequestMapping("/api")
public class PrSuspensionTypesResource {

    private final Logger log = LoggerFactory.getLogger(PrSuspensionTypesResource.class);

    private static final String ENTITY_NAME = "prSuspensionTypes";

    private final PrSuspensionTypesRepository prSuspensionTypesRepository;

    private final PrSuspensionTypesMapper prSuspensionTypesMapper;

    public PrSuspensionTypesResource(PrSuspensionTypesRepository prSuspensionTypesRepository, PrSuspensionTypesMapper prSuspensionTypesMapper) {
        this.prSuspensionTypesRepository = prSuspensionTypesRepository;
        this.prSuspensionTypesMapper = prSuspensionTypesMapper;
    }

    /**
     * POST  /pr-suspension-types : Create a new prSuspensionTypes.
     *
     * @param prSuspensionTypesDTO the prSuspensionTypesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prSuspensionTypesDTO, or with status 400 (Bad Request) if the prSuspensionTypes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-suspension-types")
    @Timed
    public ResponseEntity<PrSuspensionTypesDTO> createPrSuspensionTypes(@Valid @RequestBody PrSuspensionTypesDTO prSuspensionTypesDTO) throws URISyntaxException {
        log.debug("REST request to save PrSuspensionTypes : {}", prSuspensionTypesDTO);
        if (prSuspensionTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new prSuspensionTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrSuspensionTypes prSuspensionTypes = prSuspensionTypesMapper.toEntity(prSuspensionTypesDTO);
        prSuspensionTypes = prSuspensionTypesRepository.save(prSuspensionTypes);
        PrSuspensionTypesDTO result = prSuspensionTypesMapper.toDto(prSuspensionTypes);
        return ResponseEntity.created(new URI("/api/pr-suspension-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-suspension-types : Updates an existing prSuspensionTypes.
     *
     * @param prSuspensionTypesDTO the prSuspensionTypesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prSuspensionTypesDTO,
     * or with status 400 (Bad Request) if the prSuspensionTypesDTO is not valid,
     * or with status 500 (Internal Server Error) if the prSuspensionTypesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-suspension-types")
    @Timed
    public ResponseEntity<PrSuspensionTypesDTO> updatePrSuspensionTypes(@Valid @RequestBody PrSuspensionTypesDTO prSuspensionTypesDTO) throws URISyntaxException {
        log.debug("REST request to update PrSuspensionTypes : {}", prSuspensionTypesDTO);
        if (prSuspensionTypesDTO.getId() == null) {
            return createPrSuspensionTypes(prSuspensionTypesDTO);
        }
        PrSuspensionTypes prSuspensionTypes = prSuspensionTypesMapper.toEntity(prSuspensionTypesDTO);
        prSuspensionTypes = prSuspensionTypesRepository.save(prSuspensionTypes);
        PrSuspensionTypesDTO result = prSuspensionTypesMapper.toDto(prSuspensionTypes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prSuspensionTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-suspension-types : get all the prSuspensionTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prSuspensionTypes in body
     */
    @GetMapping("/pr-suspension-types")
    @Timed
    public ResponseEntity<List<PrSuspensionTypesDTO>> getAllPrSuspensionTypes(Pageable pageable) {
        log.debug("REST request to get a page of PrSuspensionTypes");
        Page<PrSuspensionTypes> page = prSuspensionTypesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-suspension-types");
        return new ResponseEntity<>(prSuspensionTypesMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /pr-suspension-types/:id : get the "id" prSuspensionTypes.
     *
     * @param id the id of the prSuspensionTypesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prSuspensionTypesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-suspension-types/{id}")
    @Timed
    public ResponseEntity<PrSuspensionTypesDTO> getPrSuspensionTypes(@PathVariable Long id) {
        log.debug("REST request to get PrSuspensionTypes : {}", id);
        PrSuspensionTypes prSuspensionTypes = prSuspensionTypesRepository.findOne(id);
        PrSuspensionTypesDTO prSuspensionTypesDTO = prSuspensionTypesMapper.toDto(prSuspensionTypes);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prSuspensionTypesDTO));
    }

    /**
     * DELETE  /pr-suspension-types/:id : delete the "id" prSuspensionTypes.
     *
     * @param id the id of the prSuspensionTypesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-suspension-types/{id}")
    @Timed
    public ResponseEntity<Void> deletePrSuspensionTypes(@PathVariable Long id) {
        log.debug("REST request to delete PrSuspensionTypes : {}", id);
        prSuspensionTypesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
