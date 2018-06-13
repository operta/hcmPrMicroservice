package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrSalaryTypes;

import ba.infostudio.com.repository.PrSalaryTypesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrSalaryTypesDTO;
import ba.infostudio.com.service.mapper.PrSalaryTypesMapper;
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
 * REST controller for managing PrSalaryTypes.
 */
@RestController
@RequestMapping("/api")
public class PrSalaryTypesResource {

    private final Logger log = LoggerFactory.getLogger(PrSalaryTypesResource.class);

    private static final String ENTITY_NAME = "prSalaryTypes";

    private final PrSalaryTypesRepository prSalaryTypesRepository;

    private final PrSalaryTypesMapper prSalaryTypesMapper;

    public PrSalaryTypesResource(PrSalaryTypesRepository prSalaryTypesRepository, PrSalaryTypesMapper prSalaryTypesMapper) {
        this.prSalaryTypesRepository = prSalaryTypesRepository;
        this.prSalaryTypesMapper = prSalaryTypesMapper;
    }

    /**
     * POST  /pr-salary-types : Create a new prSalaryTypes.
     *
     * @param prSalaryTypesDTO the prSalaryTypesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prSalaryTypesDTO, or with status 400 (Bad Request) if the prSalaryTypes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-salary-types")
    @Timed
    public ResponseEntity<PrSalaryTypesDTO> createPrSalaryTypes(@Valid @RequestBody PrSalaryTypesDTO prSalaryTypesDTO) throws URISyntaxException {
        log.debug("REST request to save PrSalaryTypes : {}", prSalaryTypesDTO);
        if (prSalaryTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new prSalaryTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrSalaryTypes prSalaryTypes = prSalaryTypesMapper.toEntity(prSalaryTypesDTO);
        prSalaryTypes = prSalaryTypesRepository.save(prSalaryTypes);
        PrSalaryTypesDTO result = prSalaryTypesMapper.toDto(prSalaryTypes);
        return ResponseEntity.created(new URI("/api/pr-salary-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-salary-types : Updates an existing prSalaryTypes.
     *
     * @param prSalaryTypesDTO the prSalaryTypesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prSalaryTypesDTO,
     * or with status 400 (Bad Request) if the prSalaryTypesDTO is not valid,
     * or with status 500 (Internal Server Error) if the prSalaryTypesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-salary-types")
    @Timed
    public ResponseEntity<PrSalaryTypesDTO> updatePrSalaryTypes(@Valid @RequestBody PrSalaryTypesDTO prSalaryTypesDTO) throws URISyntaxException {
        log.debug("REST request to update PrSalaryTypes : {}", prSalaryTypesDTO);
        if (prSalaryTypesDTO.getId() == null) {
            return createPrSalaryTypes(prSalaryTypesDTO);
        }
        PrSalaryTypes prSalaryTypes = prSalaryTypesMapper.toEntity(prSalaryTypesDTO);
        prSalaryTypes = prSalaryTypesRepository.save(prSalaryTypes);
        PrSalaryTypesDTO result = prSalaryTypesMapper.toDto(prSalaryTypes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prSalaryTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-salary-types : get all the prSalaryTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prSalaryTypes in body
     */
    @GetMapping("/pr-salary-types")
    @Timed
    public ResponseEntity<List<PrSalaryTypesDTO>> getAllPrSalaryTypes(Pageable pageable) {
        log.debug("REST request to get a page of PrSalaryTypes");
        Page<PrSalaryTypes> page = prSalaryTypesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-salary-types");
        return new ResponseEntity<>(prSalaryTypesMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /pr-salary-types/:id : get the "id" prSalaryTypes.
     *
     * @param id the id of the prSalaryTypesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prSalaryTypesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-salary-types/{id}")
    @Timed
    public ResponseEntity<PrSalaryTypesDTO> getPrSalaryTypes(@PathVariable Long id) {
        log.debug("REST request to get PrSalaryTypes : {}", id);
        PrSalaryTypes prSalaryTypes = prSalaryTypesRepository.findOne(id);
        PrSalaryTypesDTO prSalaryTypesDTO = prSalaryTypesMapper.toDto(prSalaryTypes);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prSalaryTypesDTO));
    }

    /**
     * DELETE  /pr-salary-types/:id : delete the "id" prSalaryTypes.
     *
     * @param id the id of the prSalaryTypesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-salary-types/{id}")
    @Timed
    public ResponseEntity<Void> deletePrSalaryTypes(@PathVariable Long id) {
        log.debug("REST request to delete PrSalaryTypes : {}", id);
        prSalaryTypesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
