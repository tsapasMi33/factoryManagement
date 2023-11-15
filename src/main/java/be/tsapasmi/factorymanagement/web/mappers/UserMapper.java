package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.User;
import be.tsapasmi.factorymanagement.web.models.form.UserCreationForm;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User toEntity(UserCreationForm userCreationForm);
}
