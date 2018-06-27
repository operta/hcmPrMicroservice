package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.EmEmpSalariesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EmEmpSalaries and its DTO EmEmpSalariesDTO.
 */
@Mapper(componentModel = "spring", uses = {EmEmployeesMapper.class, EmContractTypesMapper.class})
public interface EmEmpSalariesMapper extends EntityMapper<EmEmpSalariesDTO, EmEmpSalaries> {

    @Mapping(source = "idEmployee.id", target = "idEmployeeId")
    @Mapping(source = "idContractType.id", target = "idContractTypeId")
    @Mapping(source = "idContractType.name", target = "idContractTypeName")
    EmEmpSalariesDTO toDto(EmEmpSalaries emEmpSalaries);

    @Mapping(source = "idEmployeeId", target = "idEmployee")
    @Mapping(source = "idContractTypeId", target = "idContractType")
    EmEmpSalaries toEntity(EmEmpSalariesDTO emEmpSalariesDTO);

    default EmEmpSalaries fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmEmpSalaries emEmpSalaries = new EmEmpSalaries();
        emEmpSalaries.setId(id);
        return emEmpSalaries;
    }
}
