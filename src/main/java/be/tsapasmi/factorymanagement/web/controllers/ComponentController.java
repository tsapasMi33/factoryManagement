package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.ComponentService;
import be.tsapasmi.factorymanagement.web.mappers.ComponentMapper;
import be.tsapasmi.factorymanagement.web.models.dto.ComponentDTO;
import be.tsapasmi.factorymanagement.web.models.form.ComponentForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/component")
@AllArgsConstructor
public class ComponentController {

    private final ComponentService service;
    private final ComponentMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<ComponentDTO>> getAllComponents() {
        return ResponseEntity.ok(mapper.toDTO(service.getAll()));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ComponentDTO> getComponent(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDTO(service.getOne(id)));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createComponent(@Valid @RequestBody ComponentForm form) {
        service.create(mapper.toEntity(form));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> updateComponent(@PathVariable long id, @Valid @RequestBody ComponentForm form) {
        service.update(id, mapper.toEntity(form));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> deleteComponent(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
