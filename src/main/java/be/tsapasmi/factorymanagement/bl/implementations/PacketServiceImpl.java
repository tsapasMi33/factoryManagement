package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.BatchService;
import be.tsapasmi.factorymanagement.bl.interfaces.PacketService;
import be.tsapasmi.factorymanagement.dal.BatchRepository;
import be.tsapasmi.factorymanagement.dal.PacketRepository;
import be.tsapasmi.factorymanagement.domain.entities.Batch;
import be.tsapasmi.factorymanagement.domain.entities.Packet;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class PacketServiceImpl extends BaseServiceImpl<Packet,Long, PacketRepository> implements PacketService {
    public PacketServiceImpl(PacketRepository repository) {
        super(repository, Packet.class);
    }
}
