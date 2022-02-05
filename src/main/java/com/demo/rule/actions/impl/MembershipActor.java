package com.demo.rule.actions.impl;

import com.demo.rule.actions.IActionExecutor;
import com.demo.rule.model.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Qualifier("MembershipActorImpl")
public class MembershipActor implements IActionExecutor {
    @Override
    public boolean execute(PaymentRequest request,String type) {
        return true;
    }
}
