package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.OrderService;
import be.tsapasmi.factorymanagement.dal.ComponentRepository;
import be.tsapasmi.factorymanagement.dal.OrderRepository;
import be.tsapasmi.factorymanagement.domain.entities.Component;
import be.tsapasmi.factorymanagement.domain.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@AllArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<Order,Long, OrderRepository> implements OrderService {

    private final OrderRepository repository;

    @Override
    public Class<Order> getResourceClass() {
        return Order.class;
    }
}
