package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.EmContractTypes;

import ba.infostudio.com.repository.EmContractTypesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.EmContractTypesDTO;
import ba.infostudio.com.service.mapper.EmContractTypesMapper;
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
 * REST controller for managing EmContractTypes.
 */
@RestController
@RequestMapping("/api")
public class EmContractTypesResource {

    private final Logger log = LoggerFactory.getLogger(EmContractTypesResource.class);

    private static final String ENTITY_NAME = "emContractTypes";

    private final EmContractTypesRepository emContractTypesRepository;

    private final EmContractTypesMapper emContractTypesMapper;

    public EmContractTypesResource(EmContractTypesRepository emContractTypesRepository, EmContractTypesMapper emContractTypesMapper) {
        this.emContractTypesRepository = emContractTypesRepository;
        this.emContractTypesMapper = emContractTypesMapper;
    }

    /**
     * POST  /em-contract-types : Create a new emContractTypes.
     *
     * @param emContractTypesDTO the emContractTypesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emContractTypesDTO, or with status 400 (Bad Request) if the emContractTypes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/em-contract-types")
    @Timed
    public ResponseEntity<EmContractTypesDTO> createEmContractTypes(@Valid @RequestBody EmContractTypesDTO emContractTypesDTO) throws URISyntaxException {
        log.debug("REST request to save EmContractTypes : {}", emContractTypesDTO);
        if (emContractTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new emContractTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmContractTypes emContractTypes = emContractTypesMapper.toEntity(emContractTypesDTO);
        emContractTypes = emContractTypesRepository.save(emContractTypes);
        EmContractTypesDTO result = emContractTypesMapper.toDto(emContractTypes);
        return ResponseEntity.created(new URI("/api/em-contract-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /em-contract-types : Updates an existing emContractTypes.
     *
     * @param emContractTypesDTO the emContractTypesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emContractTypesDTO,
     * or with status 400 (Bad Request) if the emContractTypesDTO is not valid,
     * or with status 500 (Internal Server Error) if the emContractTypesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/em-contract-types")
    @Timed
    public ResponseEntity<EmContractTypesDTO> updateEmContractTypes(@Valid @RequestBody EmContractTypesDTO emContractTypesDTO) throws URISyntaxException {
        log.debug("REST request to update EmContractTypes : {}", emContractTypesDTO);
        if (emContractTypesDTO.getId() == null) {
            return createEmContractTypes(emContractTypesDTO);
        }
        EmContractTypes emContractTypes = emContractTypesMapper.toEntity(emContractTypesDTO);
        emContractTypes = emContractTypesRepository.save(emContractTypes);
        EmContractTypesDTO result = emContractTypesMapper.toDto(emContractTypes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emContractTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /em-contract-types : get all the emContractTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of emContractTypes in body
     */
    @GetMapping("/em-contract-types")
    @Timed
    public ResponseEntity<List<EmContractTypesDTO>> getAllEmContractTypes(Pageable pageable) {
        log.debug("REST request to get a page of EmContractTypes");
        Page<EmContractTypes> page = emContractTypesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/em-contract-types");
        return new ResponseEntity<>(emContractTypesMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /em-contract-types/:id : get the "id" emContractTypes.
     *
     * @param id the id of the emContractTypesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emContractTypesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/em-contract-types/{id}")
    @Timed
    public ResponseEntity<EmContractTypesDTO> getEmContractTypes(@PathVariable Long id) {
        log.debug("REST request to get EmContractTypes : {}", id);
        EmContractTypes emContractTypes = emContractTypesRepository.findOne(id);
        EmContractTypesDTO emContractTypesDTO = emContractTypesMapper.toDto(emContractTypes);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(emContractTypesDTO));
    }

    /**
     * DELETE  /em-contract-types/:id : delete the "id" emContractTypes.
     *
     * @param id the id of the emContractTypesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/em-contract-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmContractTypes(@PathVariable Long id) {
        log.debug("REST request to delete EmContractTypes : {}", id);
        emContractTypesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
