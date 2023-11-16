package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.bl.interfaces.PacketService;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductService;
import be.tsapasmi.factorymanagement.domain.entities.Packet;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.web.models.dto.PacketDTO;
import be.tsapasmi.factorymanagement.web.models.form.PacketForm;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Mapper(uses = {ProductService.class, PacketService.class})
public abstract class PacketMapper {

    protected ProductService productService;
    protected PacketService packetService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    @Autowired
    public void setPacketService(PacketService packetService) {
        this.packetService = packetService;
    }

    public abstract PacketDTO toDTO(Packet packet);

    public abstract List<PacketDTO> toDTO(List<Packet> packets);


    public Packet toEntity(PacketForm form)  {
        if (form == null) {
            return null;
        } else {
            Packet packet = new Packet();
            packet.setProducts(this.mapProducts(form.getProductIds()));
            packet.setCode(generateCode());
            return packet;
        }
    }

    @Named("mapProducts")
    protected List<Product> mapProducts(List<Long> productIds) {
        return productIds.stream()
                .map(productId -> productService.getOne(productId))
                .toList();
    }

    protected String generateCode() {
        Packet lastPacket = packetService.getLastPacket();
        if (lastPacket == null || lastPacket.getCreatedDate().toLocalDate().isBefore(LocalDate.now())) {
            LocalDate today = LocalDate.now();
            return String.valueOf(today.getYear()).replace("20", "") +
                    (today.getMonthValue() < 10 ? "0" + today.getMonthValue() : today.getMonthValue()) +
                    (today.getDayOfMonth() < 10 ? "0" + today.getDayOfMonth() : today.getDayOfMonth() ) +
                    "001";
        }
        long code = Long.parseLong(lastPacket.getCode());
        code++;
        return String.valueOf(code);
    }
}
