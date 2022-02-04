package com.demo.rule.actions.service;

import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    public boolean sendEmail(){
        boolean isEmailSent = false;
        System.out.println("send Email");
        isEmailSent =true;
        return isEmailSent;
    }
}
