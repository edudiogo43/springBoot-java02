package com.newapi.newApi.controllers;

import java.util.List;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newapi.newApi.models.ProductModel;
import com.newapi.newApi.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    ProductService productService;
    
    @GetMapping
    public ResponseEntity<Object> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") UUID id) {
        ProductModel foundIt = productService.findById(id);
        return foundIt != null ? ResponseEntity.status(HttpStatus.OK).body(foundIt) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
    }

    @PostMapping
    public ResponseEntity<ProductModel> saveProduct(@RequestBody ProductModel productModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id) {
        ProductModel foundIt = productService.findById(id);

        // In case of the product is not found return to the user with 404
        if(foundIt == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found !");
        }

        // In fact now delete the product by its Id
        productService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucecssfuly!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id,
                                                @RequestBody ProductModel productModel) {
        
        productModel.setId(id);
        var updated = productService.save(productModel);                                         

        if(updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found !");
        }

        return ResponseEntity.status(HttpStatus.OK).body(productModel);
    }

}
