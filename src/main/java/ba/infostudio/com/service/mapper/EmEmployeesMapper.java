package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.EmEmployeesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EmEmployees and its DTO EmEmployeesDTO.
 */
@Mapper(componentModel = "spring", uses = {EmEmpTypesMapper.class, EmStatusesMapper.class})
public interface EmEmployeesMapper extends EntityMapper<EmEmployeesDTO, EmEmployees> {

    @Mapping(source = "idEmploymentType.id", target = "idEmploymentTypeId")
    @Mapping(source = "idEmploymentType.name", target = "idEmploymentTypeName")
    @Mapping(source = "idStatus.id", target = "idStatusId")
    @Mapping(source = "idStatus.name", target = "idStatusName")
    EmEmployeesDTO toDto(EmEmployees emEmployees);

    @Mapping(source = "idEmploymentTypeId", target = "idEmploymentType")
    @Mapping(source = "idStatusId", target = "idStatus")
    EmEmployees toEntity(EmEmployeesDTO emEmployeesDTO);

    default EmEmployees fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmEmployees emEmployees = new EmEmployees();
        emEmployees.setId(id);
        return emEmployees;
    }
}
