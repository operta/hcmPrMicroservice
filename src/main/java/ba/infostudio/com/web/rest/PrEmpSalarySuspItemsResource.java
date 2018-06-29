package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrEmpSalarySuspItems;

import ba.infostudio.com.repository.PrEmpSalarySuspItemsRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrEmpSalarySuspItemsDTO;
import ba.infostudio.com.service.mapper.PrEmpSalarySuspItemsMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PrEmpSalarySuspItems.
 */
@RestController
@RequestMapping("/api")
public class PrEmpSalarySuspItemsResource {

    private final Logger log = LoggerFactory.getLogger(PrEmpSalarySuspItemsResource.class);

    private static final String ENTITY_NAME = "prEmpSalarySuspItems";

    private final PrEmpSalarySuspItemsRepository prEmpSalarySuspItemsRepository;

    private final PrEmpSalarySuspItemsMapper prEmpSalarySuspItemsMapper;

    public PrEmpSalarySuspItemsResource(PrEmpSalarySuspItemsRepository prEmpSalarySuspItemsRepository, PrEmpSalarySuspItemsMapper prEmpSalarySuspItemsMapper) {
        this.prEmpSalarySuspItemsRepository = prEmpSalarySuspItemsRepository;
        this.prEmpSalarySuspItemsMapper = prEmpSalarySuspItemsMapper;
    }

