package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductStepRepository extends JpaRepository<ProductStep,Long> {

    @Query("""
           SELECT ps FROM ProductStep ps
           WHERE ps.product.id = :productId
""")
    List<ProductStep> findAllByProduct(long productId);
}
