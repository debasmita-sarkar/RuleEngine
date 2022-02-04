package com.demo.rule.actions.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembershipActorTest {
    MembershipActor membershipActor = new MembershipActor();
    @Test
    void executeActivateSuccess() {
        assertTrue(membershipActor.execute("activate"));
    }
    @Test
    void executeUpgradeSuccess() {
        assertTrue(membershipActor.execute("upgrade"));
    }
}