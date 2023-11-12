package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.PackageService;
import be.tsapasmi.factorymanagement.dal.ComponentRepository;
import be.tsapasmi.factorymanagement.dal.PackageRepository;
import be.tsapasmi.factorymanagement.domain.entities.Component;
import be.tsapasmi.factorymanagement.domain.entities.Package;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@AllArgsConstructor
public class PackageServiceImpl extends BaseServiceImpl<Package,Long, PackageRepository> implements PackageService {

    private final PackageRepository repository;

    @Override
    public Class<Package> getResourceClass() {
        return Package.class;
    }
}
