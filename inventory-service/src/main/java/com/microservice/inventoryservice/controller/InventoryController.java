package com.microservice.inventoryservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.microservice.inventoryservice.dto.InventoryDetailResponse;
import com.microservice.inventoryservice.dto.InventoryRequest;
import com.microservice.inventoryservice.dto.InventoryResponse;
import com.microservice.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
    private final InventoryService inventoryService;
    // http://localhost:8081/api/inventory/check?skuCode=iphone-13&skuCode=iphone13-red
    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> quantityInStock(@RequestParam("skuCode") List<String> skuCodes) {
        log.info("Received inventory check request for skuCodes: {}", skuCodes);
        return inventoryService.quantityInStock(skuCodes);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createInventory(@RequestBody InventoryRequest inventoryRequest) {
        inventoryService.createInventory(inventoryRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryDetailResponse> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @PutMapping("/{inventoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@PathVariable Long inventoryId, @RequestBody InventoryRequest inventoryRequest) {
        try {
            inventoryService.updateInventory(inventoryId, inventoryRequest);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found." + e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update inventory: " + e.getMessage(), e);
        }
    }

    @DeleteMapping("/{inventoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInventory(@PathVariable Long inventoryId) {
        try {
            inventoryService.deleteInventory(inventoryId);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found." + e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete Inventory: " + e.getMessage(), e);
        }
    }
}
