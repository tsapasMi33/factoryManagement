package be.tsapasmi.factorymanagement.domain.entities;

import be.tsapasmi.factorymanagement.domain.enums.Step;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStep extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Step step;

    @Column(nullable = false)
    private LocalDateTime start;

    private LocalDateTime finish;

    @Column(nullable = false)
    private Duration duration;

    @Column(nullable = false)
    private boolean finished;

    @Column(nullable = false)
    private boolean paused;
}
