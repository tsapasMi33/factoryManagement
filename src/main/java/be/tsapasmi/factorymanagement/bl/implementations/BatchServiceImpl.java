package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.BatchService;
import be.tsapasmi.factorymanagement.dal.BatchRepository;
import be.tsapasmi.factorymanagement.domain.entities.Batch;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class BatchServiceImpl extends BaseServiceImpl<Batch,Long, BatchRepository> implements BatchService {
    public BatchServiceImpl(BatchRepository repository) {
        super(repository, Batch.class);
    }
}
