package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.ProductState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStateRepository extends JpaRepository<ProductState,Long> {
}
