package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.Batch;
import be.tsapasmi.factorymanagement.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("""
    SELECT o FROM Order o
    WHERE o.createdDate =
        (SELECT MAX(o2.createdDate) FROM Order o2)
    """)
    Optional<Order> getLastOrder();
}
