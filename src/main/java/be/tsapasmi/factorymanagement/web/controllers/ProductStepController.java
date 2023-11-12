package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.ProductStepService;
import be.tsapasmi.factorymanagement.web.mappers.ProductStepMapper;
import be.tsapasmi.factorymanagement.web.models.dto.ProductStepDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product-step")
@AllArgsConstructor
public class ProductStepController {

    private final ProductStepService service;
    private final ProductStepMapper mapper;

    @GetMapping("/product/{id:^[0-9]+$}")
    public ResponseEntity<List<ProductStepDTO>> getAllProductStepsForProduct(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDTO(service.getAllStepsForProduct(id)));
    }
}
