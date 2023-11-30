package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.StatsService;
import be.tsapasmi.factorymanagement.dal.ProductStepRepository;
import be.tsapasmi.factorymanagement.dal.UserRepository;
import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import be.tsapasmi.factorymanagement.domain.entities.User;
import be.tsapasmi.factorymanagement.domain.enums.Step;
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
    private final UserRepository userRepository;

    @Override
    public StatsDto getProductionStats(LocalDate startDate, LocalDate endDate) {
        List<ProductStep> steps = repository.findByCreatedDateBetween(startDate.atTime(0,0), endDate.atTime(23,59));
        var r = steps.stream()
                .collect(Collectors.groupingBy( s -> s.getStep().toString(),
                        Collectors.summingInt(s -> (int) s.getProduct().getVariant().getPrice())));

        List<String> labels = List.of(Step.ENCODED.toString(),
                Step.PRODUCTION.toString(),
                Step.CUT.toString(),
                Step.BENT.toString(),
                Step.COMBINED.toString(),
                Step.WELDED.toString(),
                Step.ASSEMBLED.toString(),
                Step.FINISHED.toString(),
                Step.PACKED.toString(),
                Step.SENT.toString()
                );

        return new StatsDto(r,labels,startDate,endDate);
    }

    @Override
    public StatsDto getStepStats(LocalDate startDate, LocalDate endDate, Step step) {
        List<ProductStep> steps = repository.findByStepAndCreatedDateBetween(step,startDate.atTime(0,0), endDate.atTime(23,59));

        var r = steps.stream()
                .collect(Collectors.groupingBy(s -> s.getCreatedBy().getUsername(),
                        Collectors.summingInt(s -> (int) s.getProduct().getVariant().getPrice())));

        List<String> labels = steps.stream()
                .map(s -> s.getCreatedBy().getUsername())
                .distinct()
                .toList();

        return new StatsDto(r,labels,startDate,endDate);
    }
}
