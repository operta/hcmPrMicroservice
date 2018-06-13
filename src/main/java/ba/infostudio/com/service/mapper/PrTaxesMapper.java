package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.PrTaxesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrTaxes and its DTO PrTaxesDTO.
 */
@Mapper(componentModel = "spring", uses = {PrTaxBaseMapper.class, PrTaxLevelPaymentsMapper.class, PrTypeOfTaxesMapper.class, PrTaxLinkMapper.class})
public interface PrTaxesMapper extends EntityMapper<PrTaxesDTO, PrTaxes> {

    @Mapping(source = "taxBase.id", target = "taxBaseId")
    @Mapping(source = "taxBase.name", target = "taxBaseName")
    @Mapping(source = "taxLevel.id", target = "taxLevelId")
    @Mapping(source = "taxLevel.name", target = "taxLevelName")
    @Mapping(source = "taxType.id", target = "taxTypeId")
    @Mapping(source = "taxType.name", target = "taxTypeName")
    @Mapping(source = "taxLink.id", target = "taxLinkId")
    @Mapping(source = "taxLink.name", target = "taxLinkName")
    PrTaxesDTO toDto(PrTaxes prTaxes);

    @Mapping(source = "taxBaseId", target = "taxBase")
    @Mapping(source = "taxLevelId", target = "taxLevel")
    @Mapping(source = "taxTypeId", target = "taxType")
    @Mapping(source = "taxLinkId", target = "taxLink")
    PrTaxes toEntity(PrTaxesDTO prTaxesDTO);

    default PrTaxes fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrTaxes prTaxes = new PrTaxes();
        prTaxes.setId(id);
        return prTaxes;
    }
}
