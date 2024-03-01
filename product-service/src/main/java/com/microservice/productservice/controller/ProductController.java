package com.microservice.productservice.controller;

import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.dto.ProductResponse;
import com.microservice.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable String productId, @RequestBody ProductRequest productRequest) {
        try {
            productService.updateProduct(productId, productRequest);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found." + e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update product: " + e.getMessage(), e);
        }
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable String productId) {
        try {
            productService.deleteProduct(productId);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found." + e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete product: " + e.getMessage(), e);
        }
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getByProductId(@PathVariable String productId) {
        return productService.getProductByProductId(productId);
    }

    @GetMapping("/skucode")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getBySkuCode(@RequestParam("skucode") String skucode) {
        return productService.getProductBySkuCode(skucode);
    }

    @GetMapping("/tags")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllByTags(@RequestParam("tag") List<String> tags) {
        return productService.getProductsByTags(tags);
    }

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllByCatogories(@RequestParam("category") List<String> catogories) {
        return productService.getProductsByCatogories(catogories);
    }

}