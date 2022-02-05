package com.demo.rule.service;

import com.demo.rule.actions.ActionFactory;
import com.demo.rule.actions.IActionExecutor;
import com.demo.rule.actions.impl.PackingSlipGenerator;
import com.demo.rule.model.PaymentRequest;
import com.demo.rule.payments.PAYMENT_TYPES;
import com.demo.rule.utility.Constant;
import com.demo.rule.utility.MEMBERSHIP_ACTION_TYPE;
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
        }else if(request.getPaymentType().compareTo(PAYMENT_TYPES.MEMBERSHIP_ACTIVATE.name()) ==0){
            updateMemberState(request, MEMBERSHIP_ACTION_TYPE.ACTIVATE);
        }else if(request.getPaymentType().compareTo(PAYMENT_TYPES.MEMBERSHIP_UPGRADE.name()) ==0){
            updateMemberState(request, MEMBERSHIP_ACTION_TYPE.UPGRADE);
        }else if(request.getPaymentType().compareTo(PAYMENT_TYPES.VIDEO.name()) ==0){
            IActionExecutor executor = factory.getInstance(PAYSLIP_TYPE.VIDEO.name());
            return executor.execute(request,PAYSLIP_TYPE.VIDEO.name());
        }
        return result;
    }

    private void updateMemberState(PaymentRequest request, MEMBERSHIP_ACTION_TYPE upgrade) {
        IActionExecutor executor = factory.getInstance(MEMBERSHIP_ACTION_TYPE.UPGRADE.toString());
        executor.execute(request, upgrade.toString());
    }

    private boolean executePhysicalProduct(PaymentRequest request, boolean result, PAYSLIP_TYPE shipping) {
        IActionExecutor executor = factory.getInstance(shipping.name());
        result = result && (executor.execute(request, shipping.name()));
        executor = factory.getInstance(Constant.EMPTY_STRING);
        result = result && executor.execute(request, RECEIVER_TYPE.AGENT.name());
        return result;
    }
}
