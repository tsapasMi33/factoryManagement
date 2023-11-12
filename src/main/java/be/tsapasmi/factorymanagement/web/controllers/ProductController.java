//package be.tsapasmi.factorymanagement.web.controllers;
//
//import be.tsapasmi.factorymanagement.bl.interfaces.ProductService;
//import be.tsapasmi.factorymanagement.web.models.dto.ProductVariantDTO;
//import be.tsapasmi.factorymanagement.web.models.form.ProductVariantForm;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/product")
//@AllArgsConstructor
//public class ProductController {
//
//    private final ProductService service;
//    private final ProductMapper mapper;
//
//    @GetMapping("/all")
//    public ResponseEntity<List<ProductVariantDTO>> getAllProductVariants() {
//        return ResponseEntity.ok(mapper.toDTO(service.getAll()));
//    }
//
//    @GetMapping("/{id:^[0-9]+$}")
//    public ResponseEntity<ProductVariantDTO> getProductVariant(@PathVariable long id) {
//        return ResponseEntity.ok(mapper.toDTO(service.getOne(id)));
//    }
//
//    @PostMapping()
//    public ResponseEntity<HttpStatus> createProductVariant(@Valid @RequestBody ProductVariantForm form) {
//        service.create(mapper.toEntity(form));
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @PutMapping("/{id:^[0-9]+$}")
//    public ResponseEntity<HttpStatus> updateProductVariant(@PathVariable long id, @Valid @RequestBody ProductVariantForm form) {
//        service.update(id, mapper.toEntity(form));
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{id:^[0-9]+$}")
//    public ResponseEntity<HttpStatus> deleteProductVariant(@PathVariable long id) {
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//}
