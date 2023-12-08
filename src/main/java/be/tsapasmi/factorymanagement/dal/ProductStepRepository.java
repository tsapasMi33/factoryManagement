package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.dal.projections.BenefitProjection;
import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductStepRepository extends JpaRepository<ProductStep, Long> {

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
                SELECT COALESCE(CAST(sum(rslt2.average) AS INTEGER), 3600)
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
                SELECT pf.name as name, ROUND(AVG(pv.price),2) as average FROM ProductFamily pf
                LEFT JOIN ProductVariant pv ON pv.productFamily = pf
                GROUP BY pf.name
            """)
    List<BenefitProjection> getOverallCatalogPrice();


    @Query("""
                SELECT pf.name as name, COALESCE(ROUND(AVG(p.sellPrice),2),0) as average FROM ProductFamily pf
                LEFT JOIN ProductVariant pv ON pv.productFamily = pf
                LEFT JOIN Product p ON p.variant = pv
                GROUP BY pf.name
            """)
    List<BenefitProjection> getOverallSellPrice();


    @Query(nativeQuery = true, value = """
                SELECT pf.name as name, COALESCE(ROUND(AVG
                (
                    (
                    SELECT AVG(materials.sum) FROM
                    (
                        SELECT pv.code, SUM(c.price) AS sum
                        FROM component c
                        RIGHT JOIN product_variant_components pvc on c.id = pvc.components_id
                        RIGHT JOIN product_variant pv ON pvc.product_variant_id = pv.id
                        WHERE pv.product_family_id = pf.id
                        GROUP BY pv.code
                        ) materials
                     )
                +
                    (
                    SELECT COALESCE(AVG(work.sum),0) FROM
                        (
                            SELECT pv2.code, SUM(ps.step_cost) AS sum
                            FROM product_step ps
                            RIGHT JOIN product p ON ps.product_id = p.id
                            RIGHT JOIN product_variant pv2 ON p.variant_id = pv2.id
                            WHERE pv2.product_family_id = pf.id
                            AND ps.id IN
                                (
                                    SELECT ps2.id
                                    FROM product_step ps2
                                    WHERE ps2.product_id = p.id
                                    ORDER BY ps2.created_date DESC
                                    LIMIT 15
                                )
                            GROUP BY pv2.code
                            ) work
                        )
                )), 0) as average
                FROM product_family pf
                GROUP BY pf.name
            """)
    List<BenefitProjection> getOverallProductionCost();


    @Query("""
                SELECT pv.code as name, ROUND(AVG(pv.price),2) as average FROM ProductVariant pv
                            WHERE pv.productFamily.name = :family
                            GROUP BY pv.code
            """)
    List<BenefitProjection> getOverallCatalogPriceFor(String family);

    @Query("""
                SELECT pv.code as name, COALESCE(ROUND(AVG(p.sellPrice),2),0) as average FROM ProductVariant pv
                LEFT JOIN Product p ON p.variant = pv
                WHERE pv.productFamily.name = :family
                GROUP BY pv.code
            """)
    List<BenefitProjection> getOverallSellPriceFor(String family);

    @Query(nativeQuery = true, value = """
                SELECT name, ROUND(COALESCE(SUM(materials), 0) + COALESCE(SUM(work), 0)) AS average
                                FROM (
                                    SELECT pv.code AS name, SUM(c.price) AS materials, 0 AS work
                                    FROM component c
                                    RIGHT JOIN product_variant_components pvc ON c.id = pvc.components_id
                                    RIGHT JOIN product_variant pv ON pvc.product_variant_id = pv.id
                                    WHERE pv.product_family_id = (SELECT pf.id FROM product_family pf WHERE pf.name = :family)
                                    GROUP BY pv.code
                                
                                    UNION ALL
                                
                                    SELECT pv2.code AS name, 0 AS decimal1, SUM(ps.step_cost) AS decimal2
                                    FROM product_step ps
                                    RIGHT JOIN product p ON ps.product_id = p.id
                                    RIGHT JOIN product_variant pv2 ON p.variant_id = pv2.id
                                    WHERE pv2.product_family_id = (
                                        SELECT pf.id FROM product_family pf WHERE pf.name = :family)
                                        AND ps.id IN (
                                            SELECT ps2.id
                                            FROM product_step ps2
                                            WHERE ps2.product_id = p.id
                                            ORDER BY ps2.created_date DESC
                                            LIMIT 15
                                        )
                                    GROUP BY
                                        pv2.code
                                ) AS union_query
                                GROUP BY
                                    name;
            """)
    List<BenefitProjection> getOverallProductionCostFor(String family);
}
