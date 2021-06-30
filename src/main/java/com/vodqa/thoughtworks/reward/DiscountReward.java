package com.vodqa.thoughtworks.reward;

import com.vodqa.thoughtworks.product.Product;

import java.util.List;

public class DiscountReward extends RewardService {
    private double discountPercentage;

    @Override
    public Reward applyReward(
            List<Product> order, long customerPoints) {
        Reward reward = new Reward();

        if(customerPoints >= minimumRequiredPoints) {
            double orderTotal = total(order);
            double discount = orderTotal * getDiscountPercentage();
            reward = new Reward(getMinimumRequiredPoints(), discount);
        }

        return reward;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        if(discountPercentage > 0) {
            this.discountPercentage = discountPercentage;
        } else {
            throw new IllegalArgumentException("Percentage should be greater than zero");
        }
    }
}
