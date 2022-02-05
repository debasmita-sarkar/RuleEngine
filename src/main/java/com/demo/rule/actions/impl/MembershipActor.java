package com.demo.rule.actions.impl;

import com.demo.rule.actions.IActionExecutor;
import com.demo.rule.model.PaymentRequest;
import com.demo.rule.service.EmailSender;
import com.demo.rule.service.UserService;
import com.demo.rule.utility.MEMBERSHIP_ACTION_TYPE;
import com.demo.rule.utility.PaymentUtility;
import com.demo.rule.utility.UserCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Qualifier("MembershipActorImpl")
public class MembershipActor implements IActionExecutor {
    @Autowired
    UserService userService;
    @Autowired
    EmailSender emailSender;
    @Override
    public boolean execute(PaymentRequest request,String type) {
        if(UserCache.get(request.getFrom()) ==null){
            return false;
        }
        if(type.equalsIgnoreCase(MEMBERSHIP_ACTION_TYPE.ACTIVATE.toString())) {
            boolean result=userService.activateMemberShip(UserCache.get(request.getFrom()));
            result=result && emailSender.sendEmail();
            return result;
        }else if(type.equalsIgnoreCase(MEMBERSHIP_ACTION_TYPE.UPGRADE.toString())){
            boolean result=userService.updateMemberShip(UserCache.get(request.getFrom()));
            result=result && emailSender.sendEmail();
            return result;
        }
        return false;
    }
}
