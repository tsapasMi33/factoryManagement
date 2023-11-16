package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.User;
import be.tsapasmi.factorymanagement.web.models.dto.UserDTO;
import be.tsapasmi.factorymanagement.web.models.form.UserCreationForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(uses = {ProductStepMapper.class})
public interface UserMapper {

    User toEntity(UserCreationForm userCreationForm);

    @AfterMapping
    default void linkJobs(@MappingTarget User user) {
        user.getJobs().forEach(job -> job.setCreatedBy(user));
    }

    @Named("userToUserDTO")
    UserDTO toDTO(User user);

    @IterableMapping(qualifiedByName = "userToUserDTO")
    List<UserDTO> toDTO(List<User> user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDTO userDTO, @MappingTarget User user);
}
