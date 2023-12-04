package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductVariantRepository extends JpaRepository<ProductVariant,Long> {

    @Transactional
    @Modifying
    @Query("""
    UPDATE ProductVariant p
    SET p.price = :price
    WHERE p.id = :id
""")
    void updatePrice(long id, double price);
}
