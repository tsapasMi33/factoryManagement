package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.Packet;
import be.tsapasmi.factorymanagement.domain.entities.Shipment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    @Modifying
    @Transactional
    @Query("""
    UPDATE Packet p
    SET p.shipment = :shipment
    WHERE p IN :packets
""")
    void updatePackets(Shipment shipment, List<Packet> packets);
}
