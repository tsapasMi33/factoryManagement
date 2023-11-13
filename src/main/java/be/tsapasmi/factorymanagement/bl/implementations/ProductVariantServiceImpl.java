package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.ProductVariantService;
import be.tsapasmi.factorymanagement.dal.ProductVariantRepository;
import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
@Getter
public class ProductVariantServiceImpl extends BaseServiceImpl<ProductVariant,Long, ProductVariantRepository> implements ProductVariantService {

    public ProductVariantServiceImpl(ProductVariantRepository repo) {
        super(repo, ProductVariant.class);
    }

}
