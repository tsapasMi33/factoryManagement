package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    List<ProductStep> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<ProductStep> findByStepAndCreatedDateBetween(Step step, LocalDateTime startDate, LocalDateTime endDate);

    List<ProductStep> findByCreatedBy_UsernameAndStepAndCreatedDateBetween(String username, Step step, LocalDateTime startDate, LocalDateTime endDate);

    @Query("""
    SELECT CAST(AVG(ps.duration) as INTEGER) FROM ProductStep ps
    JOIN Product p ON ps.product = p
    JOIN ProductVariant v ON p.variant = v
    WHERE v = :v
    AND ps.step = :step
    AND ps.createdDate BETWEEN :startDate AND :currentDate
""")
    Long findAverageTimeForStep(ProductVariant v, Step step, LocalDateTime startDate, LocalDateTime currentDate);
}
