package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.BatchService;
import be.tsapasmi.factorymanagement.dal.BatchRepository;
import be.tsapasmi.factorymanagement.dal.ProductRepository;
import be.tsapasmi.factorymanagement.domain.entities.Batch;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@AllArgsConstructor
public class BatchServiceImpl extends BaseServiceImpl<Batch,Long, BatchRepository> implements BatchService {

    private final BatchRepository repository;

    @Override
    public Class<Batch> getResourceClass() {
        return Batch.class;
    }
}
