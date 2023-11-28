package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.Batch;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Page<Batch> findAll(Specification<Batch> specification, Pageable pageable);

    @Query("""
    SELECT b FROM Batch b
    JOIN Product p on p.batch = b
    AND p.archived = false
""")
    List<Batch> findAllActive();
}



