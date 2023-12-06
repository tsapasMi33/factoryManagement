package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findAll(Specification<Product> specification, Pageable pageable);

    @Transactional
    @Modifying
    @Query("""
    UPDATE  Product p
    SET p.archived = true
    WHERE p.nextStep = null
""")
    void archiveAll();

    @Query("""
    SELECT p FROM Product p
    JOIN Order o ON p.order = o
    WHERE o.code = :orderCode
    AND p.code = :productCode
""")
    Optional<Product> findByCode(String orderCode, String productCode);

    List<Product> findByNextStep(Step step);
}
