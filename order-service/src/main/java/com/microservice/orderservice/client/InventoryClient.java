package com.microservice.orderservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.orderservice.dto.InventoryResponse;

import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "inventory-service")
@Retry(name = "inventory")
public interface InventoryClient {
    @GetMapping("/api/inventory")
    List<InventoryResponse> checkStock(@RequestParam List<String> skuCode);
}