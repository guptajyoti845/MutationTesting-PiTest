package com.vodqa.thoughtworks.reward;

import com.vodqa.thoughtworks.product.Product;

import java.util.List;

public class ConversionReward extends RewardService {
    private double amount;

    @Override
    public Reward applyReward(
            List<Product> order, long customerPoints) {
        Reward reward = new Reward();

        if (customerPoints >= minimumRequiredPoints) {
            double orderTotal = total(order);
            if (orderTotal > getAmount()) {
                reward = new Reward(getMinimumRequiredPoints(), getAmount());
            }
        }

        return reward;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount > 0) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("Amount should be greater than zero");
        }
    }
}
