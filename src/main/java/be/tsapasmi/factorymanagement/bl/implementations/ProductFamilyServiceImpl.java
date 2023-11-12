package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.ProductFamilyService;
import be.tsapasmi.factorymanagement.dal.ProductFamilyRepository;
import be.tsapasmi.factorymanagement.domain.entities.ProductFamily;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
@Getter
@AllArgsConstructor
public class ProductFamilyServiceImpl extends BaseServiceImpl<ProductFamily,Long, ProductFamilyRepository> implements ProductFamilyService {

    private final ProductFamilyRepository repository;

    @Override
    public Class<ProductFamily> getResourceClass() {
        return ProductFamily.class;
    }
}
