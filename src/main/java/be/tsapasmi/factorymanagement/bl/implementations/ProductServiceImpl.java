package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.exceptions.BadPathException;
import be.tsapasmi.factorymanagement.bl.exceptions.ResourceNotFoundException;
import be.tsapasmi.factorymanagement.bl.exceptions.SingleProductInBatchStepException;
import be.tsapasmi.factorymanagement.bl.interfaces.*;
import be.tsapasmi.factorymanagement.dal.ProductRepository;
import be.tsapasmi.factorymanagement.dal.ProductStepRepository;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Getter
public class ProductServiceImpl extends BaseServiceImpl<Product, Long, ProductRepository> implements ProductService {

    private final ProductStepService productStepService;
    private final ProductVariantService productVariantService;
    private final ProductStepRepository productStepRepository;

    public ProductServiceImpl(ProductRepository repo, ProductStepService productStepService, ProductVariantService productVariantService,
                              ProductStepRepository productStepRepository) {
        super(repo, Product.class);

        this.productStepService = productStepService;
        this.productVariantService = productVariantService;
        this.productStepRepository = productStepRepository;
    }


    public Page<Product> findAllByCriteria(int page, Step currentStep, Step nextStep, Long productFamilyId, Long batchId, Long clientId, LocalDate deliveryDate, LocalDate orderDate, Long packetId, String productVariantCode) {
        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("archived"), false));

            if (currentStep != null) {
                predicates.add(criteriaBuilder.equal(root.get("currentStep"), currentStep));
            }
            if (nextStep != null) {
                predicates.add(criteriaBuilder.equal(root.get("nextStep"), nextStep));
            }
            if (productFamilyId != null) {
                predicates.add(criteriaBuilder.equal(root.get("variant").get("productFamily").get("id"),productFamilyId));
            }
            if (batchId != null) {
                predicates.add(criteriaBuilder.equal(root.get("batch").get("id"), batchId));
            }
            if (clientId != null) {
                predicates.add(criteriaBuilder.equal(root.get("order").get("client").get("id"), clientId));
            }
            if (deliveryDate != null) {
                predicates.add(criteriaBuilder.equal(root.get("order").get("plannedDeliveryDate"), deliveryDate));
            }
            if (orderDate != null) {
                predicates.add(criteriaBuilder.equal(root.get("order").get("createdDate"), orderDate));
            }
            if (packetId != null) {
                predicates.add(criteriaBuilder.equal(root.get("packet").get("id"), packetId));
            }
            if (productVariantCode != null) {
                predicates.add(criteriaBuilder.like(root.get("variant").get("code"), "%"+productVariantCode+"%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return repository.findAll(specification, PageRequest.of(page - 1, 25, Sort.by("createdDate")));
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
    public Product pauseStep(Step targetStep, Long productId) {

        if (targetStep != Step.FINISHED) {
            throw new SingleProductInBatchStepException();
        }

        Product product = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(productId, Product.class));

        return pauseStep(targetStep, product, 1);
    }

    @Override
    public Product pauseStep(Step targetStep, Product product, int batchSize) {

        product.getSteps().add(product.getSteps().size() - 1,productStepService.pauseStep(targetStep, product, batchSize));
        return repository.save(product);
    }

    @Override
    public Product finishStep(Step targetStep, Long productId) {

        if (targetStep != Step.FINISHED) {
            throw new SingleProductInBatchStepException();
        }

        Product product = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(productId, Product.class));

        return finishStep(targetStep, product, 1);
    }

    @Override
    public Product finishStep(Step targetStep, Product product, int batchSize) {

        product.getSteps().add(product.getSteps().size() - 1,productStepService.finishStep(targetStep, product, batchSize));
        doNextStep(product);

        Optional<ProductStep> existing =  product.getSteps().stream()
                .filter(s -> s.getStep() == product.getNextStep())
                .findFirst();

        if (existing.isPresent()) {
            existing.get().setFinished(false);
            existing.get().setPaused(true);
        }

        return repository.save(product);
    }

    @Override
    public void archiveAll() {
        repository.archiveAll();
    }

    @Override
    public Product getByCode(String orderCode, String productCode) {
        return repository.findByCode(orderCode, productCode)
                .orElseThrow(BadPathException::new);
    }

    private void doNextStep(Product product) {
        product.setStepCounter(product.getStepCounter() + 1);
        product.setCurrentStep(product.getNextStep());
        int nextStepIdx = product.getStepCounter();
        if (nextStepIdx < product.getVariant().getProductFamily().getProductionPath().size()) {
            product.setNextStep(product.getVariant().getProductFamily().getProductionPath().get(nextStepIdx));
        } else {
            product.setNextStep(null);
        }
    }

    @Override
    public Product create(Product entity) {
        entity.setVariant(productVariantService.getOne(entity.getVariant().getId()));
        entity.setSellPrice(entity.getVariant().getPrice() - (entity.getVariant().getPrice() * ((double) entity.getOrder().getClient().getDiscountPercentage() / 100)));
        startStep(Step.ENCODED, entity);
        return super.create(entity);
    }
}
