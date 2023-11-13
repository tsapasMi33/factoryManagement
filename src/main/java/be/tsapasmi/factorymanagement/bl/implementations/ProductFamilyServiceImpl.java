package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.ProductFamilyService;
import be.tsapasmi.factorymanagement.dal.ProductFamilyRepository;
import be.tsapasmi.factorymanagement.domain.entities.ProductFamily;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
@Getter
public class ProductFamilyServiceImpl extends BaseServiceImpl<ProductFamily,Long, ProductFamilyRepository> implements ProductFamilyService {

    public ProductFamilyServiceImpl(ProductFamilyRepository repo) {
        super(repo, ProductFamily.class);
    }

}
