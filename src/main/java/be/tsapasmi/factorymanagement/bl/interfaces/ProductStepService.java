package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import be.tsapasmi.factorymanagement.domain.enums.Step;

import java.util.List;

public interface ProductStepService extends BaseService<ProductStep,Long> {

    List<ProductStep> getAllStepsForProduct(long id);

    ProductStep startStep(Step targetStep, Product product);

    ProductStep pauseStep(Step targetStep, Product product, int batchSize);

    ProductStep finishStep(Step targetStep, Product product, int batchSize);
}
