package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.exceptions.ResourceNotFoundException;
import be.tsapasmi.factorymanagement.bl.exceptions.SingleProductInBatchStepException;
import be.tsapasmi.factorymanagement.bl.interfaces.*;
import be.tsapasmi.factorymanagement.dal.ProductRepository;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Getter
public class ProductServiceImpl extends BaseServiceImpl<Product, Long, ProductRepository> implements ProductService {

    private final ProductFamilyService productFamilyService;
    private final ProductStepService productStepService;
    private final ProductVariantService productVariantService;

    public ProductServiceImpl(ProductRepository repo,
                              ProductFamilyService productFamilyService,
                              ProductStepService productStepService, ProductVariantService productVariantService) {
        super(repo, Product.class);
        this.productFamilyService = productFamilyService;
        this.productStepService = productStepService;
        this.productVariantService = productVariantService;
    }


    public List<Product> findAllByCriteria(Step currentStep, Step nextStep, Long productFamilyId) {
        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (currentStep != null) {
                predicates.add(criteriaBuilder.equal(root.get("currentStep"), currentStep));
            }
            if (nextStep != null) {
                predicates.add(criteriaBuilder.equal(root.get("nextStep"), nextStep));
            }
            if (productFamilyId != null) {
                predicates.add(criteriaBuilder.equal(root.get("variant").get("productFamily"), productFamilyService.getOne(productFamilyId)));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return repository.findAll(specification);
    }

    @Override
    public Product startStep(Step targetStep, Long productId) {

        if (targetStep.isBatchStep()) {
            throw new SingleProductInBatchStepException();
        }

        Product product = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(productId, Product.class));

        return startStep(targetStep, product);
    }

    @Override
    public Product startStep(Step targetStep, Product product) {

        product.getSteps().add(productStepService.startStep(targetStep, product));

        if (!targetStep.isMeasurable()) {
            doNextStep(product);
        }

        return repository.save(product);
    }

    @Override
    public void pauseStep(Step targetStep, Long productId) {

        if (targetStep != Step.FINISHED) {
            throw new SingleProductInBatchStepException();
        }

        Product product = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(productId, Product.class));

        pauseStep(targetStep, product, 1);
    }

    @Override
    public void pauseStep(Step targetStep, Product product, int batchSize) {

        product.getSteps().add(product.getSteps().size() - 1,productStepService.pauseStep(targetStep, product, batchSize));
        repository.save(product);
    }

    @Override
    public void finishStep(Step targetStep, Long productId) {

        if (targetStep != Step.FINISHED) {
            throw new SingleProductInBatchStepException();
        }

        Product product = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(productId, Product.class));

        finishStep(targetStep, product, 1);
    }

    @Override
    public void finishStep(Step targetStep, Product product, int batchSize) {

        product.getSteps().add(product.getSteps().size() - 1,productStepService.finishStep(targetStep, product, batchSize));
        doNextStep(product);
        repository.save(product);
    }

    private void doNextStep(Product product) {
        product.setCurrentStep(product.getNextStep());
        int nextStepIdx = product.getVariant().getProductFamily().getProductionPath().indexOf(product.getNextStep()) + 1;
        if (nextStepIdx < product.getVariant().getProductFamily().getProductionPath().size()) {
            product.setNextStep(product.getVariant().getProductFamily().getProductionPath().get(nextStepIdx));
        } else {
            product.setNextStep(null);
        }
    }

    @Override
    public Product create(Product entity) {
        entity.setVariant(productVariantService.getOne(entity.getVariant().getId()));
        startStep(Step.ENCODED, entity);
        return super.create(entity);
    }
}
