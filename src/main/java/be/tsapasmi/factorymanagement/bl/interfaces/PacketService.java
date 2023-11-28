package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.entities.Packet;
import be.tsapasmi.factorymanagement.domain.enums.Step;

import java.util.List;

public interface PacketService extends BaseService<Packet,Long> {

    Packet getLastPacket();

    List<Packet> findAllAtStep(Step step);

    List<Packet> findAllActive();
}
