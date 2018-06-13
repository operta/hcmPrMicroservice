package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrEmployeeSuspensionsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrEmployeeSuspensions and its DTO PrEmployeeSuspensionsDTO.
 */
@Mapper(componentModel = "spring", uses = {PrSuspensionsMapper.class, PrSuspensionTypesMapper.class})
public interface PrEmployeeSuspensionsMapper extends EntityMapper<PrEmployeeSuspensionsDTO, PrEmployeeSuspensions> {

    @Mapping(source = "suspension.id", target = "suspensionId")
    @Mapping(source = "suspension.name", target = "suspensionName")
    @Mapping(source = "suspensionType.id", target = "suspensionTypeId")
    @Mapping(source = "suspensionType.name", target = "suspensionTypeName")
    PrEmployeeSuspensionsDTO toDto(PrEmployeeSuspensions prEmployeeSuspensions);

    @Mapping(source = "suspensionId", target = "suspension")
    @Mapping(source = "suspensionTypeId", target = "suspensionType")
    PrEmployeeSuspensions toEntity(PrEmployeeSuspensionsDTO prEmployeeSuspensionsDTO);

    default PrEmployeeSuspensions fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrEmployeeSuspensions prEmployeeSuspensions = new PrEmployeeSuspensions();
        prEmployeeSuspensions.setId(id);
        return prEmployeeSuspensions;
    }
}
