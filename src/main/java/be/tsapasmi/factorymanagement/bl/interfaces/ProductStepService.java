package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.entities.ProductStep;

import java.util.List;

public interface ProductStepService extends BaseService<ProductStep,Long> {

    List<ProductStep> getAllStepsForProduct(long id);
}
