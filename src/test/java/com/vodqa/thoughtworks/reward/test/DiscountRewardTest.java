package com.vodqa.thoughtworks.reward.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.vodqa.thoughtworks.product.Product;
import com.vodqa.thoughtworks.reward.DiscountReward;
import com.vodqa.thoughtworks.reward.Reward;

public class DiscountRewardTest {
    private DiscountReward reward = null;

    @BeforeEach
    void setUp() {
        reward = new DiscountReward();
        reward.setDiscountPercentage(0.1);
        reward.setMinimumRequiredPoints(100);
    }

    @Test
    @DisplayName("When customer has zero points no reward should be applied")
    void zeroCustomerPoints() {
        Product smallDecaf = new Product(1, "Small Decaf", 1.99);
        List<Product> order = Collections.singletonList(smallDecaf);

        Reward info = reward.applyReward(order, 0);

        assertEquals(0, info.getDiscount());
        assertEquals(0, info.getRedeemedPoints());
    }

    @Test
    @DisplayName("When customer has enough points reward should be applied")
    void customerHasEnoughPoints() {
        Product smallDecaf = new Product(1, "Small Decaf", 1.99);
        List<Product> order = Collections.singletonList(smallDecaf);

        Reward info = reward.applyReward(order, 100);

        assertEquals(0.199, info.getDiscount());
        assertEquals(reward.getMinimumRequiredPoints(), info.getRedeemedPoints());
    }

    @Test
    @DisplayName("Exception is thrown when invalid percentage is set")
    void exceptionThrownWhenInvalidPercentage() {
        long percentage = -1;
        assertThrows(RuntimeException.class, () -> {
        	reward.setDiscountPercentage(percentage);
        });
    }
}
