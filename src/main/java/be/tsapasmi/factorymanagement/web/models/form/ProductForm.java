package be.tsapasmi.factorymanagement.web.models.form;

import be.tsapasmi.factorymanagement.domain.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
@Getter
@Setter
public class ProductForm implements Serializable {
    private String comments;
    private Long variantId;
}
