package com.microservice.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.orderservice.client.InventoryClient;
import com.microservice.orderservice.dto.InventoryResponse;
import com.microservice.orderservice.dto.OrderLineItemsDto;
import com.microservice.orderservice.dto.OrderRequest;
import com.microservice.orderservice.model.Order;
import com.microservice.orderservice.model.OrderLineItems;
import com.microservice.orderservice.repository.IOrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final IOrderRepository orderRepository;
    // private final WebClient.Builder webClientBuilder;
    private final InventoryClient inventoryClient;
    
    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList());

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        // InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
        //                     .uri("http://inventory-service/api/inventory?", 
        //                         uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
        //                     .retrieve()
        //                     .bodyToMono(InventoryResponse[].class)
        //                     .block();
        log.info("checking inventory");

        // boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(inventoryResponse -> inventoryResponse.isInStock());
        boolean allProductsInStock = inventoryClient.checkStock(skuCodes)
                .stream()
                .allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            orderRepository.save(order);    
            return "Order Placed";
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try it again");
        }
        
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
