package com.facundom.payGoal.controller;

import com.facundom.payGoal.model.ProductDTO;
import com.facundom.payGoal.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;


    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) throws Exception{
        try {
            service.create(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product created");
        }catch (ResponseStatusException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id){
        try {
            ProductDTO productDTO = service.read(id);
            return ResponseEntity.ok(productDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public List<ProductDTO> listProducts(){
        return service.getAllSortedByPrice();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) throws  Exception{
        productDTO.setProductId(id);
        service.update(productDTO);
        return ResponseEntity.ok("Product updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) throws Exception {
        try {
            service.delete(id);
            return ResponseEntity.ok("Product deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
