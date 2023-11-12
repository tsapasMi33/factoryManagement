package be.tsapasmi.factorymanagement.domain.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends BaseEntity {

    private String name;

    private String companyType;

    @Embedded
    private Address address;

    private int discountPercentage;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;
}

