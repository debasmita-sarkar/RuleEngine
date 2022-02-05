package com.demo.rule.utility;

import com.demo.rule.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PaymentUtility {
    public boolean pay(User from, User receiver){
        //TODO payment logic
        return true;
    }
}
