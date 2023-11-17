package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.User;
import be.tsapasmi.factorymanagement.web.models.dtos.UserDto;
import be.tsapasmi.factorymanagement.web.models.forms.UserForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Named("toDto")
    UserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto userDto, @MappingTarget User user);

    @IterableMapping(qualifiedByName = "toDto")
    List<UserDto> toDto(List<User> users);


    User toEntity(UserForm userForm);
}
