package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.exceptions.ResourceNotFoundException;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductStepService;
import be.tsapasmi.factorymanagement.dal.ProductStepRepository;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Getter
public class ProductStepServiceImpl extends BaseServiceImpl<ProductStep,Long, ProductStepRepository> implements ProductStepService {

    public ProductStepServiceImpl(ProductStepRepository repository) {
        super(repository, ProductStep.class);
    }


    @Override
    public List<ProductStep> getAllStepsForProduct(long id) {
        return repository.findAllByProduct(id);
    }

    @Override
    public ProductStep startStep(Step targetStep, Product product) {

        if (product.getNextStep() != targetStep) {
            throw new RuntimeException("Target step is not the next step for this product"); // TODO create custom exception
        }

        ProductStep step =  repository.findExistingStep(product.getId(), targetStep)
                .orElse(new ProductStep());

        if (step.isFinished()){
            throw new RuntimeException("Step is finished"); // TODO create custom exception
        }

        return switch (targetStep) {
            case ENCODED, PRODUCTION, PACKED, SENT -> {
                step.setProduct(product);
                step.setStep(targetStep);
                step.setStart(LocalDateTime.now());
                step.setFinish(LocalDateTime.now());
                step.setDuration(Duration.ofSeconds(0));
                step.setFinished(true);
                step.setPaused(false);

                yield repository.save(step);
            }
            case CUT, BENT, COMBINED, WELDED, ASSEMBLED, FINISHED -> {
                step.setProduct(product);
                step.setStep(targetStep);
                step.setStart(LocalDateTime.now());
                if (step.isPaused()) {
                    step.setPaused(false);
                    step.setDuration(step.getDuration());
                } else {
                    step.setDuration(Duration.ofSeconds(0));
                }
                step.setFinished(false);

                yield repository.save(step);
            }
        };
    }

    @Override
    public ProductStep pauseStep(Step targetStep,Product product, int batchSize) {
        ProductStep step =  repository.findExistingStep(product.getId(), Step.CUT)
                .orElseThrow(() -> new RuntimeException("Step does not exist")); // TODO create custom exception

        return switch (targetStep) {
            case ENCODED, PRODUCTION, PACKED, SENT -> throw new RuntimeException("Step cannot be paused"); // TODO create custom exception
            case CUT, BENT, COMBINED, WELDED, ASSEMBLED, FINISHED -> {

                if (step.isFinished()){
                    throw new RuntimeException("Step is finished"); // TODO create custom exception
                }

                if (step.isPaused()){
                    throw new RuntimeException("Step is paused"); // TODO create custom exception
                }

                step.setDuration(Duration.between(step.getStart(),LocalDateTime.now()).dividedBy(batchSize));
                step.setPaused(true);

                yield repository.save(step);
            }
        };

    }

    @Override
    public ProductStep finishStep(Step targetStep,Product product, int batchSize) {
        ProductStep step =  repository.findExistingStep(product.getId(), targetStep)
                .orElseThrow(() -> new RuntimeException("Step does not exist")); // TODO create custom exception

        return switch (targetStep) {
            case ENCODED, PRODUCTION, PACKED, SENT -> throw new RuntimeException("Step cannot be paused"); // TODO create custom exception
            case CUT, BENT, COMBINED, WELDED, ASSEMBLED, FINISHED -> {

                if (step.isFinished()){
                    throw new RuntimeException("Step is finished"); // TODO create custom exception
                }

                if (step.isPaused()){
                    throw new RuntimeException("Step is paused"); // TODO create custom exception
                }

                step.setFinish(LocalDateTime.now());
                step.setDuration(step.getDuration().plus(Duration.between(step.getStart(),step.getFinish())).dividedBy(batchSize));
                step.setFinished(true);

                yield repository.save(step);
            }
        };
    }
}
