package com.demo.rule.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentRequest {
    String paymentType;
    String to;
    String from;
}
