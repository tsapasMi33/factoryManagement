package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.entities.Batch;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.enums.Step;

import java.util.List;

public interface ProductService extends BaseService<Product,Long> {
    List<Product> findAllByCriteria(Step currentStep, Step nextStep, Long packetId, Long productFamilyId);


    Product startStep(Step targetStep, Long productId);

    Product startStep(Step targetStep, Product product);

    void pauseStep(Step targetStep, Long productId);

    void pauseStep(Step targetStep, Product product, int batchSize);

    void finishStep(Step targetStep, Long productId);

    void finishStep(Step targetStep, Product product, int batchSize);
}
