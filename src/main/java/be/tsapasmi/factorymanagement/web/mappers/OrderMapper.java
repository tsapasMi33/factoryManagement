package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.Order;
import be.tsapasmi.factorymanagement.web.models.dtos.OrderDto;
import be.tsapasmi.factorymanagement.web.models.forms.OrderForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClientMapper.class})
public interface OrderMapper {

    @Named("toDto")
    OrderDto toDto(Order order);

    @IterableMapping(qualifiedByName = "toDto")
    List<OrderDto> toDto(List<Order> orders);

    Order toEntity(OrderForm orderForm);
}
