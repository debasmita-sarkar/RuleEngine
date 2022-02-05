package com.demo.rule.service;

import com.demo.rule.actions.ActionFactory;
import com.demo.rule.actions.IActionExecutor;
import com.demo.rule.actions.impl.PackingSlipGenerator;
import com.demo.rule.model.PaymentRequest;
import com.demo.rule.payments.PAYMENT_TYPES;
import com.demo.rule.utility.Constant;
import com.demo.rule.utility.PAYSLIP_TYPE;
import com.demo.rule.utility.RECEIVER_TYPE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {
    @Autowired
    ActionFactory factory;

    public boolean performJobs(PaymentRequest request) {
        if(request == null ){
            log.warn("Request is null. Hence no action will be taken.");
            return false;
        }
        boolean result =true;
        if(request.getPaymentType().compareTo(PAYMENT_TYPES.PHYSICAL_PRODUCT.name()) ==0){
            result = executePhysicalProduct(request, result, PAYSLIP_TYPE.SHIPPING);
        }else if(request.getPaymentType().compareTo(PAYMENT_TYPES.BOOK.name()) ==0){
            result = executePhysicalProduct(request, result, PAYSLIP_TYPE.ROYALTY);
        }
        return result;
    }

    private boolean executePhysicalProduct(PaymentRequest request, boolean result, PAYSLIP_TYPE shipping) {
        IActionExecutor executor = factory.getInstance(shipping.name());
        result = result && (executor.execute(request, shipping.name()));
        executor = factory.getInstance(Constant.EMPTY_STRING);
        result = result && executor.execute(request, RECEIVER_TYPE.AGENT.name());
        return result;
    }
}