    /**
     * POST  /pr-emp-salary-susp-items : Create a new prEmpSalarySuspItems.
     *
     * @param prEmpSalarySuspItemsDTO the prEmpSalarySuspItemsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prEmpSalarySuspItemsDTO, or with status 400 (Bad Request) if the prEmpSalarySuspItems has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-emp-salary-susp-items")
    @Timed
    public ResponseEntity<PrEmpSalarySuspItemsDTO> createPrEmpSalarySuspItems(@RequestBody PrEmpSalarySuspItemsDTO prEmpSalarySuspItemsDTO) throws URISyntaxException {
        log.debug("REST request to save PrEmpSalarySuspItems : {}", prEmpSalarySuspItemsDTO);
        if (prEmpSalarySuspItemsDTO.getId() != null) {
            throw new BadRequestAlertException("A new prEmpSalarySuspItems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrEmpSalarySuspItems prEmpSalarySuspItems = prEmpSalarySuspItemsMapper.toEntity(prEmpSalarySuspItemsDTO);
        prEmpSalarySuspItems = prEmpSalarySuspItemsRepository.save(prEmpSalarySuspItems);
        PrEmpSalarySuspItemsDTO result = prEmpSalarySuspItemsMapper.toDto(prEmpSalarySuspItems);
        return ResponseEntity.created(new URI("/api/pr-emp-salary-susp-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-emp-salary-susp-items : Updates an existing prEmpSalarySuspItems.
     *
     * @param prEmpSalarySuspItemsDTO the prEmpSalarySuspItemsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prEmpSalarySuspItemsDTO,
     * or with status 400 (Bad Request) if the prEmpSalarySuspItemsDTO is not valid,
     * or with status 500 (Internal Server Error) if the prEmpSalarySuspItemsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-emp-salary-susp-items")
    @Timed
    public ResponseEntity<PrEmpSalarySuspItemsDTO> updatePrEmpSalarySuspItems(@RequestBody PrEmpSalarySuspItemsDTO prEmpSalarySuspItemsDTO) throws URISyntaxException {
        log.debug("REST request to update PrEmpSalarySuspItems : {}", prEmpSalarySuspItemsDTO);
        if (prEmpSalarySuspItemsDTO.getId() == null) {
            return createPrEmpSalarySuspItems(prEmpSalarySuspItemsDTO);
        }
        PrEmpSalarySuspItems prEmpSalarySuspItems = prEmpSalarySuspItemsMapper.toEntity(prEmpSalarySuspItemsDTO);
        prEmpSalarySuspItems = prEmpSalarySuspItemsRepository.save(prEmpSalarySuspItems);
        PrEmpSalarySuspItemsDTO result = prEmpSalarySuspItemsMapper.toDto(prEmpSalarySuspItems);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prEmpSalarySuspItemsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-emp-salary-susp-items : get all the prEmpSalarySuspItems.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prEmpSalarySuspItems in body
     */
    @GetMapping("/pr-emp-salary-susp-items")
    @Timed
    public ResponseEntity<List<PrEmpSalarySuspItemsDTO>> getAllPrEmpSalarySuspItems(Pageable pageable) {
        log.debug("REST request to get a page of PrEmpSalarySuspItems");
        Page<PrEmpSalarySuspItems> page = prEmpSalarySuspItemsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-emp-salary-susp-items");
        return new ResponseEntity<>(prEmpSalarySuspItemsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /pr-emp-salary-susp-items : get all the prEmpSalaryItems. by emp salary
     *
     * @param id the id of emp salary
     * @return the ResponseEntity with status 200 (OK) and the list of prEmpSalaryItems in body
     */
    @GetMapping("/pr-emp-salary-susp-items/salary/{id}")
    @Timed
    public ResponseEntity<List<PrEmpSalarySuspItemsDTO>> getSalaryItemsBySalaryId(@PathVariable Integer id) {
        log.debug("REST request to get a page of PrEmpSalarySuspItems");
        List<PrEmpSalarySuspItems> items = prEmpSalarySuspItemsRepository.findByEmployeeSalaryId(id.longValue());
        List<PrEmpSalarySuspItemsDTO> dtoitems = new ArrayList<PrEmpSalarySuspItemsDTO>();
        for (PrEmpSalarySuspItems item : items) {
            dtoitems.add(prEmpSalarySuspItemsMapper.toDto(item));
        }
        return new ResponseEntity<>(dtoitems, null, HttpStatus.OK);
    }

       /**
     * GET  /pr-emp-salary-susp-items : get all the prEmpSalaryItems. by emp supsension
     *
     * @param id the id of emp suspension
     * @return the ResponseEntity with status 200 (OK) and the list of prEmpSalaryItems in body
     */
    @GetMapping("/pr-emp-salary-susp-items/suspension/{id}")
    @Timed
    public ResponseEntity<List<PrEmpSalarySuspItemsDTO>> getSalaryItemsBySuspensionId(@PathVariable Integer id) {
        log.debug("REST request to get a page of PrEmpSalarySuspItems");
        List<PrEmpSalarySuspItems> items = prEmpSalarySuspItemsRepository.findByEmpSuspensionId(id.longValue());
        List<PrEmpSalarySuspItemsDTO> dtoitems = new ArrayList<PrEmpSalarySuspItemsDTO>();
        for (PrEmpSalarySuspItems item : items) {
            dtoitems.add(prEmpSalarySuspItemsMapper.toDto(item));
        }
        return new ResponseEntity<>(dtoitems, null, HttpStatus.OK);
    }


    /**
     * GET  /pr-emp-salary-susp-items/:id : get the "id" prEmpSalarySuspItems.
     *
     * @param id the id of the prEmpSalarySuspItemsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prEmpSalarySuspItemsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-emp-salary-susp-items/{id}")
    @Timed
    public ResponseEntity<PrEmpSalarySuspItemsDTO> getPrEmpSalarySuspItems(@PathVariable Long id) {
        log.debug("REST request to get PrEmpSalarySuspItems : {}", id);
        PrEmpSalarySuspItems prEmpSalarySuspItems = prEmpSalarySuspItemsRepository.findOne(id);
        PrEmpSalarySuspItemsDTO prEmpSalarySuspItemsDTO = prEmpSalarySuspItemsMapper.toDto(prEmpSalarySuspItems);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prEmpSalarySuspItemsDTO));
    }

    /**
     * DELETE  /pr-emp-salary-susp-items/:id : delete the "id" prEmpSalarySuspItems.
     *
     * @param id the id of the prEmpSalarySuspItemsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-emp-salary-susp-items/{id}")
    @Timed
    public ResponseEntity<Void> deletePrEmpSalarySuspItems(@PathVariable Long id) {
        log.debug("REST request to delete PrEmpSalarySuspItems : {}", id);
        prEmpSalarySuspItemsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
