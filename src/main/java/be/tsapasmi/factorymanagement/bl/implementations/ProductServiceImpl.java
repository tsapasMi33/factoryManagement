package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.ProductService;
import be.tsapasmi.factorymanagement.dal.ProductRepository;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
@Getter
@AllArgsConstructor
public class ProductServiceImpl extends BaseServiceImpl<Product,Long,ProductRepository> implements ProductService {

    private final ProductRepository repository;

    @Override
    public Class<Product> getResourceClass() {
        return Product.class;
    }
}
