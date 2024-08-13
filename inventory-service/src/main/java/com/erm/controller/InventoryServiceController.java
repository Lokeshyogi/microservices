package com.erm.controller;


import com.erm.dto.InventoryResponseDTO;
import com.erm.dto.InventoryRequestDTO;
import com.erm.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryServiceController {
    InventoryResponseDTO createInventory(InventoryRequestDTO inventoryRequestDTO);
    InventoryResponseDTO updateInventory(Long id, InventoryRequestDTO inventoryRequestDTO);
    Optional<InventoryResponseDTO> getInventoryById(Long id);
    Optional<InventoryResponseDTO> getInventoryByProductId(Long id);
    List<InventoryResponseDTO> getAllInventory();
    void deleteInventory(Long id);
}
