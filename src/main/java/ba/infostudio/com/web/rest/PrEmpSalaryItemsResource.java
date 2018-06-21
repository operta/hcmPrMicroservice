package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrEmpSalaryItems;

import ba.infostudio.com.repository.PrEmpSalaryItemsRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrEmpSalaryItemsDTO;
import ba.infostudio.com.service.mapper.PrEmpSalaryItemsMapper;
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
 * REST controller for managing PrEmpSalaryItems.
 */
@RestController
@RequestMapping("/api")
public class PrEmpSalaryItemsResource {

    private final Logger log = LoggerFactory.getLogger(PrEmpSalaryItemsResource.class);

    private static final String ENTITY_NAME = "prEmpSalaryItems";

    private final PrEmpSalaryItemsRepository prEmpSalaryItemsRepository;

    private final PrEmpSalaryItemsMapper prEmpSalaryItemsMapper;

    public PrEmpSalaryItemsResource(PrEmpSalaryItemsRepository prEmpSalaryItemsRepository, PrEmpSalaryItemsMapper prEmpSalaryItemsMapper) {
        this.prEmpSalaryItemsRepository = prEmpSalaryItemsRepository;
        this.prEmpSalaryItemsMapper = prEmpSalaryItemsMapper;
    }

    /**
     * POST  /pr-emp-salary-items : Create a new prEmpSalaryItems.
     *
     * @param prEmpSalaryItemsDTO the prEmpSalaryItemsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prEmpSalaryItemsDTO, or with status 400 (Bad Request) if the prEmpSalaryItems has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-emp-salary-items")
    @Timed
    public ResponseEntity<PrEmpSalaryItemsDTO> createPrEmpSalaryItems(@RequestBody PrEmpSalaryItemsDTO prEmpSalaryItemsDTO) throws URISyntaxException {
        log.debug("REST request to save PrEmpSalaryItems : {}", prEmpSalaryItemsDTO);
        if (prEmpSalaryItemsDTO.getId() != null) {
            throw new BadRequestAlertException("A new prEmpSalaryItems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrEmpSalaryItems prEmpSalaryItems = prEmpSalaryItemsMapper.toEntity(prEmpSalaryItemsDTO);
        prEmpSalaryItems = prEmpSalaryItemsRepository.save(prEmpSalaryItems);
        PrEmpSalaryItemsDTO result = prEmpSalaryItemsMapper.toDto(prEmpSalaryItems);
        return ResponseEntity.created(new URI("/api/pr-emp-salary-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-emp-salary-items : Updates an existing prEmpSalaryItems.
     *
     * @param prEmpSalaryItemsDTO the prEmpSalaryItemsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prEmpSalaryItemsDTO,
     * or with status 400 (Bad Request) if the prEmpSalaryItemsDTO is not valid,
     * or with status 500 (Internal Server Error) if the prEmpSalaryItemsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-emp-salary-items")
    @Timed
    public ResponseEntity<PrEmpSalaryItemsDTO> updatePrEmpSalaryItems(@RequestBody PrEmpSalaryItemsDTO prEmpSalaryItemsDTO) throws URISyntaxException {
        log.debug("REST request to update PrEmpSalaryItems : {}", prEmpSalaryItemsDTO);
        if (prEmpSalaryItemsDTO.getId() == null) {
            return createPrEmpSalaryItems(prEmpSalaryItemsDTO);
        }
        PrEmpSalaryItems prEmpSalaryItems = prEmpSalaryItemsMapper.toEntity(prEmpSalaryItemsDTO);
        prEmpSalaryItems = prEmpSalaryItemsRepository.save(prEmpSalaryItems);
        PrEmpSalaryItemsDTO result = prEmpSalaryItemsMapper.toDto(prEmpSalaryItems);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prEmpSalaryItemsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-emp-salary-items : get all the prEmpSalaryItems.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prEmpSalaryItems in body
     */
    @GetMapping("/pr-emp-salary-items")
    @Timed
    public ResponseEntity<List<PrEmpSalaryItemsDTO>> getAllPrEmpSalaryItems(Pageable pageable) {
        log.debug("REST request to get a page of PrEmpSalaryItems");
        Page<PrEmpSalaryItems> page = prEmpSalaryItemsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-emp-salary-items");
        return new ResponseEntity<>(prEmpSalaryItemsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

        /**
     * GET  /pr-emp-salary-items : get all the prEmpSalaryItems. by emp salary
     *
     * @param id the id of emp salary
     * @return the ResponseEntity with status 200 (OK) and the list of prEmpSalaryItems in body
     */
    @GetMapping("/pr-emp-salary-items/salary/{id}")
    @Timed
    public ResponseEntity<List<PrEmpSalaryItemsDTO>> getSalaryItemsBySalaryId(@PathVariable Integer id) {
        log.debug("REST request to get a page of PrEmpSalaryItems");
        List<PrEmpSalaryItems> items = prEmpSalaryItemsRepository.findByEmployeeSalaryId(id.longValue());
        List<PrEmpSalaryItemsDTO> dtoitems = new ArrayList<PrEmpSalaryItemsDTO>();
        for (PrEmpSalaryItems item : items) {
            dtoitems.add(prEmpSalaryItemsMapper.toDto(item));
        }
        return new ResponseEntity<>(dtoitems, null, HttpStatus.OK);
    }

    /**
     * GET  /pr-emp-salary-items/:id : get the "id" prEmpSalaryItems.
     *
     * @param id the id of the prEmpSalaryItemsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prEmpSalaryItemsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-emp-salary-items/{id}")
    @Timed
    public ResponseEntity<PrEmpSalaryItemsDTO> getPrEmpSalaryItems(@PathVariable Long id) {
        log.debug("REST request to get PrEmpSalaryItems : {}", id);
        PrEmpSalaryItems prEmpSalaryItems = prEmpSalaryItemsRepository.findOne(id);
        PrEmpSalaryItemsDTO prEmpSalaryItemsDTO = prEmpSalaryItemsMapper.toDto(prEmpSalaryItems);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prEmpSalaryItemsDTO));
    }

    /**
     * DELETE  /pr-emp-salary-items/:id : delete the "id" prEmpSalaryItems.
     *
     * @param id the id of the prEmpSalaryItemsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-emp-salary-items/{id}")
    @Timed
    public ResponseEntity<Void> deletePrEmpSalaryItems(@PathVariable Long id) {
        log.debug("REST request to delete PrEmpSalaryItems : {}", id);
        prEmpSalaryItemsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
