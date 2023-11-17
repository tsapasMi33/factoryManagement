package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.ProductFamilyService;
import be.tsapasmi.factorymanagement.dal.ProductFamilyRepository;
import be.tsapasmi.factorymanagement.domain.entities.ProductFamily;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Getter
public class ProductFamilyServiceImpl extends BaseServiceImpl<ProductFamily,Long, ProductFamilyRepository> implements ProductFamilyService {

    public ProductFamilyServiceImpl(ProductFamilyRepository repo) {
        super(repo, ProductFamily.class);
    }


    @Override
    public ProductFamily create(ProductFamily entity) {
        List<Step> path = entity.getProductionPath();
        path.addAll(0, List.of(Step.ENCODED, Step.PRODUCTION));
        path.addAll(List.of(Step.PACKED, Step.SENT));

        entity.setProductionPath(path);
        return super.create(entity);
    }
}
