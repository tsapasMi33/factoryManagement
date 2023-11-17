package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.ComponentService;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductFamilyService;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductVariantService;
import be.tsapasmi.factorymanagement.dal.ProductVariantRepository;
import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
@Getter
public class ProductVariantServiceImpl extends BaseServiceImpl<ProductVariant,Long, ProductVariantRepository> implements ProductVariantService {

    private final ProductFamilyService productFamilyService;
    private final ComponentService componentService;

    public ProductVariantServiceImpl(ProductVariantRepository repo, ProductFamilyService productFamilyService, ComponentService componentService) {
        super(repo, ProductVariant.class);
        this.productFamilyService = productFamilyService;
        this.componentService = componentService;
    }

    @Override
    public ProductVariant create(ProductVariant entity) {
        entity.setProductFamily(productFamilyService.getOne(entity.getProductFamily().getId()));
        entity.setComponents(
                entity.getComponents().stream()
                        .map(component -> componentService.getOne(component.getId()))
                        .toList()
        );
        entity.setCode(generateCode(entity));
        return super.create(entity);
    }

    private String generateCode(ProductVariant productVariant) {

        return productVariant.getProductFamily().getName().substring(0, 3).toUpperCase() +
                productVariant.getMaterial().name().toUpperCase() +
                "[" +
                (productVariant.getLength() != null ? "L" + productVariant.getLength() + "x" : "") +
                (productVariant.getWidth() != null ? "W" + productVariant.getWidth() + "x" : "") +
                (productVariant.getWidth() != null ? "H" + productVariant.getHeight() : "") +
                "]" +
                productVariant.getVariantIdentifier().toUpperCase();
    }
}
