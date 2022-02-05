package com.demo.rule.service;

import com.demo.rule.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService service = new UserService();

    @Test
    void activateMemberShip_Success() {
        User user1 = User.builder().id("user1").isMember(true).accountNumber("1234").agentID("agent1").memberActivationStatus(true).build();
        assertTrue(service.activateMemberShip(user1));
    }

    @Test
    void updateMemberShip_Success() {
        User user1 = User.builder().id("user1").isMember(true).accountNumber("1234").agentID("agent1").memberActivationStatus(true).build();
        assertTrue(service.updateMemberShip(user1));
    }
}