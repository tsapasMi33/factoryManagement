package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.exceptions.IllegalCollectionException;
import be.tsapasmi.factorymanagement.bl.interfaces.PacketService;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductService;
import be.tsapasmi.factorymanagement.bl.interfaces.ShipmentService;
import be.tsapasmi.factorymanagement.dal.ShipmentRepository;
import be.tsapasmi.factorymanagement.domain.entities.Client;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.Shipment;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class ShipmentServiceImpl extends BaseServiceImpl<Shipment,Long, ShipmentRepository> implements ShipmentService {

    private final ProductService productService;
    private final PacketService packetService;

    public ShipmentServiceImpl(ShipmentRepository repository, ProductService productService, PacketService packetService) {
        super(repository, Shipment.class);
        this.productService = productService;
        this.packetService = packetService;
    }

    @Override
    public Shipment create(Shipment entity) {

        entity.setPackets(
                entity.getPackets().stream()
                        .map(packet -> packetService.getOne(packet.getId()))
                        .toList()
        );

        Client client = entity.getPackets().get(0).getProducts().get(0).getOrder().getClient();
        List<Product> products = entity.getPackets().stream()
                .flatMap(packet -> packet.getProducts().stream())
                .toList();
        if (products.stream().anyMatch(product -> product.getOrder().getClient() != client)){
            throw new IllegalCollectionException("All Shipment products must belong to the same client");
        }

        products.forEach(product -> productService.startStep(Step.SENT, product));

        Shipment created = super.create(entity);
        repository.updatePackets(created, created.getPackets());
        return created;
    }
}
