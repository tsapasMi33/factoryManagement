package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.StatsService;
import be.tsapasmi.factorymanagement.dal.ProductRepository;
import be.tsapasmi.factorymanagement.dal.ProductStepRepository;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import be.tsapasmi.factorymanagement.web.models.dtos.EvolutionDto;
import be.tsapasmi.factorymanagement.web.models.dtos.StatsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final ProductRepository productRepository;
    private final ProductStepRepository repository;

    @Override
    public StatsDto getProductionStats(LocalDate startDate, LocalDate endDate) {
        List<ProductStep> steps = repository.findByCreatedDateBetween(startDate.atTime(0, 0), endDate.atTime(23, 59));
        var r = steps.stream()
                .collect(Collectors.groupingBy(s -> s.getStep().toString(),
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

        return new StatsDto(r, labels, startDate, endDate);
    }

    @Override
    public StatsDto getStepStats(LocalDate startDate, LocalDate endDate, Step step) {
        List<ProductStep> steps = repository.findByStepAndCreatedDateBetween(step, startDate.atTime(0, 0), endDate.atTime(23, 59));

        var r = steps.stream()
                .collect(Collectors.groupingBy(s -> s.getCreatedBy().getUsername(),
                        Collectors.summingInt(s -> (int) s.getProduct().getVariant().getPrice())));

        List<String> labels = steps.stream()
                .map(s -> s.getCreatedBy().getUsername())
                .distinct()
                .toList();

        return new StatsDto(r, labels, startDate, endDate);
    }

    @Override
    public StatsDto getUserStatsForStep(LocalDate startDate, LocalDate endDate, Step step, String username) {
        List<ProductStep> steps = repository.findByCreatedBy_UsernameAndStepAndCreatedDateBetween(username, step, startDate.atTime(0, 0), endDate.atTime(23, 59));

        var r = steps.stream()
                .collect(Collectors.groupingBy(s -> s.getProduct().getVariant().getProductFamily().getName(),
                        Collectors.summingInt(s -> (int) s.getProduct().getVariant().getPrice())));

        List<String> labels = steps.stream()
                .map(s -> s.getProduct().getVariant().getProductFamily().getName())
                .distinct()
                .toList();

        return new StatsDto(r, labels, startDate, endDate);
    }

    @Override
    public EvolutionDto getEvolutionStats(boolean comparison, int periodSpan) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(periodSpan);

        List<ProductStep> steps = repository.findByCreatedDateBetween(startDate.atTime(0, 0), endDate.atTime(23, 59));

        var r = steps.stream()
                .filter(s -> s.getCreatedDate().toLocalDate().getDayOfWeek().getValue() != 6 && s.getCreatedDate().toLocalDate().getDayOfWeek().getValue() != 7)
                .collect(Collectors.groupingBy(s -> s.getCreatedDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy'/'MM'/'dd")),
                        Collectors.groupingBy(s -> s.getStep().toString(),
                                Collectors.summingInt(s -> (int) s.getProduct().getVariant().getPrice()))));

        Map<String, Map<String,Integer>> r1 = new HashMap<>();

        if (comparison) {
            LocalDate previousPeriodEndDate = endDate.minusMonths(periodSpan).with(TemporalAdjusters.previousOrSame(endDate.getDayOfWeek()));
            LocalDate previousPeriodStartDate = startDate.minusMonths(periodSpan).with(TemporalAdjusters.previousOrSame(startDate.getDayOfWeek()));


            List<ProductStep> previousPeriodSteps = repository.findByCreatedDateBetween(previousPeriodStartDate.atTime(0, 0), previousPeriodEndDate.atTime(23, 59));
            r1 = previousPeriodSteps.stream()
                    .filter(s -> s.getCreatedDate().toLocalDate().getDayOfWeek().getValue() != 6 && s.getCreatedDate().toLocalDate().getDayOfWeek().getValue() != 7)
                    .collect(Collectors.groupingBy(s -> s.getCreatedDate().toLocalDate().format(DateTimeFormatter.ofPattern("MM'/'dd")),
                            Collectors.groupingBy(s -> s.getStep().toString() + " - PREVIOUS PERIOD",
                                    Collectors.summingInt(s -> (int) s.getProduct().getVariant().getPrice()))));
        }

        List<String> labels = r.keySet().stream().sorted().toList();

        return new EvolutionDto(r, r1, labels);
    }

    @Override
    public StatsDto getWorkload() {
        long cutHours = productRepository.findByNextStep(Step.CUT).stream()
                .map(Product::getVariant)
                .map(v -> repository.findAverageTimeForStep(v, Step.CUT, LocalDate.now().minusMonths(1).atStartOfDay(), LocalDate.now().atTime(23,59)))
                .map(Duration::ofNanos)
                .reduce(Duration.ZERO, Duration::plus)
                .toHours();

        long bendHours = productRepository.findByNextStep(Step.BENT).stream()
                .map(Product::getVariant)
                .map(v -> repository.findAverageTimeForStep(v, Step.BENT, LocalDate.now().minusMonths(1).atStartOfDay(), LocalDate.now().atTime(23,59)))
                .map(Duration::ofNanos)
                .reduce(Duration.ZERO, Duration::plus)
                .toHours();

        long combineHours = productRepository.findByNextStep(Step.COMBINED).stream()
                .map(Product::getVariant)
                .map(v -> repository.findAverageTimeForStep(v, Step.COMBINED, LocalDate.now().minusMonths(1).atStartOfDay(), LocalDate.now().atTime(23,59)))
                .map(Duration::ofNanos)
                .reduce(Duration.ZERO, Duration::plus)
                .toHours();

        long weldHours = productRepository.findByNextStep(Step.WELDED).stream()
                .map(Product::getVariant)
                .map(v -> repository.findAverageTimeForStep(v, Step.WELDED, LocalDate.now().minusMonths(1).atStartOfDay(), LocalDate.now().atTime(23,59)))
                .map(Duration::ofNanos)
                .reduce(Duration.ZERO, Duration::plus)
                .toHours();

        long assembleHours = productRepository.findByNextStep(Step.ASSEMBLED).stream()
                .map(Product::getVariant)
                .map(v -> repository.findAverageTimeForStep(v, Step.ASSEMBLED, LocalDate.now().minusMonths(1).atStartOfDay(), LocalDate.now().atTime(23,59)))
                .map(Duration::ofNanos)
                .reduce(Duration.ZERO, Duration::plus)
                .toHours();

        long finishHours = productRepository.findByNextStep(Step.FINISHED).stream()
                .map(Product::getVariant)
                .map(v -> repository.findAverageTimeForStep(v, Step.FINISHED, LocalDate.now().minusMonths(1).atStartOfDay(), LocalDate.now().atTime(23,59)))
                .map(Duration::ofNanos)
                .reduce(Duration.ZERO, Duration::plus)
                .toHours();

        Map<String,Integer> r = new HashMap<>();
        r.put(Step.CUT.toString(), (int) cutHours);
        r.put(Step.BENT.toString(), (int) bendHours);
        r.put(Step.COMBINED.toString(), (int) combineHours);
        r.put(Step.WELDED.toString(), (int) weldHours);
        r.put(Step.ASSEMBLED.toString(), (int) assembleHours);
        r.put(Step.FINISHED.toString(), (int) finishHours);

        List<String> labels = List.of(Step.CUT.toString(),Step.BENT.toString(),Step.COMBINED.toString(),Step.WELDED.toString(),Step.ASSEMBLED.toString(),Step.FINISHED.toString());
        return new StatsDto(r,labels, null, null);
    }
}
