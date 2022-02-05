package com.demo.rule.controller;

import com.demo.rule.model.PaymentRequest;
import com.demo.rule.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService service;
    @PostMapping(path = "/payment")
    public ResponseEntity<Void> pay(
        @RequestBody PaymentRequest request) {
        log.info("API call: /api/v1/payment");
        boolean result =service.performJobs(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
