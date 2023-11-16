package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.PacketService;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import be.tsapasmi.factorymanagement.web.mappers.PacketMapper;
import be.tsapasmi.factorymanagement.web.models.dto.PacketDTO;
import be.tsapasmi.factorymanagement.web.models.form.PacketForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("packet")
@AllArgsConstructor
public class PacketController {

    private final PacketService service;
    private final PacketMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<PacketDTO>> getPackets(@RequestParam(required = false) Step currentStep) {
        return ResponseEntity.ok(mapper.toDTO(service.findAllAtStep(currentStep)));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<PacketDTO> getPacket(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDTO(service.getOne(id)));
    }

    @PostMapping("/pack")
    public ResponseEntity<HttpStatus> createPacket(@Valid @RequestBody PacketForm form) {
        service.create(mapper.toEntity(form));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> updatePacket(@PathVariable long id, @Valid @RequestBody PacketForm form) {
        service.update(id, mapper.toEntity(form));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> deletePacket(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
