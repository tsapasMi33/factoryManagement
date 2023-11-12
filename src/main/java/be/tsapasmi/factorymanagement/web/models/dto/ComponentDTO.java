package be.tsapasmi.factorymanagement.web.models.dto;

import be.tsapasmi.factorymanagement.domain.enums.Material;

import java.io.Serializable;

public record ComponentDTO(long id, String name, String type, Material material, Integer thickness, Integer length, Integer width, int qty, double price) implements Serializable {
}
