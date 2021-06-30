package com.vodqa.thoughtworks.reward;

import com.vodqa.thoughtworks.product.Product;

import java.util.List;

public abstract class RewardService {
    protected long minimumRequiredPoints;

    public abstract Reward applyReward(
            List<Product> order, long customerPoints);

    protected double total(List<Product> order) {
        double sum = 0;

        if(order != null) {
            sum = order.stream().mapToDouble(Product::getPrice).sum();
        }

        return sum;
    }

    public long getMinimumRequiredPoints() {
        return minimumRequiredPoints;
    }

    public void setMinimumRequiredPoints(long minimumRequiredPoints) {
        this.minimumRequiredPoints = minimumRequiredPoints;
    }
}
