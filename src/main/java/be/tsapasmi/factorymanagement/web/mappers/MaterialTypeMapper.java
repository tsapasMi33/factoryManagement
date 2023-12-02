package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.MaterialType;
import be.tsapasmi.factorymanagement.web.models.dtos.MaterialTypeDto;
import be.tsapasmi.factorymanagement.web.models.forms.MaterialTypeForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MaterialTypeMapper {

    @Named("toDto")
    MaterialTypeDto toDto(MaterialType materialType);

    @IterableMapping(qualifiedByName = "toDto")
    List<MaterialTypeDto> toDto(List<MaterialType> materialTypes);

    MaterialType toEntity(MaterialTypeForm materialTypeForm);

}
