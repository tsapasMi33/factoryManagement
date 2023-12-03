package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.Component;
import be.tsapasmi.factorymanagement.web.models.dtos.ComponentDto;
import be.tsapasmi.factorymanagement.web.models.forms.ComponentForm;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ComponentMapper {

    ComponentDto toDto(Component component);


    Component toEntity(ComponentForm componentForm);
}
