package com.vodqa.thoughtworks.reward.test;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.vodqa.thoughtworks.product.Product;
import com.vodqa.thoughtworks.reward.GiftReward;
import com.vodqa.thoughtworks.reward.Reward;

public class GiftRewardTest {
    private GiftReward reward = null;

    @BeforeEach
    void setUp() {
        reward = new GiftReward();
        reward.setGiftProductId(4);
        reward.setMinimumRequiredPoints(100);
    }

    @Test
    @DisplayName("Reward applied with enough points")
    void rewardApplied() {
        Reward info = reward.applyReward(
                buildSampleOrder(10), 200
        );

        assertAll("Reward info errors",
                () -> assertNotNull(info),
                () -> assertEquals(2.99, info.getDiscount()),
                () -> assertEquals(100, info.getRedeemedPoints())
        );
    }

    @Test
    @DisplayName("Exception is thrown when invalid product ID is set")
    void exceptionThrownWhenInvalidProductID() {
        long productId = -1;
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reward.setGiftProductId(productId);
        });
        assertTrue(exception.getMessage().contains(String.valueOf(productId)));
    }

    private List<Product> buildSampleOrder(int numberOfProducts) {
        List<Product> list = IntStream.range(1, numberOfProducts + 1)
                .mapToObj(i -> new Product(i, "Product " + i, 2.99))
                .collect(toList());
        return list;
    }

}
