package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrTaxBaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrTaxBase and its DTO PrTaxBaseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PrTaxBaseMapper extends EntityMapper<PrTaxBaseDTO, PrTaxBase> {



    default PrTaxBase fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrTaxBase prTaxBase = new PrTaxBase();
        prTaxBase.setId(id);
        return prTaxBase;
    }
}
