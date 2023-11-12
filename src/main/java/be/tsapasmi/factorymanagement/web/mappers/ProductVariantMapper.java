package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.bl.interfaces.ComponentService;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductFamilyService;
import be.tsapasmi.factorymanagement.domain.entities.Component;
import be.tsapasmi.factorymanagement.domain.entities.ProductFamily;
import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import be.tsapasmi.factorymanagement.web.models.dto.ProductVariantDTO;
import be.tsapasmi.factorymanagement.web.models.form.ProductVariantForm;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(uses = {ProductFamilyService.class, ComponentService.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ProductVariantMapper {

    protected ProductFamilyService productFamilyService;
    protected ComponentService componentService;

    @Autowired
    public void setProductFamilyService(ProductFamilyService productFamilyService){
        this.productFamilyService = productFamilyService;
    }

    @Autowired
    public void setComponentService(ComponentService componentService){
        this.componentService = componentService;
    }

    public abstract ProductVariantDTO toDTO(ProductVariant productVariant);

    public abstract List<ProductVariantDTO> toDTO(List<ProductVariant> productVariant);

    @Mapping(target = "productFamily", source = "productFamilyId", qualifiedByName = "mapProductFamily")
    @Mapping(target = "components", source = "componentIds", qualifiedByName = "mapComponentIds")
    @Mapping(target = "code", expression = "java(generateCode(form))")
    public abstract ProductVariant toEntity(ProductVariantForm form);

    @Named("mapProductFamily")
    protected ProductFamily mapProductFamily(long productFamilyId) {
        return productFamilyService.getOne(productFamilyId);
    }

    @Named("mapComponentIds")
    protected List<Component> mapComponentIds(List<Long> componentIds) {
        return componentService.findComponentsById(componentIds);
    }

    protected String generateCode(ProductVariantForm form) {
        StringBuilder sb = new StringBuilder();
        ProductFamily pf = productFamilyService.getOne(form.getProductFamilyId());

        sb.append(pf.getName().toUpperCase(), 0, 3)
                .append(form.getMaterial().name());
        sb.append("[");
        if (form.getLength() != null) sb.append("L").append(form.getLength() / 10).append("x");
        if (form.getLength() != null) sb.append("W").append(form.getWidth() / 10).append("x");
        if (form.getLength() != null) sb.append("H").append(form.getHeight() / 10);
        sb.append("]");
        sb.append(form.getVariantIdentifier().toUpperCase());

        return sb.toString();
    }
}
