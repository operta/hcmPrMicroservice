package ba.infostudio.com.web.rest;

import ba.infostudio.com.domain.Action;
import ba.infostudio.com.repository.PrEmpSalariesRepository;
import ba.infostudio.com.repository.PrEmpSalarySettingsRepository;
import ba.infostudio.com.security.SecurityUtils;
import ba.infostudio.com.service.PrPayrollSettingsService;
import ba.infostudio.com.service.RestResponse;
import ba.infostudio.com.service.UserPayrollComposition;
import ba.infostudio.com.web.rest.util.AuditUtil;
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
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;

/**
 * REST controller for managing PrPayrollSettings.
 */
@RestController
@RequestMapping("/api")
public class PrPayrollSettingsResource {

    private final Logger log = LoggerFactory.getLogger(PrPayrollSettingsResource.class);

    private static final String ENTITY_NAME = "prPayrollSettings";

    private final PrPayrollSettingsRepository prPayrollSettingsRepository;

    private final PrEmpSalariesRepository prEmpSalariesRepository;

    private final PrEmpSalarySettingsRepository prEmpSalarySettingsRepository;

    private final PrPayrollSettingsMapper prPayrollSettingsMapper;

    private final PrPayrollSettingsService prPayrollSettingsService;

    private final ApplicationEventPublisher applicationEventPublisher;

    public PrPayrollSettingsResource(PrPayrollSettingsRepository prPayrollSettingsRepository,
                                     PrEmpSalarySettingsRepository prEmpSalarySettingsRepository,
                                     PrEmpSalariesRepository prEmpSalariesRepository,
                                     PrPayrollSettingsMapper prPayrollSettingsMapper,
                                     PrPayrollSettingsService prPayrollSettingsService,
                                     ApplicationEventPublisher applicationEventPublisher) {
        this.prPayrollSettingsRepository = prPayrollSettingsRepository;
        this.prPayrollSettingsMapper = prPayrollSettingsMapper;
        this.prEmpSalariesRepository = prEmpSalariesRepository;
        this.prEmpSalarySettingsRepository = prEmpSalarySettingsRepository;
        this.prPayrollSettingsService = prPayrollSettingsService;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public enum GenerationType{
        OBRACUN_PLATA,
        PRIJEDLOG_PLATA,
        OBRISI_OBRACUN_PLATA
    }

    /**
     *
     * @param month
     * @param year
     * @return number of working days in that month
     */
    private Integer getNumOfWorkingDays(Integer month, Integer year){
        LocalDate startDate = LocalDate.of(year, month, 1);

        int workDays = 0;
        int nextMonth = startDate.getMonthValue() + 1;
        if(startDate.getMonthValue() == 12) {
            nextMonth = 1;
        }
        while(startDate.getMonthValue() != nextMonth){
            if(!startDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) &&
                !startDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                workDays++;
            }
            startDate = startDate.plusDays(1);
        }

        return workDays;
    }

    private Integer getPrSequence(Integer month, Integer year){
        List<PrPayrollSettings> prSettingsInYearAndMonth = prPayrollSettingsRepository.findByMonthAndYear(month,
            year);

        return prSettingsInYearAndMonth.size() + 1;
    }

