package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.ProductStateService;
import be.tsapasmi.factorymanagement.dal.ComponentRepository;
import be.tsapasmi.factorymanagement.dal.ProductStateRepository;
import be.tsapasmi.factorymanagement.domain.entities.Component;
import be.tsapasmi.factorymanagement.domain.entities.ProductState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@AllArgsConstructor
public class ProductStateServiceImpl extends BaseServiceImpl<ProductState,Long, ProductStateRepository> implements ProductStateService {

    private final ProductStateRepository repository;

    @Override
    public Class<ProductState> getResourceClass() {
        return ProductState.class;
    }
}
