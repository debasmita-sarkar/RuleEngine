package com.demo.rule.actions.impl;

import com.demo.rule.actions.IActionExecutor;
import com.demo.rule.model.PaymentRequest;
import com.demo.rule.utility.PAYSLIP_TYPE;
import com.demo.rule.utility.PackingSlipUtility;
import com.demo.rule.utility.PaymentUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Qualifier("PackingSlipGeneratorImpl")
public class PackingSlipGenerator implements IActionExecutor {
    @Autowired
    PackingSlipUtility packingSlipUtility;
    @Override
    public boolean execute(PaymentRequest request,String type) {
        if(type.equalsIgnoreCase(PAYSLIP_TYPE.ROYALTY.name())){
            return packingSlipUtility.generateRoyalSlip();
        }
        if(type.equalsIgnoreCase(PAYSLIP_TYPE.SHIPPING.name())){
            return packingSlipUtility.generateShippingSlip();
        }
        if(type.equalsIgnoreCase(PAYSLIP_TYPE.VIDEO.name())){
            return packingSlipUtility.addVideo();
        }
        return false;
    }
}
