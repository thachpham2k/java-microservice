package com.microservice.inventoryservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.microservice.inventoryservice.model.Inventory;
import com.microservice.inventoryservice.repository.IInventoryRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final IInventoryRepository inventoryRepository;
    @Override
    public void run(String... args) throws Exception {
        Inventory inventory = new Inventory();
        inventory.setSkuCode("iphone_15");
        inventory.setQuantity(100);

        Inventory inventory1 = new Inventory();
        inventory1.setSkuCode("iphone_15_promax");
        inventory1.setQuantity(0);

        inventoryRepository.save(inventory);
        inventoryRepository.save(inventory1);

    }
}