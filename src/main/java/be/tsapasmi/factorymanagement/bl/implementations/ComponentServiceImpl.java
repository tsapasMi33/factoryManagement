package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.ComponentService;
import be.tsapasmi.factorymanagement.dal.ComponentRepository;
import be.tsapasmi.factorymanagement.domain.entities.Component;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class ComponentServiceImpl extends BaseServiceImpl<Component,Long, ComponentRepository> implements ComponentService {

    public ComponentServiceImpl(ComponentRepository repo) {
        super(repo, Component.class);
    }

    @Override
    public List<Component> findComponentsById(List<Long> componentIds) {
        return repository.findAllById(componentIds);
    }
}
