package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.exceptions.*;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductStepService;
import be.tsapasmi.factorymanagement.bl.interfaces.UserService;
import be.tsapasmi.factorymanagement.dal.ProductStepRepository;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import be.tsapasmi.factorymanagement.domain.entities.User;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import lombok.Getter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Getter
public class ProductStepServiceImpl extends BaseServiceImpl<ProductStep,Long, ProductStepRepository> implements ProductStepService {

    private final UserService userService;

    public ProductStepServiceImpl(ProductStepRepository repository, UserService userService) {
        super(repository, ProductStep.class);
        this.userService = userService;
    }


    @Override
    public List<ProductStep> getAllStepsForProduct(long id) {
        return repository.findAllByProduct(id);
    }

    @Override
    public ProductStep startStep(Step targetStep, Product product) {

        if (!userService.isUserAvailable( (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())){
            throw new UserOccupiedException(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        }

        if (product.getNextStep() != targetStep) {
            throw new BadPathException(targetStep, product);
        }

        ProductStep step =  repository.findExistingStep(product.getId(), targetStep)
                .orElse(new ProductStep());

        if (step.isFinished()){
            step.setFinished(false);
        }

        step.setProduct(product);
        step.setStep(targetStep);
        step.setStart(LocalDateTime.now());

        if (!targetStep.isMeasurable()) {
            step.setFinish(LocalDateTime.now());
            step.setDuration(Duration.ofSeconds(0));
            step.setFinished(true);
            step.setPaused(false);
            return repository.save(step);
        }

        if (step.isPaused()) {
            step.setPaused(false);
            step.setDuration(step.getDuration());
        } else {
            step.setDuration(Duration.ofSeconds(0));
        }
        step.setFinished(false);
        return repository.save(step);
    }

    @Override
    public ProductStep pauseStep(Step targetStep,Product product, int batchSize) {
        ProductStep step =  repository.findExistingStep(product.getId(), targetStep)
                .orElseThrow(() -> new NotStartedStepException(targetStep, product));

        if (!targetStep.isMeasurable()) {
            throw new IllegalActionOnStep(targetStep, "paused");
        }

        if (step.isPaused()){
            throw new PausedStepException(targetStep, product);
        }

        if (step.isFinished()){
            throw new FinishedStepException(targetStep, product);
        }

        step.setDuration(Duration.between(step.getStart(),LocalDateTime.now()).dividedBy(batchSize));
        step.setPaused(true);

        return repository.save(step);
    }

    @Override
    public ProductStep finishStep(Step targetStep,Product product, int batchSize) {
        ProductStep step =  repository.findExistingStep(product.getId(), targetStep)
                .orElseThrow(() -> new NotStartedStepException(targetStep, product));

        if (!targetStep.isMeasurable()) {
            throw  new IllegalActionOnStep(targetStep, "finished");
        }

        if (step.isPaused()){
            throw new PausedStepException(targetStep, product);
        }

        if (step.isFinished()){
            throw new FinishedStepException(targetStep, product);
        }

        step.setFinish(LocalDateTime.now());
        step.setDuration(step.getDuration().plus(Duration.between(step.getStart(),step.getFinish())).dividedBy(batchSize));
        step.setFinished(true);
        step.setStepCost(step.getDuration().toMinutes() * step.getCreatedBy().getCostPerMinute());

        return repository.save(step);
    }
}
