package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.ProductFamily;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductFamilyRepository extends JpaRepository<ProductFamily,Long> {

    @Query("""
    SELECT pf FROM ProductFamily pf
    JOIN ProductVariant pv ON pv.productFamily = pf
    JOIN Product p ON p.variant = pv
    WHERE p.archived = false
""")
    List<ProductFamily> findAllActive();

    @Query("""
    SELECT pf FROM ProductFamily pf
    JOIN ProductVariant pv ON pv.productFamily = pf
    JOIN Product p ON p.variant = pv
    WHERE p.archived = false
    AND p.currentStep = :productsAtStep
""")
    List<ProductFamily> getAllActiveWithProductsAtStep(Step productsAtStep);
}
