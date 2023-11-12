package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.entities.Component;

import java.util.List;

public interface ComponentService extends BaseService<Component,Long> {
    List<Component> findComponentsById(List<Long> componentIds);
}
