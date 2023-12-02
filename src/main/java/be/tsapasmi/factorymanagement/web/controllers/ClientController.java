package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.ClientService;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import be.tsapasmi.factorymanagement.web.mappers.ClientMapper;
import be.tsapasmi.factorymanagement.web.models.dtos.ClientDto;
import be.tsapasmi.factorymanagement.web.models.forms.ClientForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

    private final ClientService service;
    private final ClientMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok(mapper.toDto(service.getAll()));
    }

    @GetMapping("/all-active")
    public ResponseEntity<List<ClientDto>> getAllActiveClients(@RequestParam(required = false)Step productsAtStep,
                                                               @RequestParam(required = false)Step productsAtNextStep) {
        return ResponseEntity.ok(mapper.toDto(service.getAllActiveClients(productsAtStep,productsAtNextStep)));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ClientDto> getClient(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDto(service.getOne(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<HttpStatus> createClient(@Valid @RequestBody ClientForm form) {
        service.create(mapper.toEntity(form));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> updateClient(@PathVariable long id,@Valid @RequestBody ClientForm form) {
        service.update(id, mapper.toEntity(form));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
