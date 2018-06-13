package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrPayrollSettings;

import ba.infostudio.com.repository.PrPayrollSettingsRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrPayrollSettingsDTO;
import ba.infostudio.com.service.mapper.PrPayrollSettingsMapper;
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
 * REST controller for managing PrPayrollSettings.
 */
@RestController
@RequestMapping("/api")
public class PrPayrollSettingsResource {

    private final Logger log = LoggerFactory.getLogger(PrPayrollSettingsResource.class);

    private static final String ENTITY_NAME = "prPayrollSettings";

    private final PrPayrollSettingsRepository prPayrollSettingsRepository;

    private final PrPayrollSettingsMapper prPayrollSettingsMapper;

    public PrPayrollSettingsResource(PrPayrollSettingsRepository prPayrollSettingsRepository, PrPayrollSettingsMapper prPayrollSettingsMapper) {
        this.prPayrollSettingsRepository = prPayrollSettingsRepository;
        this.prPayrollSettingsMapper = prPayrollSettingsMapper;
    }

    /**
     * POST  /pr-payroll-settings : Create a new prPayrollSettings.
     *
     * @param prPayrollSettingsDTO the prPayrollSettingsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prPayrollSettingsDTO, or with status 400 (Bad Request) if the prPayrollSettings has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-payroll-settings")
    @Timed
    public ResponseEntity<PrPayrollSettingsDTO> createPrPayrollSettings(@Valid @RequestBody PrPayrollSettingsDTO prPayrollSettingsDTO) throws URISyntaxException {
        log.debug("REST request to save PrPayrollSettings : {}", prPayrollSettingsDTO);
        if (prPayrollSettingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new prPayrollSettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrPayrollSettings prPayrollSettings = prPayrollSettingsMapper.toEntity(prPayrollSettingsDTO);
        prPayrollSettings = prPayrollSettingsRepository.save(prPayrollSettings);
        PrPayrollSettingsDTO result = prPayrollSettingsMapper.toDto(prPayrollSettings);
        return ResponseEntity.created(new URI("/api/pr-payroll-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-payroll-settings : Updates an existing prPayrollSettings.
     *
     * @param prPayrollSettingsDTO the prPayrollSettingsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prPayrollSettingsDTO,
     * or with status 400 (Bad Request) if the prPayrollSettingsDTO is not valid,
     * or with status 500 (Internal Server Error) if the prPayrollSettingsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-payroll-settings")
    @Timed
    public ResponseEntity<PrPayrollSettingsDTO> updatePrPayrollSettings(@Valid @RequestBody PrPayrollSettingsDTO prPayrollSettingsDTO) throws URISyntaxException {
        log.debug("REST request to update PrPayrollSettings : {}", prPayrollSettingsDTO);
        if (prPayrollSettingsDTO.getId() == null) {
            return createPrPayrollSettings(prPayrollSettingsDTO);
        }
        PrPayrollSettings prPayrollSettings = prPayrollSettingsMapper.toEntity(prPayrollSettingsDTO);
        prPayrollSettings = prPayrollSettingsRepository.save(prPayrollSettings);
        PrPayrollSettingsDTO result = prPayrollSettingsMapper.toDto(prPayrollSettings);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prPayrollSettingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-payroll-settings : get all the prPayrollSettings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prPayrollSettings in body
     */
    @GetMapping("/pr-payroll-settings")
    @Timed
    public ResponseEntity<List<PrPayrollSettingsDTO>> getAllPrPayrollSettings(Pageable pageable) {
        log.debug("REST request to get a page of PrPayrollSettings");
        Page<PrPayrollSettings> page = prPayrollSettingsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-payroll-settings");
        return new ResponseEntity<>(prPayrollSettingsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /pr-payroll-settings/:id : get the "id" prPayrollSettings.
     *
     * @param id the id of the prPayrollSettingsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prPayrollSettingsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-payroll-settings/{id}")
    @Timed
    public ResponseEntity<PrPayrollSettingsDTO> getPrPayrollSettings(@PathVariable Long id) {
        log.debug("REST request to get PrPayrollSettings : {}", id);
        PrPayrollSettings prPayrollSettings = prPayrollSettingsRepository.findOne(id);
        PrPayrollSettingsDTO prPayrollSettingsDTO = prPayrollSettingsMapper.toDto(prPayrollSettings);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prPayrollSettingsDTO));
    }

    /**
     * DELETE  /pr-payroll-settings/:id : delete the "id" prPayrollSettings.
     *
     * @param id the id of the prPayrollSettingsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-payroll-settings/{id}")
    @Timed
    public ResponseEntity<Void> deletePrPayrollSettings(@PathVariable Long id) {
        log.debug("REST request to delete PrPayrollSettings : {}", id);
        prPayrollSettingsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
