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

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderDto orderDto, @MappingTarget Order order);

    @IterableMapping(qualifiedByName = "toDto")
    List<OrderDto> toDto(List<Order> orders);

    Order toEntity(OrderForm orderForm);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderForm orderForm, @MappingTarget Order order);
}
