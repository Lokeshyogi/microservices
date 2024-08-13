package com.payment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private Long id;
    private Long orderId;
    private Long userId;
    private Double amount;
    private String status;

    // getters and setters

}
