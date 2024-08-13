package com.erm.service;


import com.erm.client.ProductServiceClient;
import com.erm.client.UserServiceClient;
import com.erm.dto.InventoryResponseDTO;
import com.erm.dto.InventoryRequestDTO;
import com.erm.dto.Product;
import com.erm.exception.InventoryServiceException;
import com.erm.model.Inventory;
import com.erm.repository.InventoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements IInventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductServiceClient productServiceClient;
    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public InventoryResponseDTO addInventory(InventoryRequestDTO inventoryRequestDTO) {
        Inventory inventory = new Inventory();
        InventoryResponseDTO inventoryResponseDTO = new InventoryResponseDTO();
        modelMapper.map(inventoryRequestDTO, inventory);
        Inventory save = inventoryRepository.save(inventory);
        modelMapper.map(save,inventoryResponseDTO);
        Product productById = productServiceClient.getProductById(inventoryRequestDTO.getProductId());
        inventoryResponseDTO.setProduct(productById);
        return inventoryResponseDTO;
    }

    @Override
    public InventoryResponseDTO updateInventory(Long id, InventoryRequestDTO inventoryRequestDTO) {
        Optional<Inventory> inventoryById = inventoryRepository.findById(id);
        InventoryResponseDTO inventoryResponseDTO = new InventoryResponseDTO();
        inventoryById.orElseThrow(() -> new InventoryServiceException("Inventory not found"));
        modelMapper.map(inventoryRequestDTO,inventoryById.get());
        inventoryRepository.save(inventoryById.get());
        modelMapper.map(inventoryById.get(),inventoryResponseDTO);
        Product productById = productServiceClient.getProductById(inventoryRequestDTO.getProductId());
        inventoryResponseDTO.setProduct(productById);
        return inventoryResponseDTO;
    }

    @Override
    public Optional<InventoryResponseDTO> getInventoryById(Long id) {
        Optional<InventoryResponseDTO> inventoryResponseDTO = Optional.of(new InventoryResponseDTO());
        Product productById = productServiceClient.getProductById(id);
        Optional<Inventory> byId = inventoryRepository.findById(id);
        byId.orElseThrow(()->new InventoryServiceException("inventory not exists"));
        modelMapper.map(byId.get(),inventoryResponseDTO.get());
        inventoryResponseDTO.get().setProduct(productById);
        return inventoryResponseDTO;
    }

    @Override
    public Optional<InventoryResponseDTO> getInventoryByProductId(Long id) {
        Optional<Inventory> byId = inventoryRepository.findByProductId(id);
        Optional<InventoryResponseDTO> inventoryResponseDTO = Optional.of(new InventoryResponseDTO());
        byId.orElseThrow(()->new InventoryServiceException("inventory not exists"));
        modelMapper.map(byId.get(),inventoryResponseDTO.get());
        Product productById = productServiceClient.getProductById(id);
        inventoryResponseDTO.get().setProduct(productById);
        return inventoryResponseDTO;
    }

    @Override
    public List<InventoryResponseDTO> getAllInventory() {
        List<Inventory> all = inventoryRepository.findAll();
        InventoryResponseDTO inventoryResponseDTO = new InventoryResponseDTO();
        modelMapper.map(all,inventoryResponseDTO);
        return List.of((InventoryResponseDTO) inventoryRepository);
    }

    @Override
    public void deleteInventory(Long id) {
        Optional<Inventory> inventoryById = inventoryRepository.findById(id);;
        inventoryById.orElseThrow(() -> new InventoryServiceException("Inventory not found"));
        inventoryRepository.deleteById(id);
    }
}
