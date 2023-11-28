package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.Packet;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PacketRepository extends JpaRepository<Packet,Long> {
    @Query("""
    SELECT p FROM Packet p
    WHERE p.createdDate =
        (SELECT MAX(p2.createdDate) FROM Packet p2)
    """)
    Optional<Packet> getLastPacket();

    @Query("""
    SELECT p FROM Packet p
    JOIN Product pr on pr.packet = p
    WHERE pr.currentStep = :currentStep
""")
    List<Packet> findAllAtStep(Step currentStep);

    @Modifying
    @Transactional
    @Query("""
    UPDATE Product p
    SET p.packet = :packet
    WHERE p IN :products
""")
    void updateProducts(Packet packet, List<Product> products);


    @Query("""
    SELECT pa FROM Packet pa
    JOIN Product p ON p.packet = pa
    WHERE p.archived = false
""")
    List<Packet> findAllActive();

}
