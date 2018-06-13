package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrEmpSalaryItemsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrEmpSalaryItems and its DTO PrEmpSalaryItemsDTO.
 */
@Mapper(componentModel = "spring", uses = {PrEmpSalariesMapper.class, PrSalaryItemsMapper.class})
public interface PrEmpSalaryItemsMapper extends EntityMapper<PrEmpSalaryItemsDTO, PrEmpSalaryItems> {

    @Mapping(source = "employeeSalary.id", target = "employeeSalaryId")
    @Mapping(source = "salaryItem.id", target = "salaryItemId")
    @Mapping(source = "salaryItem.name", target = "salaryItemName")
    PrEmpSalaryItemsDTO toDto(PrEmpSalaryItems prEmpSalaryItems);

    @Mapping(source = "employeeSalaryId", target = "employeeSalary")
    @Mapping(source = "salaryItemId", target = "salaryItem")
    PrEmpSalaryItems toEntity(PrEmpSalaryItemsDTO prEmpSalaryItemsDTO);

    default PrEmpSalaryItems fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrEmpSalaryItems prEmpSalaryItems = new PrEmpSalaryItems();
        prEmpSalaryItems.setId(id);
        return prEmpSalaryItems;
    }
}