    /**
     * automatically generates number of working days, hours, and calculation number, if needed
     */
    private void generatePayrollSetings(PrPayrollSettingsDTO prPayrollSettingsDTO){
        final Integer payrollYear = prPayrollSettingsDTO.getYear();
        final Integer payrollMonth = prPayrollSettingsDTO.getMonth();
        final Long payrollSalaryTypeId = prPayrollSettingsDTO.getSalaryTypeId();
        final Integer prSequence = getPrSequence(payrollMonth, payrollYear);

        final String calculationNumber = payrollYear.toString() + '.' +
            payrollMonth.toString() + '.' +
            payrollSalaryTypeId.toString() + '.' +
            prSequence.toString();

        prPayrollSettingsDTO.setCalculationNumber(calculationNumber);


        final Integer numOfWorkingDays = getNumOfWorkingDays(payrollMonth, payrollYear);

        prPayrollSettingsDTO.setNumberOfWorkingDays(numOfWorkingDays);
        prPayrollSettingsDTO.setNumberOfWorkingHours(numOfWorkingDays * 8);
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
        // automatically generates number of working days, hours and calculation number if needed
        generatePayrollSetings(prPayrollSettingsDTO);

        PrPayrollSettings prPayrollSettings = prPayrollSettingsMapper.toEntity(prPayrollSettingsDTO);
        prPayrollSettings = prPayrollSettingsRepository.save(prPayrollSettings);
        PrPayrollSettingsDTO result = prPayrollSettingsMapper.toDto(prPayrollSettings);
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                "payroll",
                ENTITY_NAME,
                result.getId().toString(),
                Action.POST
            )
        );
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

        // automatically generates number of working days, hours and calculation number if needed
        generatePayrollSetings(prPayrollSettingsDTO);

        PrPayrollSettings prPayrollSettings = prPayrollSettingsMapper.toEntity(prPayrollSettingsDTO);
        prPayrollSettings = prPayrollSettingsRepository.save(prPayrollSettings);
        PrPayrollSettingsDTO result = prPayrollSettingsMapper.toDto(prPayrollSettings);
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                "payroll",
                ENTITY_NAME,
                result.getId().toString(),
                Action.PUT
            )
        );
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
    public ResponseEntity<List<PrPayrollSettingsDTO>> getAllPrPayrollSettings(
        Pageable pageable,
        @RequestParam(value = "month", required = false) Integer month,
        @RequestParam(value = "year", required = false) Integer year,
        @RequestParam(value = "salaryTypeId", required = false) Integer salaryTypeId
        ) {
        log.debug("REST request to get a page of PrPayrollSettings");

        Set<Integer> ids = new HashSet<Integer>(Arrays.asList(extractIds(prPayrollSettingsRepository.findAll())));

        if(month != null && year != null) {
            List<PrPayrollSettings> list = prPayrollSettingsRepository.findByMonthAndYear(month, year);
            Set<Integer> s = new HashSet<Integer>(Arrays.asList(extractIds(list)));
            ids.retainAll(s);
        }
        if(salaryTypeId != null) {
            List<PrPayrollSettings> list = prPayrollSettingsRepository.findBySalaryTypeId(salaryTypeId.longValue());
            Set<Integer> s = new HashSet<Integer>(Arrays.asList(extractIds(list)));
            ids.retainAll(s);
        }

        Integer[] result = ids.toArray(new Integer[ids.size()]);


        List<PrPayrollSettings> payrolls = new ArrayList<PrPayrollSettings>();
        for(int i = 0; i < result.length; i++) {
            PrPayrollSettings item = prPayrollSettingsRepository.findOne(result[i].longValue());
            payrolls.add(item);
        }

        Page<PrPayrollSettings> page = new PageImpl<PrPayrollSettings>(payrolls, pageable, payrolls.size());

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pr-payroll-settings");
        return new ResponseEntity<>(prPayrollSettingsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }


    public static Integer[] extractIds(List<PrPayrollSettings> array) {
        if(array.size() == 0) {
            return new Integer[0];
        }
        Integer[] result = new Integer[array.size()];
        for(int i = 0; i < array.size(); i++) {
            result[i] = (array.get(i).getId().intValue());
        }
        return result;
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


    @PostMapping("/pr-payroll-settings/delete-payrolls")
    @Timed
    public ResponseEntity<RestResponse> deletePayrolls(@RequestBody UserPayrollComposition userPayrollComposition){
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent("Obračun plata obrisan")
        );
        return generatePayrollUtil(userPayrollComposition, GenerationType.OBRISI_OBRACUN_PLATA,
            "delete-payrolls", "payrollsDeleted");
    }




    @PostMapping("/pr-payroll-settings/generate-payrolls")
    @Timed
    public ResponseEntity<RestResponse> generatePayrolls(@RequestBody UserPayrollComposition userPayrollComposition){
        log.debug("PAYROLL {}", userPayrollComposition);
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent("Obračun plata generisan")
        );
        return generatePayrollUtil(userPayrollComposition, GenerationType.OBRACUN_PLATA,
            "generate-payrolls", "payrollsNotGenerated");
    }


    @PostMapping("/pr-payroll-settings/generate-payroll-suggestion")
    @Timed
    public ResponseEntity<RestResponse> generatePayrollSuggestion(@RequestBody UserPayrollComposition userPayrollComposition){
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent("Prijedlog obračuna plata generisan")
        );
        return generatePayrollUtil(userPayrollComposition, GenerationType.PRIJEDLOG_PLATA,
            "generate-payroll-suggestion", "payrollSuggestionNotGenerated");
    }

    private ResponseEntity<RestResponse> generatePayrollUtil(UserPayrollComposition userPayrollComposition,
                                                             GenerationType generationType,
                                                             String generationTypeName,
                                                             String error){
        if(userPayrollComposition.getUserId() == null){
            throw new BadRequestAlertException("You must provide a user id", ENTITY_NAME, error);
        }
        if(userPayrollComposition.getPayrollSettings() == null){
            throw new BadRequestAlertException("You must provide the payroll settings", ENTITY_NAME,
                error);
        }
        log.debug("Request for " + generationType.name());

        Integer year = userPayrollComposition.getPayrollSettings().getYear();
        Integer month = userPayrollComposition.getPayrollSettings().getMonth();
        String calculationNumber = userPayrollComposition.getPayrollSettings().getCalculationNumber();
        String userId = userPayrollComposition.getUserId();
        Long salaryTypeId = userPayrollComposition.getPayrollSettings().getSalaryTypeId();

        String output = "";

        if(generationType.equals(GenerationType.OBRACUN_PLATA)) {
            output = prPayrollSettingsService.obracunPlata(year, month, salaryTypeId, calculationNumber, userId);
        }else if(generationType.equals(GenerationType.PRIJEDLOG_PLATA)){
            output = prPayrollSettingsService.prijedlogPlata(year, month, salaryTypeId, calculationNumber, userId);
        } else {
            output = prPayrollSettingsService.obrisiPlate(year, month, salaryTypeId, calculationNumber, userId);
        }
        if(!output.equals("D") && (generationType.equals(GenerationType.OBRACUN_PLATA) || generationType.equals(GenerationType.PRIJEDLOG_PLATA))){
            throw new BadRequestAlertException("There was an error while generating payrolls", ENTITY_NAME,
                error);
        }

        log.debug("OUTPUT FROM PROCEDURE {}", output);

        return ResponseEntity.created(URI.create("/api/" + generationTypeName + "/"))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, output))
            .body(new RestResponse("You've successfully generated " + generationType.name(),"success"));

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
        prEmpSalariesRepository.deleteAllByPayrollSettingsId(id);
        prEmpSalarySettingsRepository.deleteAllByPayrollSettingsId(id);
        prPayrollSettingsRepository.delete(id);
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                "payroll",
                ENTITY_NAME,
                id.toString(),
                Action.DELETE
            )
        );
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
