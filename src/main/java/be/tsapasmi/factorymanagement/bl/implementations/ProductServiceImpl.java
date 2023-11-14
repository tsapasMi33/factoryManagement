package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.*;
import be.tsapasmi.factorymanagement.dal.ProductRepository;
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
public class ProductServiceImpl extends BaseServiceImpl<Product,Long,ProductRepository> implements ProductService {

    private final EntityManager entityManager;
    private final BatchService batchService;
    private final PacketService packetService;
    private final ProductFamilyService productFamilyService;

    public ProductServiceImpl(ProductRepository repo,
                              EntityManager entityManager,
                              BatchService batchService,
                              PacketService packetService,
                              ProductFamilyService productFamilyService) {
        super(repo, Product.class);
        this.entityManager = entityManager;
        this.batchService = batchService;
        this.packetService = packetService;
        this.productFamilyService = productFamilyService;
    }


    public List<Product> findAllByCriteria(Step step, Long batchId, Long packetId, Long productFamilyId) {
        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (step != null) {
                predicates.add(criteriaBuilder.equal(root.get("currentStep"), step));
            }
            if (batchId != null) {
                predicates.add(criteriaBuilder.equal(root.get("batch"), batchService.getOne(batchId)));
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
}
