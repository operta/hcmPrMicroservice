package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrEmpSalarySuspItemsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrEmpSalarySuspItems and its DTO PrEmpSalarySuspItemsDTO.
 */
@Mapper(componentModel = "spring", uses = {PrEmpSalariesMapper.class, PrEmployeeSuspensionsMapper.class})
public interface PrEmpSalarySuspItemsMapper extends EntityMapper<PrEmpSalarySuspItemsDTO, PrEmpSalarySuspItems> {

    @Mapping(source = "employeeSalary.id", target = "employeeSalaryId")
    @Mapping(source = "empSuspension.id", target = "empSuspensionId")
    PrEmpSalarySuspItemsDTO toDto(PrEmpSalarySuspItems prEmpSalarySuspItems);

    @Mapping(source = "employeeSalaryId", target = "employeeSalary")
    @Mapping(source = "empSuspensionId", target = "empSuspension")
    PrEmpSalarySuspItems toEntity(PrEmpSalarySuspItemsDTO prEmpSalarySuspItemsDTO);

    default PrEmpSalarySuspItems fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrEmpSalarySuspItems prEmpSalarySuspItems = new PrEmpSalarySuspItems();
        prEmpSalarySuspItems.setId(id);
        return prEmpSalarySuspItems;
    }
}
