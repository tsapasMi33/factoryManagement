package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.entities.ProductFamily;
import be.tsapasmi.factorymanagement.domain.enums.Step;

import java.util.List;

public interface ProductFamilyService extends BaseService<ProductFamily,Long> {

    List<ProductFamily> getAllActive(Step productsAtStep);
}
