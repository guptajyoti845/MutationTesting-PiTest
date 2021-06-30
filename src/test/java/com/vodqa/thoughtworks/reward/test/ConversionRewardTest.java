package com.vodqa.thoughtworks.reward.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.vodqa.thoughtworks.product.Product;
import com.vodqa.thoughtworks.reward.ConversionReward;
import com.vodqa.thoughtworks.reward.Reward;

public class ConversionRewardTest {
    private ConversionReward reward = null;

    @BeforeEach
    void setUp() {
        reward = new ConversionReward();
        reward.setAmount(10);
        reward.setMinimumRequiredPoints(100);
    }

    @Test
    @DisplayName("When empty order and zero points no reward should be applied")
    void emptyOrderZeroPoints() {
        Reward info = reward.applyReward(getEmptyOrder(), 0);

        assertEquals(0, info.getDiscount());
        assertEquals(0, info.getRedeemedPoints());
    }

    @Test
    @DisplayName("When not enough points no reward should be applied")
    void notEnoughPoints() {
        Reward info = reward.applyReward(getSampleOrder(), 10);

        assertEquals(0, info.getDiscount());
        assertEquals(0, info.getRedeemedPoints());
    }

    @Test
    @DisplayName("When empty order and enough points no reward should be applied")
    void emptyOrderEnoughPoints() {
        Reward info = reward.applyReward(getEmptyOrder(), 200);

        assertEquals(0, info.getDiscount());
        assertEquals(0, info.getRedeemedPoints());
    }

    @Test
    @DisplayName("When enough points and order total is greater than amount reward should be applied")
    void enoughPointsOrderTotalGreaterThanAmount() {
        Reward info = reward.applyReward(getSampleOrder(), 100);

        assertEquals(reward.getAmount(), info.getDiscount());
        assertEquals(reward.getMinimumRequiredPoints(), info.getRedeemedPoints());
    }

    @Test
    @DisplayName("Exception is thrown when invalid amount is set")
    void exceptionThrownWhenInvalidAmount() {
        long amount = -1;
        assertThrows(RuntimeException.class, () -> {
        	reward.setAmount(amount);
        });
    }

	private List<Product> getEmptyOrder() {
        return Arrays.asList();
    }

    private List<Product> getSampleOrder() {
        Product bigDecaf = new Product(1, "Big Decaf", 2.49);
        Product bigLatte = new Product(2, "Big Latte", 2.99);
        Product bigTea = new Product(3, "Big Tea", 2.99);
        Product espresso = new Product(4, "Espresso", 2.99);
        return Arrays.asList(
                bigDecaf, bigLatte, bigTea, espresso);
    }
}
