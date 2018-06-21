package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.PrEmpSalaryTaxItems;

import ba.infostudio.com.repository.PrEmpSalaryTaxItemsRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.PrEmpSalaryTaxItemsDTO;
import ba.infostudio.com.service.mapper.PrEmpSalaryTaxItemsMapper;
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
 * REST controller for managing PrEmpSalaryTaxItems.
 */
@RestController
@RequestMapping("/api")
public class PrEmpSalaryTaxItemsResource {

    private final Logger log = LoggerFactory.getLogger(PrEmpSalaryTaxItemsResource.class);

    private static final String ENTITY_NAME = "prEmpSalaryTaxItems";

    private final PrEmpSalaryTaxItemsRepository prEmpSalaryTaxItemsRepository;

    private final PrEmpSalaryTaxItemsMapper prEmpSalaryTaxItemsMapper;

    public PrEmpSalaryTaxItemsResource(PrEmpSalaryTaxItemsRepository prEmpSalaryTaxItemsRepository, PrEmpSalaryTaxItemsMapper prEmpSalaryTaxItemsMapper) {
        this.prEmpSalaryTaxItemsRepository = prEmpSalaryTaxItemsRepository;
        this.prEmpSalaryTaxItemsMapper = prEmpSalaryTaxItemsMapper;
    }

    /**
     * POST  /pr-emp-salary-tax-items : Create a new prEmpSalaryTaxItems.
     *
     * @param prEmpSalaryTaxItemsDTO the prEmpSalaryTaxItemsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prEmpSalaryTaxItemsDTO, or with status 400 (Bad Request) if the prEmpSalaryTaxItems has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pr-emp-salary-tax-items")
    @Timed
    public ResponseEntity<PrEmpSalaryTaxItemsDTO> createPrEmpSalaryTaxItems(@RequestBody PrEmpSalaryTaxItemsDTO prEmpSalaryTaxItemsDTO) throws URISyntaxException {
        log.debug("REST request to save PrEmpSalaryTaxItems : {}", prEmpSalaryTaxItemsDTO);
        if (prEmpSalaryTaxItemsDTO.getId() != null) {
            throw new BadRequestAlertException("A new prEmpSalaryTaxItems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrEmpSalaryTaxItems prEmpSalaryTaxItems = prEmpSalaryTaxItemsMapper.toEntity(prEmpSalaryTaxItemsDTO);
        prEmpSalaryTaxItems = prEmpSalaryTaxItemsRepository.save(prEmpSalaryTaxItems);
        PrEmpSalaryTaxItemsDTO result = prEmpSalaryTaxItemsMapper.toDto(prEmpSalaryTaxItems);
        return ResponseEntity.created(new URI("/api/pr-emp-salary-tax-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pr-emp-salary-tax-items : Updates an existing prEmpSalaryTaxItems.
     *
     * @param prEmpSalaryTaxItemsDTO the prEmpSalaryTaxItemsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prEmpSalaryTaxItemsDTO,
     * or with status 400 (Bad Request) if the prEmpSalaryTaxItemsDTO is not valid,
     * or with status 500 (Internal Server Error) if the prEmpSalaryTaxItemsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pr-emp-salary-tax-items")
    @Timed
    public ResponseEntity<PrEmpSalaryTaxItemsDTO> updatePrEmpSalaryTaxItems(@RequestBody PrEmpSalaryTaxItemsDTO prEmpSalaryTaxItemsDTO) throws URISyntaxException {
        log.debug("REST request to update PrEmpSalaryTaxItems : {}", prEmpSalaryTaxItemsDTO);
        if (prEmpSalaryTaxItemsDTO.getId() == null) {
            return createPrEmpSalaryTaxItems(prEmpSalaryTaxItemsDTO);
        }
        PrEmpSalaryTaxItems prEmpSalaryTaxItems = prEmpSalaryTaxItemsMapper.toEntity(prEmpSalaryTaxItemsDTO);
        prEmpSalaryTaxItems = prEmpSalaryTaxItemsRepository.save(prEmpSalaryTaxItems);
        PrEmpSalaryTaxItemsDTO result = prEmpSalaryTaxItemsMapper.toDto(prEmpSalaryTaxItems);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prEmpSalaryTaxItemsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pr-emp-salary-tax-items : get all the prEmpSalaryTaxItems.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prEmpSalaryTaxItems in body
     */
    @GetMapping("/pr-emp-salary-tax-items")
    @Timed
    public ResponseEntity<List<PrEmpSalaryTaxItemsDTO>> getAllPrEmpSalaryTaxItems(Pageable pageable) {
        log.debug("REST request to get a page of PrEmpSalaryTaxItems");
        Page<PrEmpSalaryTaxItems> page = prEmpSalaryTaxItemsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-emp-salary-tax-items");
        return new ResponseEntity<>(prEmpSalaryTaxItemsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET   /pr-emp-salary-tax-items : get all the prEmpSalaryItems. by emp salary
     *
     * @param id the id of emp salary
     * @return the ResponseEntity with status 200 (OK) and the list of prEmpSalaryItems in body
     */
    @GetMapping("/pr-emp-salary-tax-items/salary/{id}")
    @Timed
    public ResponseEntity<List<PrEmpSalaryTaxItemsDTO>> getSalaryItemsBySalaryId(@PathVariable Integer id) {
        log.debug("REST request to get a page of PrEmpSalaryTaxItems");
        List<PrEmpSalaryTaxItems> items = prEmpSalaryTaxItemsRepository.findByEmployeeSalaryId(id.longValue());
        List<PrEmpSalaryTaxItemsDTO> dtoitems = new ArrayList<PrEmpSalaryTaxItemsDTO>();
        for (PrEmpSalaryTaxItems item : items) {
            dtoitems.add(prEmpSalaryTaxItemsMapper.toDto(item));
        }
        return new ResponseEntity<>(dtoitems, null, HttpStatus.OK);
    }

    /**
     * GET  /pr-emp-salary-tax-items/:id : get the "id" prEmpSalaryTaxItems.
     *
     * @param id the id of the prEmpSalaryTaxItemsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prEmpSalaryTaxItemsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pr-emp-salary-tax-items/{id}")
    @Timed
    public ResponseEntity<PrEmpSalaryTaxItemsDTO> getPrEmpSalaryTaxItems(@PathVariable Long id) {
        log.debug("REST request to get PrEmpSalaryTaxItems : {}", id);
        PrEmpSalaryTaxItems prEmpSalaryTaxItems = prEmpSalaryTaxItemsRepository.findOne(id);
        PrEmpSalaryTaxItemsDTO prEmpSalaryTaxItemsDTO = prEmpSalaryTaxItemsMapper.toDto(prEmpSalaryTaxItems);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prEmpSalaryTaxItemsDTO));
    }

    /**
     * DELETE  /pr-emp-salary-tax-items/:id : delete the "id" prEmpSalaryTaxItems.
     *
     * @param id the id of the prEmpSalaryTaxItemsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pr-emp-salary-tax-items/{id}")
    @Timed
    public ResponseEntity<Void> deletePrEmpSalaryTaxItems(@PathVariable Long id) {
        log.debug("REST request to delete PrEmpSalaryTaxItems : {}", id);
        prEmpSalaryTaxItemsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
