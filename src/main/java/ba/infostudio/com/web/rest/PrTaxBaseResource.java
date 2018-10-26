package ba.infostudio.com.web.rest;

import org.apache.commons.lang.RandomStringUtils;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrTaxBase;

import ba.infostudio.com.repository.PrTaxBaseRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrTaxBaseDTO;
import ba.infostudio.com.service.mapper.PrTaxBaseMapper;
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
 * REST controller for managing PrTaxBase.
 */
@RestController
@RequestMapping("/api")
public class PrTaxBaseResource {

    private final Logger log = LoggerFactory.getLogger(PrTaxBaseResource.class);

    private static final String ENTITY_NAME = "prTaxBase";

    private final PrTaxBaseRepository prTaxBaseRepository;

    private final PrTaxBaseMapper prTaxBaseMapper;

    public PrTaxBaseResource(PrTaxBaseRepository prTaxBaseRepository, PrTaxBaseMapper prTaxBaseMapper) {
        this.prTaxBaseRepository = prTaxBaseRepository;
        this.prTaxBaseMapper = prTaxBaseMapper;
    }

    /**
     * POST  /pr-tax-bases : Create a new prTaxBase.
     *
     * @param prTaxBaseDTO the prTaxBaseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prTaxBaseDTO, or with status 400 (Bad Request) if the prTaxBase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-tax-bases")
    @Timed
    public ResponseEntity<PrTaxBaseDTO> createPrTaxBase(@Valid @RequestBody PrTaxBaseDTO prTaxBaseDTO) throws URISyntaxException {
        log.debug("REST request to save PrTaxBase : {}", prTaxBaseDTO);
        if (prTaxBaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new prTaxBase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String newCode = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
        while(prTaxBaseRepository.findByCode(newCode) != null){
            newCode = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
        }
        prTaxBaseDTO.setCode(newCode);
        PrTaxBase prTaxBase = prTaxBaseMapper.toEntity(prTaxBaseDTO);
        prTaxBase = prTaxBaseRepository.save(prTaxBase);
        PrTaxBaseDTO result = prTaxBaseMapper.toDto(prTaxBase);
        return ResponseEntity.created(new URI("/api/pr-tax-bases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-tax-bases : Updates an existing prTaxBase.
     *
     * @param prTaxBaseDTO the prTaxBaseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prTaxBaseDTO,
     * or with status 400 (Bad Request) if the prTaxBaseDTO is not valid,
     * or with status 500 (Internal Server Error) if the prTaxBaseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-tax-bases")
    @Timed
    public ResponseEntity<PrTaxBaseDTO> updatePrTaxBase(@Valid @RequestBody PrTaxBaseDTO prTaxBaseDTO) throws URISyntaxException {
        log.debug("REST request to update PrTaxBase : {}", prTaxBaseDTO);
        if (prTaxBaseDTO.getId() == null) {
            return createPrTaxBase(prTaxBaseDTO);
        }
        PrTaxBase prTaxBase = prTaxBaseMapper.toEntity(prTaxBaseDTO);
        prTaxBase = prTaxBaseRepository.save(prTaxBase);
        PrTaxBaseDTO result = prTaxBaseMapper.toDto(prTaxBase);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prTaxBaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-tax-bases : get all the prTaxBases.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prTaxBases in body
     */
    @GetMapping("/pr-tax-bases")
    @Timed
    public ResponseEntity<List<PrTaxBaseDTO>> getAllPrTaxBases(Pageable pageable) {
        log.debug("REST request to get a page of PrTaxBases");
        Page<PrTaxBase> page = prTaxBaseRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-tax-bases");
        return new ResponseEntity<>(prTaxBaseMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /pr-tax-bases/:id : get the "id" prTaxBase.
     *
     * @param id the id of the prTaxBaseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prTaxBaseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-tax-bases/{id}")
    @Timed
    public ResponseEntity<PrTaxBaseDTO> getPrTaxBase(@PathVariable Long id) {
        log.debug("REST request to get PrTaxBase : {}", id);
        PrTaxBase prTaxBase = prTaxBaseRepository.findOne(id);
        PrTaxBaseDTO prTaxBaseDTO = prTaxBaseMapper.toDto(prTaxBase);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prTaxBaseDTO));
    }

    /**
     * DELETE  /pr-tax-bases/:id : delete the "id" prTaxBase.
     *
     * @param id the id of the prTaxBaseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-tax-bases/{id}")
    @Timed
    public ResponseEntity<Void> deletePrTaxBase(@PathVariable Long id) {
        log.debug("REST request to delete PrTaxBase : {}", id);
        prTaxBaseRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
