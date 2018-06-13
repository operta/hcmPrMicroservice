package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrEmpSalarySettingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrEmpSalarySettings and its DTO PrEmpSalarySettingsDTO.
 */
@Mapper(componentModel = "spring", uses = {PrSalaryTypesMapper.class, PrPayrollSettingsMapper.class, PrSalaryItemsMapper.class})
public interface PrEmpSalarySettingsMapper extends EntityMapper<PrEmpSalarySettingsDTO, PrEmpSalarySettings> {

    @Mapping(source = "salaryType.id", target = "salaryTypeId")
    @Mapping(source = "salaryType.name", target = "salaryTypeName")
    @Mapping(source = "payrollSettings.id", target = "payrollSettingsId")
    @Mapping(source = "salaryItem.id", target = "salaryItemId")
    PrEmpSalarySettingsDTO toDto(PrEmpSalarySettings prEmpSalarySettings);

    @Mapping(source = "salaryTypeId", target = "salaryType")
    @Mapping(source = "payrollSettingsId", target = "payrollSettings")
    @Mapping(source = "salaryItemId", target = "salaryItem")
    PrEmpSalarySettings toEntity(PrEmpSalarySettingsDTO prEmpSalarySettingsDTO);

    default PrEmpSalarySettings fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrEmpSalarySettings prEmpSalarySettings = new PrEmpSalarySettings();
        prEmpSalarySettings.setId(id);
        return prEmpSalarySettings;
    }
}
