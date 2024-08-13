package com.payment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class PaymentRequest {
    private Long orderId;
    private Long userId;
    private Double amount;

    // getters and setters
}

