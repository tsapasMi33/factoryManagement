package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface ProductService extends BaseService<Product,Long> {
    Page<Product> findAllByCriteria(int page, Step currentStep, Step nextStep, Long productFamilyId, Long batchId, Long clientId, LocalDate deliveryDate, LocalDate orderDate, Long packetId, String productVariantCode);


    Product startStep(Step targetStep, Long productId);

    Product startStep(Step targetStep, Product product);

    Product pauseStep(Step targetStep, Long productId);

    Product pauseStep(Step targetStep, Product product, int batchSize);

    Product finishStep(Step targetStep, Long productId);

    Product finishStep(Step targetStep, Product product, int batchSize);

    void archiveAll();

    Product getByCode(String orderCode, String productCode);
}
