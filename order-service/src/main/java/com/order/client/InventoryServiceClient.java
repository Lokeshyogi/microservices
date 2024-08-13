package com.order.client;

import com.order.dto.InventoryRequestDTO;
import com.order.dto.InventoryResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "inventory-service")
public interface InventoryServiceClient {
    @GetMapping("/inventory/product/{id}")
    InventoryResponseDTO getInventoryByProductId(@PathVariable("id") Long id);

    @PutMapping("/inventory/{id}")
    InventoryResponseDTO updateInventory(@PathVariable Long id, @RequestBody InventoryRequestDTO inventoryRequestDTO);

}

