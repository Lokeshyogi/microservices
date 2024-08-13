package com.erm.controller;

import com.erm.dto.InventoryResponseDTO;
import com.erm.dto.InventoryRequestDTO;
import com.erm.model.Inventory;
import com.erm.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class InventoryServiceControllerImpl implements InventoryServiceController {


    private final IInventoryService iInventoryService;

    @Autowired
    public InventoryServiceControllerImpl(IInventoryService iInventoryService) {
        this.iInventoryService = iInventoryService;
    }

    @Override
    @PostMapping("/")
    public InventoryResponseDTO createInventory(@RequestBody InventoryRequestDTO inventoryRequestDTO) {
        return iInventoryService.addInventory(inventoryRequestDTO);
    }

    @Override
    @PutMapping("/{id}")
    public InventoryResponseDTO updateInventory(@PathVariable Long id, @RequestBody InventoryRequestDTO inventoryRequestDTO) {
        return iInventoryService.updateInventory(id, inventoryRequestDTO);
    }

    @Override
    @GetMapping("/{id}")
    public Optional<InventoryResponseDTO> getInventoryById(@PathVariable Long id) {
        return iInventoryService.getInventoryById(id);
    }

    @Override
    @GetMapping("/product/{id}")
    public Optional<InventoryResponseDTO> getInventoryByProductId(@PathVariable Long id) {
        return iInventoryService.getInventoryByProductId(id);
    }

    @Override
    @GetMapping("/")
    public List<InventoryResponseDTO> getAllInventory() {
        return iInventoryService.getAllInventory();
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Long id) {
        iInventoryService.deleteInventory(id);
    }
}

