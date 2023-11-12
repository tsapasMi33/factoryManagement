package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.ProductVariantService;
import be.tsapasmi.factorymanagement.dal.ProductVariantRepository;
import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
@Getter
@AllArgsConstructor
public class ProductVariantServiceImpl extends BaseServiceImpl<ProductVariant,Long, ProductVariantRepository> implements ProductVariantService {

    private final ProductVariantRepository repository;


    @Override
    public Class<ProductVariant> getResourceClass() {
        return ProductVariant.class;
    }
}
