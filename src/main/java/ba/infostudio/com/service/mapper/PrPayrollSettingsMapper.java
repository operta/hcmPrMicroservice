package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrPayrollSettingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrPayrollSettings and its DTO PrPayrollSettingsDTO.
 */
@Mapper(componentModel = "spring", uses = {PrSalaryTypesMapper.class})
public interface PrPayrollSettingsMapper extends EntityMapper<PrPayrollSettingsDTO, PrPayrollSettings> {

    @Mapping(source = "salaryType.id", target = "salaryTypeId")
    @Mapping(source = "salaryType.name", target = "salaryTypeName")
    PrPayrollSettingsDTO toDto(PrPayrollSettings prPayrollSettings);

    @Mapping(source = "salaryTypeId", target = "salaryType")
    PrPayrollSettings toEntity(PrPayrollSettingsDTO prPayrollSettingsDTO);

    default PrPayrollSettings fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrPayrollSettings prPayrollSettings = new PrPayrollSettings();
        prPayrollSettings.setId(id);
        return prPayrollSettings;
    }
}
