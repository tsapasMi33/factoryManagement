package be.tsapasmi.factorymanagement.web.models.dto;

import be.tsapasmi.factorymanagement.domain.enums.DeliveryPreference;
import be.tsapasmi.factorymanagement.domain.entities.Packet;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.Order;
import be.tsapasmi.factorymanagement.domain.entities.Client;
import be.tsapasmi.factorymanagement.domain.entities.Address;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link Packet}
 */
public record PacketDTO(Long id, String code, List<ProductDto> products, ProductDto.OrderDto order) implements Serializable {
    /**
     * DTO for {@link Product}
     */
    public record ProductDto(Long id, String comments) implements Serializable {
        /**
         * DTO for {@link Order}
         */
        public record OrderDto(Long id, LocalDate orderDate, LocalDate plannedDeliveryDate,
                               DeliveryPreference deliveryPreference, ClientDto client) implements Serializable {
            /**
             * DTO for {@link Client}
             */
            public record ClientDto(Long id, String name, String companyType,
                                    AddressDto address) implements Serializable {
                /**
                 * DTO for {@link Address}
                 */
                public record AddressDto(String street, String number, String city, String cp,
                                         String country) implements Serializable {
                }
            }
        }
    }
}