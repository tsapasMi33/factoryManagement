package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.exceptions.ResourceNotFoundException;
import be.tsapasmi.factorymanagement.bl.interfaces.*;
import be.tsapasmi.factorymanagement.dal.ProductRepository;
import be.tsapasmi.factorymanagement.domain.entities.Batch;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Getter
public class ProductServiceImpl extends BaseServiceImpl<Product, Long, ProductRepository> implements ProductService {

    private final PacketService packetService;
    private final ProductFamilyService productFamilyService;
    private final ProductStepService productStepService;

    public ProductServiceImpl(ProductRepository repo,
                              PacketService packetService,
                              ProductFamilyService productFamilyService,
                              ProductStepService productStepService) {
        super(repo, Product.class);
        this.packetService = packetService;
        this.productFamilyService = productFamilyService;
        this.productStepService = productStepService;
    }


    public List<Product> findAllByCriteria(Step currentStep, Step nextStep, Long packetId, Long productFamilyId) {
        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (currentStep != null) {
                predicates.add(criteriaBuilder.equal(root.get("currentStep"), currentStep));
            }
            if (nextStep != null) {
                predicates.add(criteriaBuilder.equal(root.get("nextStep"), nextStep));
            }
            if (packetId != null) {
                predicates.add(criteriaBuilder.equal(root.get("packet"), packetService.getOne(packetId)));
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

        if (targetStep != Step.FINISHED) {
            throw new RuntimeException("Step cannot be processed independently"); // TODO create custom exception
        }

        Product product = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(productId, Product.class));

        return startStep(targetStep, product);
    }

    @Override
    public Product startStep(Step targetStep, Product product) {

        if (product.getNextStep() != targetStep) {
            throw new RuntimeException("Target Step is not the next step of current product!");
        }

        product.getSteps().add(productStepService.startStep(targetStep, product));

        if (targetStep == Step.ENCODED || targetStep == Step.PRODUCTION || targetStep == Step.PACKED || targetStep == Step.SENT) {
            product.doNextStep();
        }

        return repository.save(product);
    }

    @Override
    public void pauseStep(Step targetStep, Long productId) {

        if (targetStep != Step.FINISHED) {
            throw new RuntimeException("Step cannot be processed independently"); // TODO create custom exception
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
            throw new RuntimeException("Step cannot be processed independently"); // TODO create custom exception
        }

        Product product = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(productId, Product.class));

        finishStep(targetStep, product, 1);
    }

    @Override
    public void finishStep(Step targetStep, Product product, int batchSize) {

        product.getSteps().add(product.getSteps().size() - 1,productStepService.finishStep(targetStep, product, batchSize));
        product.doNextStep();
        repository.save(product);
    }


}
