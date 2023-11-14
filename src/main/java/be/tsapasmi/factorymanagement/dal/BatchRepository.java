package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.Batch;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch,Long> {

    @Query("""
    SELECT b FROM Batch b
    WHERE b.createdDate =
        (SELECT MAX(b2.createdDate) FROM Batch b2)
    """)
    Optional<Batch> getLastBatch();

    @Modifying
    @Transactional
    @Query("""
    UPDATE Product p
    SET p.batch = :batch
    WHERE p IN :products
""")
    void updateProducts(Batch batch, List<Product> products);

    List<Batch> findAll(Specification<Batch> specification);

    @Query(nativeQuery = true, value = """
    SELECT b.created_by_id, b.created_date, b.id, b.last_modified_by_id, b.last_modified_date, b.code
    FROM batch b
    LEFT JOIN product p ON b.id = p.batch_id
    LEFT JOIN product_variant pv ON p.variant_id = pv.id
    LEFT JOIN product_variant_steps pvs ON pv.id = pvs.product_variant_id
    WHERE p.current_step = pvs.production_path AND 'CUT' = (
        SELECT production_path
        FROM product_variant_steps pvs2
        WHERE pvs2.product_variant_id = pv.id
        AND pvs2.step_order = pvs.step_order + 1
    )
""")
    List<Batch> findAllForStep(Step targetStep);

}



