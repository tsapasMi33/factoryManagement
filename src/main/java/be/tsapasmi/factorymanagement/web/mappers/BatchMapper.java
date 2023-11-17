package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.Batch;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import be.tsapasmi.factorymanagement.web.models.dtos.BatchDto;
import be.tsapasmi.factorymanagement.web.models.forms.BatchForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BatchMapper {


    @Named("toDto")
    @Mapping(target = "currentStep", source = "products", qualifiedByName = "mapCurrentStep")
    BatchDto toDto(Batch batch);

    @Named("mapCurrentStep")
    default Step mapCurrentStep(List<Product> products) {
        return products.get(0).getCurrentStep();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Batch partialUpdate(BatchDto batchDto, @MappingTarget Batch batch);

    @IterableMapping(qualifiedByName = "toDto")
    List<BatchDto> toDto(List<Batch> batches);

    Batch toEntity(BatchForm batchForm);
}
