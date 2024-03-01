package com.microservice.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.inventoryservice.dto.InventoryDetailResponse;
import com.microservice.inventoryservice.dto.InventoryRequest;
import com.microservice.inventoryservice.dto.InventoryResponse;
import com.microservice.inventoryservice.model.Inventory;
import com.microservice.inventoryservice.repository.IInventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final IInventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> quantityInStock(List<String> skuCodes) {
        log.info("Check inventory");
        return inventoryRepository.findBySkuCodeIn(skuCodes)
                .stream()
                .map(inventory -> InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .quantity(inventory.getQuantity())
                        .build())
                .toList();
    }

    public List<InventoryDetailResponse> getAllInventories() {
        return inventoryRepository.findAll()
                .stream()
                .map(inventory -> InventoryDetailResponse.builder()
                        .id(inventory.getId())
                        .skuCode(inventory.getSkuCode())
                        .quantity(inventory.getQuantity())
                        .build())
                .toList();
    }

    public void createInventory(InventoryRequest inventoryRequest) {
        // Create new Inventory
        Inventory inventory = Inventory.builder()
                        .skuCode(inventoryRequest.getSkuCode())
                        .quantity(inventoryRequest.getQuantity())
                        .build();
        // Save new Inventory
        inventoryRepository.save(inventory);
        log.info("Inventory {} is saved", inventory.getId());
    }

    public void updateInventory(Long inventoryId, InventoryRequest inventoryRequest) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + inventoryId));
        // Update information of Inventory
        inventory.setSkuCode(inventoryRequest.getSkuCode());
        inventory.setQuantity(inventoryRequest.getQuantity());
        // Save updated Inventory
        inventoryRepository.save(inventory);
        log.info("Inventory {} is updated", inventory.getId());
    }

    public void deleteInventory(Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + inventoryId));
        // Delete Inventory
        inventoryRepository.delete(inventory);
        log.info("Inventory {} is deleted", inventory.getId());
    }
}
