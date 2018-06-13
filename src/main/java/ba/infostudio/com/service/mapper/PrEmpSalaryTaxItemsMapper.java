package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrEmpSalaryTaxItemsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrEmpSalaryTaxItems and its DTO PrEmpSalaryTaxItemsDTO.
 */
@Mapper(componentModel = "spring", uses = {PrEmpSalariesMapper.class, PrTaxesMapper.class, PrTaxLinkMapper.class})
public interface PrEmpSalaryTaxItemsMapper extends EntityMapper<PrEmpSalaryTaxItemsDTO, PrEmpSalaryTaxItems> {

    @Mapping(source = "employeeSalary.id", target = "employeeSalaryId")
    @Mapping(source = "tax.id", target = "taxId")
    @Mapping(source = "tax.name", target = "taxName")
    @Mapping(source = "taxLink.id", target = "taxLinkId")
    @Mapping(source = "taxLink.name", target = "taxLinkName")
    PrEmpSalaryTaxItemsDTO toDto(PrEmpSalaryTaxItems prEmpSalaryTaxItems);

    @Mapping(source = "employeeSalaryId", target = "employeeSalary")
    @Mapping(source = "taxId", target = "tax")
    @Mapping(source = "taxLinkId", target = "taxLink")
    PrEmpSalaryTaxItems toEntity(PrEmpSalaryTaxItemsDTO prEmpSalaryTaxItemsDTO);

    default PrEmpSalaryTaxItems fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrEmpSalaryTaxItems prEmpSalaryTaxItems = new PrEmpSalaryTaxItems();
        prEmpSalaryTaxItems.setId(id);
        return prEmpSalaryTaxItems;
    }
}
