package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.bl.interfaces.ComponentService;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductFamilyService;
import be.tsapasmi.factorymanagement.domain.entities.Order;
import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import be.tsapasmi.factorymanagement.web.models.dto.OrderDTO;
import be.tsapasmi.factorymanagement.web.models.form.OrderForm;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {ProductFamilyService.class, ComponentService.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class OrderMapper {

    public abstract OrderDTO toDTO(ProductVariant productVariant);

    public abstract List<OrderDTO> toDTO(List<ProductVariant> productVariant);

    public abstract Order toEntity(OrderForm form);
}
