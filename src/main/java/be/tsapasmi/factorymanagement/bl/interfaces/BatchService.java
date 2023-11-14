package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.entities.Batch;
import be.tsapasmi.factorymanagement.domain.enums.Step;

import java.util.List;

public interface BatchService extends BaseService<Batch,Long> {
    Batch getLastBatch();

    List<Batch> findAllByCriteria(Step step);

    List<Batch> findAllToCut();
}
