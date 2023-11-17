package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.Component;
import be.tsapasmi.factorymanagement.web.models.dtos.ComponentDto;
import be.tsapasmi.factorymanagement.web.models.forms.ComponentForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ComponentMapper {

    @Named("toDto")
    ComponentDto toDto(Component component);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Component partialUpdate(ComponentDto componentDto, @MappingTarget Component component);

    @IterableMapping(qualifiedByName = "toDto")
    List<ComponentDto> toDto(List<Component> components);

    Component toEntity(ComponentForm componentForm);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Component partialUpdate(ComponentForm componentForm, @MappingTarget Component component);
}
