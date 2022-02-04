package com.demo.rule.actions.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackingSlipGeneratorTest {
    PackingSlipGenerator slipGenerator = new PackingSlipGenerator();

    @BeforeEach
    void setUp() {
    }

    @Test
    void executeForShippingSuccess() {
        assertTrue(slipGenerator.execute("shipping"));
    }
    @Test
    void executeForRoyaltySuccess() {
        assertTrue(slipGenerator.execute("royalty"));
    }
}