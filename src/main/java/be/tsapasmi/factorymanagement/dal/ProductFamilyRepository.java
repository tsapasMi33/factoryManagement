package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.ProductFamily;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFamilyRepository extends JpaRepository<ProductFamily,Long> {
}
