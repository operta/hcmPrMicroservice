package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrTaxLevelPaymentsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrTaxLevelPayments and its DTO PrTaxLevelPaymentsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PrTaxLevelPaymentsMapper extends EntityMapper<PrTaxLevelPaymentsDTO, PrTaxLevelPayments> {



    default PrTaxLevelPayments fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrTaxLevelPayments prTaxLevelPayments = new PrTaxLevelPayments();
        prTaxLevelPayments.setId(id);
        return prTaxLevelPayments;
    }
}
