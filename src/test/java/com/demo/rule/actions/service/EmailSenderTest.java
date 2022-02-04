package com.demo.rule.actions.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailSenderTest {

    EmailSender emailSender = new EmailSender();

    @BeforeEach
    void setUp() {
    }

    @Test
    void sendEmailSuccess() {
        assertTrue(emailSender.sendEmail());
    }
}