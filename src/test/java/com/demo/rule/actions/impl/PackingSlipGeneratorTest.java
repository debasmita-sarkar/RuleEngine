package com.demo.rule.actions.impl;

import com.demo.rule.model.PaymentRequest;
import com.demo.rule.payments.PAYMENT_TYPES;
import com.demo.rule.utility.PackingSlipUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class PackingSlipGeneratorTest {
    PackingSlipGenerator slipGenerator = new PackingSlipGenerator();
    PaymentRequest request=null;
    PackingSlipUtility utility = Mockito.mock(PackingSlipUtility.class);
    @BeforeEach
    void setUp() {
        when(utility.addVideo()).thenReturn(true);
        when(utility.generateRoyalSlip()).thenReturn(true);
        when(utility.generateShippingSlip()).thenReturn(true);
        ReflectionTestUtils.setField(slipGenerator,"packingSlipUtility",utility);
        request = PaymentRequest.builder().paymentType(PAYMENT_TYPES.PHYSICAL_PRODUCT.name()).from("").build();
    }

    @Test
    void executeForShippingSuccess() {
        assertTrue(slipGenerator.execute(request,"shipping"));
    }
    @Test
    void executeForRoyaltySuccess() {
        assertTrue(slipGenerator.execute(request,"royalty"));
    }
}