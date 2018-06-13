package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrEmpSalariesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrEmpSalaries and its DTO PrEmpSalariesDTO.
 */
@Mapper(componentModel = "spring", uses = {PrPayrollSettingsMapper.class, PrSalaryTypesMapper.class})
public interface PrEmpSalariesMapper extends EntityMapper<PrEmpSalariesDTO, PrEmpSalaries> {

    @Mapping(source = "payrollSettings.id", target = "payrollSettingsId")
    @Mapping(source = "salaryType.id", target = "salaryTypeId")
    @Mapping(source = "salaryType.name", target = "salaryTypeName")
    PrEmpSalariesDTO toDto(PrEmpSalaries prEmpSalaries);

    @Mapping(source = "payrollSettingsId", target = "payrollSettings")
    @Mapping(source = "salaryTypeId", target = "salaryType")
    PrEmpSalaries toEntity(PrEmpSalariesDTO prEmpSalariesDTO);

    default PrEmpSalaries fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrEmpSalaries prEmpSalaries = new PrEmpSalaries();
        prEmpSalaries.setId(id);
        return prEmpSalaries;
    }
}
