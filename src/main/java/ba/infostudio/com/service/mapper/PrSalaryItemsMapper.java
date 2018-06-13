package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrSalaryItemsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrSalaryItems and its DTO PrSalaryItemsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PrSalaryItemsMapper extends EntityMapper<PrSalaryItemsDTO, PrSalaryItems> {



    default PrSalaryItems fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrSalaryItems prSalaryItems = new PrSalaryItems();
        prSalaryItems.setId(id);
        return prSalaryItems;
    }
}
