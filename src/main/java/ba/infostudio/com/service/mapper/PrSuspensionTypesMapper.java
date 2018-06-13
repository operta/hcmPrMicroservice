package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrSuspensionTypesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrSuspensionTypes and its DTO PrSuspensionTypesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PrSuspensionTypesMapper extends EntityMapper<PrSuspensionTypesDTO, PrSuspensionTypes> {



    default PrSuspensionTypes fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrSuspensionTypes prSuspensionTypes = new PrSuspensionTypes();
        prSuspensionTypes.setId(id);
        return prSuspensionTypes;
    }
}
