package com.demo.rule.service;

import com.demo.rule.actions.ActionFactory;
import com.demo.rule.actions.impl.MembershipActor;
import com.demo.rule.actions.impl.PackingSlipGenerator;
import com.demo.rule.actions.impl.PaymentExecutor;
import com.demo.rule.model.PaymentRequest;
import com.demo.rule.payments.PAYMENT_TYPES;
import com.demo.rule.utility.PAYSLIP_TYPE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class PaymentServiceTest {
    PaymentService service = new PaymentService();
    PaymentRequest request=null;
    ActionFactory factory = Mockito.mock(ActionFactory.class);
    PackingSlipGenerator slipGenerator = Mockito.mock(PackingSlipGenerator.class);
    PaymentExecutor paymentExecutor = Mockito.mock(PaymentExecutor.class);
    MembershipActor membershipActor = Mockito.mock(MembershipActor.class);


    @BeforeEach
    void setUp() {
        //Mock actions
        when(slipGenerator.execute(any(PaymentRequest.class),anyString()))
            .thenReturn(true);
        when(paymentExecutor.execute(any(PaymentRequest.class),anyString()))
            .thenReturn(true);
        when(membershipActor.execute(any(PaymentRequest.class),anyString()))
            .thenReturn(true);
        //Mock executors
        when(factory.getInstance(PAYSLIP_TYPE.SHIPPING.name()))
            .thenReturn(slipGenerator);
        when(factory.getInstance(PAYSLIP_TYPE.ROYALTY.name()))
            .thenReturn(slipGenerator);
        when(factory.getInstance(""))
            .thenReturn(paymentExecutor);
        ReflectionTestUtils.setField(service,"factory",factory);
        request = PaymentRequest.builder().paymentType(PAYMENT_TYPES.PHYSICAL_PRODUCT.name()).from("").build();
    }

    @Test
    void performJobs_PhysicalProduct_Success() {
        assertTrue(service.performJobs(request));
    }
    @Test
    void performJobs_Book_Success() {
        request = PaymentRequest.builder().paymentType(PAYMENT_TYPES.BOOK.name()).from("").build();
        assertTrue(service.performJobs(request));
    }
    @Test
    void performJobs_NullRequest() {
        assertFalse(service.performJobs(null));

    }
    @Test
    void performJobs_RequestTypeNull() {
        assertFalse(service.performJobs(null));

    }
}