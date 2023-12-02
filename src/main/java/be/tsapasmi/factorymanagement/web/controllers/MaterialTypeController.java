package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.MaterialTypeService;
import be.tsapasmi.factorymanagement.web.mappers.MaterialTypeMapper;
import be.tsapasmi.factorymanagement.web.models.dtos.MaterialTypeDto;
import be.tsapasmi.factorymanagement.web.models.forms.MaterialTypeForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material-type")
@AllArgsConstructor
public class MaterialTypeController {

    private final MaterialTypeService service;
    private final MaterialTypeMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<MaterialTypeDto>> getAllMaterialTypes() {
        return ResponseEntity.ok(mapper.toDto(service.getAll()));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<MaterialTypeDto> getMaterialType(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDto(service.getOne(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<HttpStatus> createMaterialType(@Valid @RequestBody MaterialTypeForm form) {
        service.create(mapper.toEntity(form));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> updateMaterialType(@PathVariable long id, @Valid @RequestBody MaterialTypeForm form) {
        service.update(id, mapper.toEntity(form));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> deleteMaterialType(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
