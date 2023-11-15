package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.bl.interfaces.BatchService;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductService;
import be.tsapasmi.factorymanagement.domain.entities.Batch;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.web.models.dto.BatchDTO;
import be.tsapasmi.factorymanagement.web.models.dto.ProductDTO;
import be.tsapasmi.factorymanagement.web.models.form.BatchForm;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;

@Mapper(uses = {ProductService.class, BatchService.class})
public abstract class BatchMapper {

    protected ProductService productService;
    protected BatchService batchService;
    protected ProductMapper productMapper;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    @Autowired
    public void setBatchService(BatchService batchService) {
        this.batchService = batchService;
    }
    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Mapping(source = "products", target = "products", qualifiedByName = "mapProductsToProductDTOs")
    public abstract BatchDTO toDTO(Batch batch);

    public abstract List<BatchDTO> toDTO(List<Batch> batches);

    @Named("batchFormToEntity")
    @Mapping(target = "products", source = "products", qualifiedByName = "mapProducts")
    @Mapping(target = "code", expression = "java(generateCode())")
    public Batch toEntity(BatchForm form) {
        if (form == null) {
            return null;
        } else {
            Batch batch = new Batch();
            batch.setProducts(this.mapProducts(form.getProducts()));
            batch.setCode(generateCode());
            return batch;
        }
    }

    @Named("mapProducts")
    protected List<Product> mapProducts(List<Long> products) {
        return products.stream()
                .map(productId -> productService.getOne(productId))
                .toList();
    }

    @Named("mapProductsToProductDTOs")
    protected List<ProductDTO> mapProductsToProductDTOs(List<Product> products){
        return productMapper.toDTO(products);
    }


    protected String generateCode() {
        Batch lastBatch = batchService.getLastBatch();
        if (lastBatch == null || lastBatch.getCreatedDate().toLocalDate().isBefore(LocalDate.now())) {
            LocalDate today = LocalDate.now();
            return String.valueOf(today.getYear()) +
                    (today.get(ChronoField.ALIGNED_WEEK_OF_YEAR) < 10 ? "0" + today.get(ChronoField.ALIGNED_WEEK_OF_YEAR) : today.get(ChronoField.ALIGNED_WEEK_OF_YEAR)) +
                    today.getDayOfWeek().getValue() +
                    "001";
        }
        long code = Long.parseLong(lastBatch.getCode());
        code++;
        return String.valueOf(code);
    }
}
