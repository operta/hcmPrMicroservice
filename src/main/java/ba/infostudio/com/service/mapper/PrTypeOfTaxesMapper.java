package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrTypeOfTaxesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrTypeOfTaxes and its DTO PrTypeOfTaxesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PrTypeOfTaxesMapper extends EntityMapper<PrTypeOfTaxesDTO, PrTypeOfTaxes> {



    default PrTypeOfTaxes fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrTypeOfTaxes prTypeOfTaxes = new PrTypeOfTaxes();
        prTypeOfTaxes.setId(id);
        return prTypeOfTaxes;
    }
}
