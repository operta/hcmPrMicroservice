package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.EmContractTypesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EmContractTypes and its DTO EmContractTypesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmContractTypesMapper extends EntityMapper<EmContractTypesDTO, EmContractTypes> {



    default EmContractTypes fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmContractTypes emContractTypes = new EmContractTypes();
        emContractTypes.setId(id);
        return emContractTypes;
    }
}
