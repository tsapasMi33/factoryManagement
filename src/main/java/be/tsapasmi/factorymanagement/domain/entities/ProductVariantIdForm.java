package be.tsapasmi.factorymanagement.domain.entities;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ProductVariant}
 */
@Value
public class ProductVariantIdForm implements Serializable {
    Long id;
}
