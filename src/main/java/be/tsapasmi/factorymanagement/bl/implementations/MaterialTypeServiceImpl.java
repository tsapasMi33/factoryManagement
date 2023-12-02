package be.tsapasmi.factorymanagement.bl.implementations;


import be.tsapasmi.factorymanagement.bl.interfaces.MaterialTypeService;
import be.tsapasmi.factorymanagement.dal.MaterialTypeRepository;
import be.tsapasmi.factorymanagement.domain.entities.MaterialType;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class MaterialTypeServiceImpl extends BaseServiceImpl<MaterialType,Long, MaterialTypeRepository> implements MaterialTypeService {
    MaterialTypeServiceImpl(MaterialTypeRepository repository) {
        super(repository, MaterialType.class);
    }
}
