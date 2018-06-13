package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrEmpSalarySettings;

import ba.infostudio.com.repository.PrEmpSalarySettingsRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrEmpSalarySettingsDTO;
import ba.infostudio.com.service.mapper.PrEmpSalarySettingsMapper;
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
 * REST controller for managing PrEmpSalarySettings.
 */
@RestController
@RequestMapping("/api")
public class PrEmpSalarySettingsResource {

    private final Logger log = LoggerFactory.getLogger(PrEmpSalarySettingsResource.class);

    private static final String ENTITY_NAME = "prEmpSalarySettings";

    private final PrEmpSalarySettingsRepository prEmpSalarySettingsRepository;

    private final PrEmpSalarySettingsMapper prEmpSalarySettingsMapper;

    public PrEmpSalarySettingsResource(PrEmpSalarySettingsRepository prEmpSalarySettingsRepository, PrEmpSalarySettingsMapper prEmpSalarySettingsMapper) {
        this.prEmpSalarySettingsRepository = prEmpSalarySettingsRepository;
        this.prEmpSalarySettingsMapper = prEmpSalarySettingsMapper;
    }

    /**
     * POST  /pr-emp-salary-settings : Create a new prEmpSalarySettings.
     *
     * @param prEmpSalarySettingsDTO the prEmpSalarySettingsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prEmpSalarySettingsDTO, or with status 400 (Bad Request) if the prEmpSalarySettings has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-emp-salary-settings")
    @Timed
    public ResponseEntity<PrEmpSalarySettingsDTO> createPrEmpSalarySettings(@Valid @RequestBody PrEmpSalarySettingsDTO prEmpSalarySettingsDTO) throws URISyntaxException {
        log.debug("REST request to save PrEmpSalarySettings : {}", prEmpSalarySettingsDTO);
        if (prEmpSalarySettingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new prEmpSalarySettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrEmpSalarySettings prEmpSalarySettings = prEmpSalarySettingsMapper.toEntity(prEmpSalarySettingsDTO);
        prEmpSalarySettings = prEmpSalarySettingsRepository.save(prEmpSalarySettings);
        PrEmpSalarySettingsDTO result = prEmpSalarySettingsMapper.toDto(prEmpSalarySettings);
        return ResponseEntity.created(new URI("/api/pr-emp-salary-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-emp-salary-settings : Updates an existing prEmpSalarySettings.
     *
     * @param prEmpSalarySettingsDTO the prEmpSalarySettingsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prEmpSalarySettingsDTO,
     * or with status 400 (Bad Request) if the prEmpSalarySettingsDTO is not valid,
     * or with status 500 (Internal Server Error) if the prEmpSalarySettingsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-emp-salary-settings")
    @Timed
    public ResponseEntity<PrEmpSalarySettingsDTO> updatePrEmpSalarySettings(@Valid @RequestBody PrEmpSalarySettingsDTO prEmpSalarySettingsDTO) throws URISyntaxException {
        log.debug("REST request to update PrEmpSalarySettings : {}", prEmpSalarySettingsDTO);
        if (prEmpSalarySettingsDTO.getId() == null) {
            return createPrEmpSalarySettings(prEmpSalarySettingsDTO);
        }
        PrEmpSalarySettings prEmpSalarySettings = prEmpSalarySettingsMapper.toEntity(prEmpSalarySettingsDTO);
        prEmpSalarySettings = prEmpSalarySettingsRepository.save(prEmpSalarySettings);
        PrEmpSalarySettingsDTO result = prEmpSalarySettingsMapper.toDto(prEmpSalarySettings);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prEmpSalarySettingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-emp-salary-settings : get all the prEmpSalarySettings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prEmpSalarySettings in body
     */
    @GetMapping("/pr-emp-salary-settings")
    @Timed
    public ResponseEntity<List<PrEmpSalarySettingsDTO>> getAllPrEmpSalarySettings(Pageable pageable) {
        log.debug("REST request to get a page of PrEmpSalarySettings");
        Page<PrEmpSalarySettings> page = prEmpSalarySettingsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-emp-salary-settings");
        return new ResponseEntity<>(prEmpSalarySettingsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /pr-emp-salary-settings/:id : get the "id" prEmpSalarySettings.
     *
     * @param id the id of the prEmpSalarySettingsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prEmpSalarySettingsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-emp-salary-settings/{id}")
    @Timed
    public ResponseEntity<PrEmpSalarySettingsDTO> getPrEmpSalarySettings(@PathVariable Long id) {
        log.debug("REST request to get PrEmpSalarySettings : {}", id);
        PrEmpSalarySettings prEmpSalarySettings = prEmpSalarySettingsRepository.findOne(id);
        PrEmpSalarySettingsDTO prEmpSalarySettingsDTO = prEmpSalarySettingsMapper.toDto(prEmpSalarySettings);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prEmpSalarySettingsDTO));
    }

    /**
     * DELETE  /pr-emp-salary-settings/:id : delete the "id" prEmpSalarySettings.
     *
     * @param id the id of the prEmpSalarySettingsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-emp-salary-settings/{id}")
    @Timed
    public ResponseEntity<Void> deletePrEmpSalarySettings(@PathVariable Long id) {
        log.debug("REST request to delete PrEmpSalarySettings : {}", id);
        prEmpSalarySettingsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
