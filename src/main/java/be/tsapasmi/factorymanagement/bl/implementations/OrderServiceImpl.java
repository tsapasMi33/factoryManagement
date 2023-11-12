package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.OrderService;
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
@AllArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<Order,Long, OrderRepository> implements OrderService {

    private final OrderRepository repository;
    private final ProductStepService productStepService;

    @Override
    public Class<Order> getResourceClass() {
        return Order.class;
    }

    @Override
    public Order create(Order entity) {
        entity.getProducts()
                .forEach(product -> {
                    List<ProductStep> steps = new ArrayList<>(List.of(new ProductStep(product, product.getCurrentStep(), LocalDateTime.now(), LocalDateTime.now(), Duration.ofSeconds(0), true, null)));
                    product.setSteps(steps);
        });
        return super.create(entity);
    }
}
