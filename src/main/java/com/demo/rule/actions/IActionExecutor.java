package com.demo.rule.actions;

import com.demo.rule.model.PaymentRequest;
import lombok.Data;


public interface IActionExecutor {
     public boolean execute(PaymentRequest request,String type);
}
