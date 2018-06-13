package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrSuspensionsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrSuspensions and its DTO PrSuspensionsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PrSuspensionsMapper extends EntityMapper<PrSuspensionsDTO, PrSuspensions> {



    default PrSuspensions fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrSuspensions prSuspensions = new PrSuspensions();
        prSuspensions.setId(id);
        return prSuspensions;
    }
}
