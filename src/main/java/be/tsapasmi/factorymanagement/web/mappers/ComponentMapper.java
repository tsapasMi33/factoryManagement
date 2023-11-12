package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.Component;
import be.tsapasmi.factorymanagement.web.models.dto.ComponentDTO;
import be.tsapasmi.factorymanagement.web.models.form.ComponentForm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ComponentMapper {

    ComponentDTO toDTO(Component component);

    List<ComponentDTO> toDTO(List<Component> components);

    Component toEntity(ComponentForm form);
}
