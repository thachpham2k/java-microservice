package com.microservice.productservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservice.productservice.model.Product;

public interface IProductRepository extends MongoRepository<Product, String> {
    Product findBySkuCode(String skucode);
    // @Query("{'categories': { $in: ?0 }}")
    List<Product> findByCategoriesIn(List<String> categories);
    // @Query("{'tags': { $in: ?0 }}")
    List<Product> findByTagsIn(List<String> tags);
}
