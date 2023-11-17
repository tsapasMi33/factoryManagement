package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.ClientService;
import be.tsapasmi.factorymanagement.bl.interfaces.OrderService;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductService;
import be.tsapasmi.factorymanagement.dal.OrderRepository;
import be.tsapasmi.factorymanagement.domain.entities.Order;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
@Getter
public class OrderServiceImpl extends BaseServiceImpl<Order,Long, OrderRepository> implements OrderService {

    private final ClientService clientService;
    private final ProductService productService;


    public OrderServiceImpl(OrderRepository repo, ClientService clientService, ProductService productService) {
        super(repo, Order.class);
        this.clientService = clientService;
        this.productService = productService;
    }


    @Override
    public Class<Order> getResourceClass() {
        return Order.class;
    }

    @Override
    public Order create(Order entity) {

        entity.setClient(clientService.getOne(entity.getClient().getId()));

        Order created = super.create(entity);

        entity.setProducts(
                entity.getProducts().stream()
                        .map(product -> {
                            product.setOrder(created);
                            return productService.create(product);
                        })
                        .toList()
        );

        return created;
    }
}
