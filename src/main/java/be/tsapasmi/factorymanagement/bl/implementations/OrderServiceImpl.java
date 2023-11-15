package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.OrderService;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductService;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductStepService;
import be.tsapasmi.factorymanagement.dal.ComponentRepository;
import be.tsapasmi.factorymanagement.dal.OrderRepository;
import be.tsapasmi.factorymanagement.domain.entities.Component;
import be.tsapasmi.factorymanagement.domain.entities.Order;
import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.weaver.ast.Or;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class OrderServiceImpl extends BaseServiceImpl<Order,Long, OrderRepository> implements OrderService {

    public OrderServiceImpl(OrderRepository repo, ProductService productService) {
        super(repo, Order.class);
        this.productService = productService;
    }

    private final ProductService productService;

    @Override
    public Class<Order> getResourceClass() {
        return Order.class;
    }

    @Override
    public Order create(Order entity) {
        Order created = super.create(entity);
        entity.getProducts()
                .forEach(product -> productService.startStep(Step.ENCODED, product));
        return created;
    }
}
