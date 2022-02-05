package com.demo.rule.actions.impl;

import com.demo.rule.actions.IActionExecutor;
import com.demo.rule.model.PaymentRequest;
import com.demo.rule.model.User;
import com.demo.rule.service.PaymentService;
import com.demo.rule.utility.PaymentUtility;
import com.demo.rule.utility.RECEIVER_TYPE;
import com.demo.rule.utility.UserCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Qualifier("PaymentExecutorImpl")
public class PaymentExecutor implements IActionExecutor {
    @Autowired
    PaymentUtility paymentUtility;

    @Override
    public boolean execute(PaymentRequest request,String type) {
        if(type.equalsIgnoreCase(RECEIVER_TYPE.AGENT.name())){
            //TODO get agent Details from user details
            User user = UserCache.get(request.getFrom());
            User agent = UserCache.get(user.getAgentID());
            return paymentUtility.pay(user,agent);
        }
        return true;
    }
}
