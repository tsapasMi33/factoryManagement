package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.enums.Step;

import java.util.List;

public interface ProductService extends BaseService<Product,Long> {
    public List<Product> findAllByCriteria(Step step, Long batchId, Long packetId, Long productFamilyId);
}
