package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrTaxLinkDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrTaxLink and its DTO PrTaxLinkDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PrTaxLinkMapper extends EntityMapper<PrTaxLinkDTO, PrTaxLink> {



    default PrTaxLink fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrTaxLink prTaxLink = new PrTaxLink();
        prTaxLink.setId(id);
        return prTaxLink;
    }
}
