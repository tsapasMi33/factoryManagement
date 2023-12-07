package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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

    @Query(nativeQuery = true, value = """
    SELECT COALESCE(CAST(sum(rslt2.average) AS INTEGER), 3600000000000)
      FROM
      (
          SELECT AVG(ps.duration) * COUNT(p.id) as "average"
          FROM product_step ps
            LEFT JOIN product p ON ps.product_id = p.id
          WHERE ps.step = :step AND p.variant_id IN
            (
              SELECT p.variant_id
              FROM product p
                LEFT JOIN product_variant pv ON p.variant_id = pv.id
              WHERE p.next_step = :step
            )
            AND ps.id IN
            (
              SELECT ps2.id
              FROM product_step ps2
                LEFT JOIN product p2 ON ps2.product_id = p2.id
              WHERE p2.variant_id = p.variant_id AND ps2.step = :step
              ORDER BY ps2.created_date DESC
              LIMIT 15
            )
        GROUP BY p.variant_id
        ) as rslt2;
""")
    Long findAverageTimeForStep(String step);


    @Query("""
    SELECT pf.name, AVG(pv.price) FROM ProductFamily pf
    LEFT JOIN ProductVariant pv ON pv.productFamily = pf
    GROUP BY pf.name
""")
    Map<String,Double> getOverallCatalogPrice();


    @Query("""
    SELECT pf.name, COALESCE(AVG(p.sellPrice),0) FROM ProductFamily pf
    LEFT JOIN ProductVariant pv ON pv.productFamily = pf
    LEFT JOIN Product p ON p.variant = pv
    GROUP BY pf.name
""")
    Map<String,Double> getOverallSellPrice();


    @Query(nativeQuery = true, value = """
    SELECT pf.name, AVG
    (
        (
        SELECT SUM(c.price)
        FROM component c
        RIGHT JOIN product_variant_components pvc on c.id = pvc.components_id
        RIGHT JOIN product_variant pv ON pvc.product_variant_id = pv.id
        WHERE pv.product_family_id = pf.id
         )
    +
        (
        SELECT sum(ps.step_cost)
        FROM product_step ps
        RIGHT JOIN product p ON ps.product_id = p.id
        RIGHT JOIN product_variant pv2 ON p.variant_id = pv2.id
        WHERE pv2.product_family_id = pf.id
        )
    )
    FROM product_family pf
    GROUP BY pf.name
""")
    Map<String,Double> getOverallProductionCost();
}
