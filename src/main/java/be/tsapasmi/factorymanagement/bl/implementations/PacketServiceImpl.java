package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.exceptions.IllegalCollectionException;
import be.tsapasmi.factorymanagement.bl.interfaces.PacketService;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductService;
import be.tsapasmi.factorymanagement.dal.PacketRepository;
import be.tsapasmi.factorymanagement.domain.entities.Client;
import be.tsapasmi.factorymanagement.domain.entities.Packet;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;


@Service
@Getter
public class PacketServiceImpl extends BaseServiceImpl<Packet, Long, PacketRepository> implements PacketService {

    private final ProductService productService;

    public PacketServiceImpl(PacketRepository repository, ProductService productService) {
        super(repository, Packet.class);
        this.productService = productService;
    }

    @Override
    public Packet getLastPacket() {
        return repository.getLastPacket()
                .orElse(null);
    }

    @Override
    public List<Packet> findAllAtStep(Step step) {
        return repository.findAllAtStep(step);
    }

    @Override
    public List<Packet> findAllActive() {
        return repository.findAllActive();
    }

    @Override
    public Packet create(Packet entity) {

        entity.setProducts(
                entity.getProducts().stream()
                        .map(product -> productService.getOne(product.getId()))
                        .toList()
        );

        Client client = entity.getProducts().get(0).getOrder().getClient();

        entity.setCode(generateCode());
        if (entity.getProducts().stream().anyMatch(product -> product.getOrder().getClient() != client)) {
            throw new IllegalCollectionException("All Packet products must belong to the same client!");
        }

        entity.getProducts()
                .forEach(product -> productService.startStep(Step.PACKED, product));

        Packet created = super.create(entity);
        repository.updateProducts(created, created.getProducts());
        return created;
    }

    private String generateCode() {
        LocalDate today = LocalDate.now();
        Optional<Packet> last = repository.getLastPacket();

        if (last.isPresent() && last.get().getCreatedDate().toLocalDate().isBefore(today) || last.isEmpty()){
            return String.valueOf(today.getYear()).substring(2) +
                    (today.getMonthValue() < 10 ? '0' + today.getMonthValue() : today.getMonthValue()) +
                    today.get(ChronoField.ALIGNED_WEEK_OF_MONTH) +
                    "0001";
        }
        long lastCode = Long.parseLong(last.get().getCode());
        return String.valueOf(++lastCode);
    }
}
