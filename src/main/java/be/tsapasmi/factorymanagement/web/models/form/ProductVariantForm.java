package be.tsapasmi.factorymanagement.web.models.form;

import be.tsapasmi.factorymanagement.domain.enums.Material;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import lombok.Data;

import java.util.List;

@Data
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
