package be.tsapasmi.factorymanagement.web.models.form;

import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import be.tsapasmi.factorymanagement.domain.enums.Material;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DTO for {@link ProductVariant}
 */
@Getter
@Setter
public class ProductVariantForm {

    private List<Step> productionPath;

    private Material material;

    private String variantIdentifier;

    private Integer width;

    private Integer length;

    private Integer height;

    private double price;

    private String description;

    private long productFamilyId;

    private List<Long> componentIds;
}
