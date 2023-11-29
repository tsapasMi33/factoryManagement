package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.entities.Batch;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BatchService extends BaseService<Batch,Long> {
    Batch getLastBatch();

    Page<Batch> findAllByCriteria(int page, Step currentStep, Step nextStep);

    Batch startStep(Step targetStep, Long batchId);

    Batch pauseStep(Step targetStep, Long batchId);

    Batch finishStep(Step targetStep, Long batchId);

    List<Batch> getAllActive();

    Batch getByCode(String code);
}
