package be.tsapasmi.factorymanagement.domain.entities;

import be.tsapasmi.factorymanagement.domain.enums.Step;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductState extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Step step;

    @Column(nullable = false)
    private LocalDateTime start;

    private LocalDateTime end;

    private int minutesPassed;

    @ManyToOne
    private User user;
}
