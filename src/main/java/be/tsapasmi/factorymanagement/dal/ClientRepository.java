package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.Client;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {

    @Query("""
    SELECT DISTINCT c FROM Product p
    JOIN Order o ON p.order = o
    JOIN Client c ON o.client = c
    WHERE p.archived = false
""")
    List<Client> getAllActive();

    @Query("""
    SELECT DISTINCT c FROM Product p
    JOIN Order o ON p.order = o
    JOIN Client c ON o.client = c
    WHERE p.archived = false
    AND p.currentStep = :productsAtStep
""")
    List<Client> getAllActiveWithProductsAtStep(Step productsAtStep);

    @Query("""
    SELECT DISTINCT c FROM Product p
    JOIN Order o ON p.order = o
    JOIN Client c ON o.client = c
    WHERE p.archived = false
    AND p.nextStep = :productsAtNextStep
""")
    List<Client> getAllActiveWithProductsAtNextStep(Step productsAtNextStep);
}
