package com.microservice.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.dto.ProductResponse;
import com.microservice.productservice.model.Product;
import com.microservice.productservice.repository.IProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final IProductRepository productRepository;
    
    public void createProduct(ProductRequest productRequest) {
        // Create new product
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .categories(productRequest.getCategories())
                .tags(productRequest.getTags())
                .imageLinks(productRequest.getImageLinks())
                .build();
        // Save new product to database
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    public void updateProduct(String productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        // Update product information
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setCategories(productRequest.getCategories());
        product.setTags(productRequest.getTags());
        product.setImageLinks(productRequest.getImageLinks());
        // Save updated Product to database
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public void deleteProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        // Delete product has productId
        productRepository.delete(product);
        log.info("Product {} is deleted", product.getId());
    }

    public ProductResponse getProductByProductId(String productId) {
        return mapToProductResponse(productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId)));
    }

    public List<ProductResponse> getProductsByTags(List<String> tags) {
        List<Product> products = productRepository.findByTagsIn(tags);

        return products.stream().map(this::mapToProductResponse).toList();
    }

    public List<ProductResponse> getProductsByCatogories(List<String> catogories) {
        List<Product> products = productRepository.findByCategoriesIn(catogories);

        return products.stream().map(this::mapToProductResponse).toList();
    }
     
    /* ===== function ===== */ 

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categories(product.getCategories())
                .tags(product.getTags())
                .imageLinks(product.getImageLinks())
                .build();
    }
}
