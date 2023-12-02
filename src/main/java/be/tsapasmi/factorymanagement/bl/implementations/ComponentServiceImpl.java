package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.ComponentService;
import be.tsapasmi.factorymanagement.bl.interfaces.MaterialTypeService;
import be.tsapasmi.factorymanagement.dal.ComponentRepository;
import be.tsapasmi.factorymanagement.domain.entities.Component;
import be.tsapasmi.factorymanagement.domain.enums.PricingMethod;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class ComponentServiceImpl extends BaseServiceImpl<Component,Long, ComponentRepository> implements ComponentService {

    private final MaterialTypeService materialTypeService;

    public ComponentServiceImpl(ComponentRepository repo, MaterialTypeService materialTypeService) {
        super(repo, Component.class);
        this.materialTypeService = materialTypeService;
    }

    @Override
    public Component create(Component entity) {
        entity.setType(materialTypeService.getOne(entity.getType().getId()));

        entity.setPrice( switch (entity.getType().getPricingMethod()) {
            case METER -> entity.getType().getBasePrice() / 1000 * entity.getLength();
            case SQUARE_METER -> entity.getType().getBasePrice() / 1000 * entity.getLength() * entity.getWidth();
            case CUBE_METER -> entity.getType().getBasePrice() / 1000 * entity.getLength() * entity.getWidth() * entity.getThickness();
            case UNIT -> entity.getPrice();
        });


        return super.create(entity);
    }

    @Override
    public List<Component> findComponentsById(List<Long> componentIds) {
        return repository.findAllById(componentIds);
    }
}
