package br.com.dio.product.controller;

import br.com.dio.product.dto.ProductRequest;
import br.com.dio.product.dto.ProductResponse;
import br.com.dio.product.dto.ProductUpdate;
import br.com.dio.product.model.Product;
import br.com.dio.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest)
    {
        ProductResponse response = productService.productCreate(productRequest);
        return new ResponseEntity<>((response), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts()
    {
        List<ProductResponse> allProducts = productService.getAllproducts(); // O service ja busca e converte para DTO

        return ResponseEntity.ok(allProducts); // Retornamos um unico valor com a lista dentro
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id)
    {
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductUpdate update)
    {
        ProductResponse product = productService.productUpdate(id, update);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping ("/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable Long id)
    {
        productService.productDelete(id);
        return ResponseEntity.noContent().build();
    }


}
