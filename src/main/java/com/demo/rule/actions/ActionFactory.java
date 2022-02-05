package com.demo.rule.actions;

import com.demo.rule.actions.impl.PackingSlipGenerator;
import com.demo.rule.actions.impl.PaymentExecutor;
import com.demo.rule.model.PaymentRequest;
import com.demo.rule.payments.PAYMENT_TYPES;
import com.demo.rule.utility.PAYSLIP_TYPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ActionFactory {

    @Autowired
    @Qualifier("PackingSlipGeneratorImpl")
    private PackingSlipGenerator slipgenerator;

    @Autowired
    @Qualifier("PaymentExecutorImpl")
    private PaymentExecutor paymentExecutor;

    public IActionExecutor getInstance(String type) {
        if(type == null || type.isEmpty()){
            return paymentExecutor;
        }
        if (type.equals(PAYSLIP_TYPE.SHIPPING.name()) || type.equals(PAYSLIP_TYPE.ROYALTY.name())) {
            return slipgenerator;
        }
        return paymentExecutor;
    }
}
