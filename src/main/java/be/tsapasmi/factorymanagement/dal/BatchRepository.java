package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.Batch;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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

    @Query("""
    SELECT b FROM Batch b
    LEFT JOIN Product p
    LEFT JOIN ProductVariant pv
    LEFT JOIN pv.productionPath pvs
    WHERE p.currentStep = pvs.
    """)
    List<Batch> findAllToCut();

}


//    SELECT * from batch
//            left join public.product p on batch.id = p.batch_id
//        left join product_variant pv on variant_id = pv.id
//        left join public.product_variant_steps pvs on pv.id = pvs.product_variant_id
//        WHERE p.current_step = pvs.production_path AND 'BENT' = (
//        SELECT production_path
//        FROM product_variant_steps pvs2
//        WHERE pvs2.product_variant_id = pv.id
//        AND pvs2.step_order = pvs.step_order+1
//        )
