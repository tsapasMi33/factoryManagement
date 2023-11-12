package be.tsapasmi.factorymanagement.domain.entities;

import be.tsapasmi.factorymanagement.domain.enums.DeliveryPreference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

    private LocalDate orderDate;

    private LocalDate plannedDeliveryDate;

    @Enumerated(EnumType.STRING)
    private DeliveryPreference deliveryPreference;

    @OneToMany(mappedBy = "order")
    private List<Product> products;

    @ManyToOne
    private Client client;
}
