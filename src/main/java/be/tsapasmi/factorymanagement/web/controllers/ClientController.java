package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.ClientService;
import be.tsapasmi.factorymanagement.web.mappers.ClientMapper;
import be.tsapasmi.factorymanagement.web.models.dtos.ClientDto;
import be.tsapasmi.factorymanagement.web.models.forms.ClientForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ClientDto> getClient(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDto(service.getOne(id)));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createClient(@Valid @RequestBody ClientForm form) {
        service.create(mapper.toEntity(form));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

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
