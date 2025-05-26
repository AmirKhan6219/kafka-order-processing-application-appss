package com.shoponline.product.controller;

import com.shoponline.product.dto.ProductDto;
import com.shoponline.product.dto.ProductPurchaseRequestDto;
import com.shoponline.product.dto.ProductPurchaseResponse;
import com.shoponline.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody @Valid ProductDto productDto
    ){
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequestDto> ProductPurchaseRequest
    ) {
        return ResponseEntity.ok(productService.purchaseProducts(ProductPurchaseRequest));
    }


    @GetMapping("/id/{product-id}")
    public ResponseEntity<ProductDto> findById(
            @PathVariable("product-id") long productId
    ) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping("/name/{product-name}")
    public ResponseEntity<List<ProductDto>> findByName(
            @PathVariable("product-name") String productName
    ) {
        return ResponseEntity.ok(productService.findByName(productName));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

}
