package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductStepRepository extends JpaRepository<ProductStep,Long> {

    @Query("""
           SELECT ps FROM ProductStep ps
           WHERE ps.product.id = :productId
""")
    List<ProductStep> findAllByProduct(long productId);

    @Query("""
    SELECT ps FROM ProductStep ps
    WHERE ps.product.id = :productId
    AND ps.step = :targetStep
""")
    Optional<ProductStep> findExistingStep(Long productId, Step targetStep);
}
