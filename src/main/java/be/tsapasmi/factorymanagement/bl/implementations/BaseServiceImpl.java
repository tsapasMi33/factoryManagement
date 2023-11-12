package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.exceptions.ResourceNotFoundException;
import be.tsapasmi.factorymanagement.bl.interfaces.BaseService;
import be.tsapasmi.factorymanagement.domain.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public abstract class BaseServiceImpl<T extends BaseEntity,K extends Long,R extends JpaRepository<T,K>> implements BaseService<T,K> {

    public abstract R getRepository();
    public abstract Class<T> getResourceClass();

    @Override
    public void create(T entity) {
        getRepository().save(entity);
    }

    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }

    @Override
    public T getOne(K id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, getResourceClass()));
    }

    @Override
    public T update(K id, T entity) {
        if (getRepository().existsById(id)) {
            throw new ResourceNotFoundException(id, getResourceClass());
        }
        entity.setId(id);
        return getRepository().save(entity);
    }

    @Override
    public void delete(K id) {
        if (!getRepository().existsById(id)) {
            throw new ResourceNotFoundException(id, getResourceClass());
        }
        getRepository().deleteById(id);
    }
}
