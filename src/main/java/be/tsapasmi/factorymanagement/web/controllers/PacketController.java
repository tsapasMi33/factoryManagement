package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.PacketService;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import be.tsapasmi.factorymanagement.web.mappers.PacketMapper;
import be.tsapasmi.factorymanagement.web.models.dtos.PacketDto;
import be.tsapasmi.factorymanagement.web.models.forms.PacketForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("packet")
@AllArgsConstructor
public class PacketController {

    private final PacketService service;
    private final PacketMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<PacketDto>> getPackets(@RequestParam Step currentStep) {
        return ResponseEntity.ok(mapper.toDto(service.findAllAtStep(currentStep)));
    }

    @GetMapping("/all-active")
    public ResponseEntity<List<PacketDto>> getActivePackets() {
        return ResponseEntity.ok(mapper.toDto(service.findAllActive()));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<PacketDto> getPacket(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDto(service.getOne(id)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','PACKER')")
    @PostMapping("/pack")
    public ResponseEntity<HttpStatus> createPacket(@Valid @RequestBody PacketForm form) {
        service.create(mapper.toEntity(form));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> deletePacket(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
