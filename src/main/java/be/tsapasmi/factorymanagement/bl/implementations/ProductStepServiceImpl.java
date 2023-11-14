package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.ProductService;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductStepService;
import be.tsapasmi.factorymanagement.dal.ProductStepRepository;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class ProductStepServiceImpl extends BaseServiceImpl<ProductStep,Long, ProductStepRepository> implements ProductStepService {

    public ProductStepServiceImpl(ProductStepRepository repository) {
        super(repository, ProductStep.class);
    }


    @Override
    public List<ProductStep> getAllStepsForProduct(long id) {
        return repository.findAllByProduct(id);
    }

    @Override
    public ProductStep encodeProduct(Product product) {
        ProductStep step = new ProductStep();
        step.setProduct(product);
        step.setStep(product.getCurrentStep());
        step.setStart(LocalDateTime.now());
        step.setFinish(LocalDateTime.now());
        step.setDuration(Duration.ofSeconds(0));
        step.setFinished(true);
        step.setPaused(false);
        return repository.save(step);
    }

    @Override
    public ProductStep putInProduction(Product product) {
        ProductStep step = new ProductStep();
        step.setProduct(product);

        int currentStepIndex = product.getVariant().getProductionPath().indexOf(product.getCurrentStep());
        Step nextStep = product.getVariant().getProductionPath().get(currentStepIndex + 1);
        product.setCurrentStep(nextStep);

        step.setStep(nextStep);
        step.setStart(LocalDateTime.now());
        step.setFinish(LocalDateTime.now());
        step.setDuration(Duration.ofSeconds(0));
        step.setFinished(true);
        step.setPaused(false);

        return repository.save(step);
    }
}
