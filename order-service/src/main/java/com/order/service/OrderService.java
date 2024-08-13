package com.order.service;

import com.order.client.InventoryServiceClient;
import com.order.client.ProductServiceClient;
import com.order.client.UserServiceClient;
import com.order.client.WebClientConfig;
import com.order.dto.*;
import com.order.exception.OrderServiceException;
import com.order.model.Order;
import com.order.repository.OrderServiceRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderServiceRepository orderRepository;
    private final WebClientConfig webClientConfig;
    private final UserServiceClient userServiceClient;
    private final ProductServiceClient productServiceClient;
    private final InventoryServiceClient inventoryServiceClient;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderService(OrderServiceRepository orderRepository, WebClientConfig webClientConfig, UserServiceClient userServiceClient, ProductServiceClient productServiceClient, InventoryServiceClient inventoryServiceClient, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.webClientConfig = webClientConfig;
        this.userServiceClient = userServiceClient;
        this.productServiceClient = productServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
        this.modelMapper = modelMapper;
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {
        // Communication using feign client
        logger.info("Validate user exists or not");
        Optional<UserDto> user = Optional.ofNullable(userServiceClient.getUserById(orderRequest.getUserId()));
        user.orElseThrow(()-> new OrderServiceException("User not exists"));
        logger.info("Validate product exists or not");
        Optional<Product> productById = Optional.ofNullable(productServiceClient.getProductById(orderRequest.getProductId()));
        productById.orElseThrow(()->new OrderServiceException(String.format("product with product id: %d not exists",orderRequest.getProductId())));
        InventoryResponseDTO inventoryByProductId = inventoryServiceClient.getInventoryByProductId(orderRequest.getProductId());
        InventoryRequestDTO inventoryRequestDTO = new InventoryRequestDTO();
        inventoryRequestDTO.setProductId(orderRequest.getProductId());
        int quantity = inventoryByProductId.getQuantity() - orderRequest.getQuantity();
        if (quantity < 0)
            throw new OrderServiceException("product out of stock");
        inventoryRequestDTO.setQuantity(quantity);
        inventoryServiceClient.updateInventory(inventoryByProductId.getId(), inventoryRequestDTO);
        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setProductId(orderRequest.getProductId());
        order.setQuantity(orderRequest.getQuantity());
        Double price = orderRequest.getQuantity() * productById.get().getPrice();
        order.setTotalPrice(price);
        order.setStatus("CREATED");
        Order order1 = orderRepository.save(order);
        OrderResponse orderResponse = new OrderResponse();
        modelMapper.map(order, orderResponse);
        orderResponse.setProduct(productById.get());
        return orderResponse;

//        // Communication using web client
//        UserDto user2 = webClientConfig.webClientConfigBuilder()
//                .get()
//                .uri("http://localhost:8080/users/1")
//                .retrieve()
//                .bodyToMono(UserDto.class)
//                .block();
//        System.out.println(user2);

    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public OrderResponse updateOrderStatus(Long id, String status) {
        Optional<Order> orderById = Optional.ofNullable(getOrderById(id));
        orderById.orElseThrow(()-> new OrderServiceException("order not exisst"));
        orderById.get().setStatus(status);
        Order save = orderRepository.save(orderById.get());
        Product productById = productServiceClient.getProductById(orderById.get().getProductId());
        OrderResponse orderResponse  = new OrderResponse();
        modelMapper.map(save,orderResponse);
        orderResponse.setProduct(productById);
        return orderResponse;
    }
}

