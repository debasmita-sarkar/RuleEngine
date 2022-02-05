package com.demo.rule;

import com.demo.rule.controller.PaymentController;
import com.demo.rule.model.PaymentRequest;
import com.demo.rule.payments.PAYMENT_TYPES;
import com.demo.rule.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;



class PaymentControllerTest {
    PaymentController controller = new PaymentController();
    @Mock
    PaymentService service = Mockito.mock(PaymentService.class);
    PaymentRequest req = null;
    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(controller,"service",service);
        req = PaymentRequest.builder().paymentType(PAYMENT_TYPES.BOOK.name()).from("from").to("to").build();
    }

    @Test
    void pay() {
        controller.pay(req);
        Mockito.verify(service, Mockito.times(1)).performJobs(req);
    }
}