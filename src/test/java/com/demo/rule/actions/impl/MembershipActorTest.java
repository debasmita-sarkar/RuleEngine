package com.demo.rule.actions.impl;

import com.demo.rule.model.PaymentRequest;
import com.demo.rule.payments.PAYMENT_TYPES;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembershipActorTest {
    MembershipActor membershipActor = new MembershipActor();
    PaymentRequest request=null;
    @BeforeEach
    void setUp() {
        request = PaymentRequest.builder().paymentType(PAYMENT_TYPES.PHYSICAL_PRODUCT.name()).from("").build();
    }
    @Test
    void executeActivateSuccess() {
        assertTrue(membershipActor.execute(request,"activate"));
    }
    @Test
    void executeUpgradeSuccess() {
        assertTrue(membershipActor.execute(request,"upgrade"));
    }
}