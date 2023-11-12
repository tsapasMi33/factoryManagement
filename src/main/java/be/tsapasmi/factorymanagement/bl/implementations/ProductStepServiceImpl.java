package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.ProductService;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductStepService;
import be.tsapasmi.factorymanagement.dal.ProductStepRepository;
import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@AllArgsConstructor
public class ProductStepServiceImpl extends BaseServiceImpl<ProductStep,Long, ProductStepRepository> implements ProductStepService {

    private final ProductStepRepository repository;

    @Override
    public Class<ProductStep> getResourceClass() {
        return ProductStep.class;
    }

    @Override
    public List<ProductStep> getAllStepsForProduct(long id) {
        return repository.findAllByProduct(id);
    }
}
