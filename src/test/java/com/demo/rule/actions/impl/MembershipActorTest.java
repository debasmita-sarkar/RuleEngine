package com.demo.rule.actions.impl;

import com.demo.rule.model.PaymentRequest;
import com.demo.rule.model.User;
import com.demo.rule.payments.PAYMENT_TYPES;
import com.demo.rule.service.EmailSender;
import com.demo.rule.service.UserService;
import com.demo.rule.utility.MEMBERSHIP_ACTION_TYPE;
import com.demo.rule.utility.UserCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MembershipActorTest {
    MembershipActor membershipActor = new MembershipActor();
    PaymentRequest request=null;
    UserService userService = Mockito.mock(UserService.class);
    EmailSender emailSender = Mockito.mock(EmailSender.class);;
    @BeforeEach
    void setUp() {
        //Mock user service
        when(userService.activateMemberShip(any(User.class))).thenReturn(true);
        when(userService.updateMemberShip(any(User.class))).thenReturn(true);
        ReflectionTestUtils.setField(membershipActor,"userService",userService);
        //Mock email sender
        when(emailSender.sendEmail()).thenReturn(true);
        ReflectionTestUtils.setField(membershipActor,"emailSender",emailSender);
        //Mock user
        User user1 = User.builder().id("user1").isMember(true).accountNumber("1234").agentID("agent1").memberActivationStatus(true).build();
        UserCache.put(user1);
        request = PaymentRequest.builder().paymentType(PAYMENT_TYPES.PHYSICAL_PRODUCT.name()).from("user1").build();
    }
    @Test
    void executeActivateSuccess() {
        assertTrue(membershipActor.execute(request, MEMBERSHIP_ACTION_TYPE.ACTIVATE.toString()));
    }
    @Test
    void executeUpgradeSuccess() {
        assertTrue(membershipActor.execute(request,MEMBERSHIP_ACTION_TYPE.UPGRADE.toString()));
    }
}