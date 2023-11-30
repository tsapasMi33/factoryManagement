package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.StatsService;
import be.tsapasmi.factorymanagement.dal.ProductStepRepository;
import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import be.tsapasmi.factorymanagement.web.models.dtos.StatsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final ProductStepRepository repository;

    @Override
    public StatsDto getProductionStatsForPeriod(LocalDate startDate, LocalDate endDate) {
        List<ProductStep> steps = repository.findByCreatedDateBetween(startDate.atTime(0,0), endDate.atTime(23,59));
        var r = steps.stream()
                .collect(Collectors.groupingBy( s -> s.getStep().toString(),
                        Collectors.summingInt(s -> (int) s.getProduct().getVariant().getPrice())));
        System.out.println(r);

        return new StatsDto(r,startDate,endDate);
    }
}
