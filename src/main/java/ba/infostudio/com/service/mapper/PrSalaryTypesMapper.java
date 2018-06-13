package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrSalaryTypesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrSalaryTypes and its DTO PrSalaryTypesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PrSalaryTypesMapper extends EntityMapper<PrSalaryTypesDTO, PrSalaryTypes> {



    default PrSalaryTypes fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrSalaryTypes prSalaryTypes = new PrSalaryTypes();
        prSalaryTypes.setId(id);
        return prSalaryTypes;
    }
}
