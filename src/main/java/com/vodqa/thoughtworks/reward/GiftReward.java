package com.vodqa.thoughtworks.reward;

import com.vodqa.thoughtworks.product.Product;

import java.util.List;
import java.util.Optional;

public class GiftReward extends RewardService {
    private long giftProductId;

    @Override
    public Reward applyReward(
            List<Product> order, long customerPoints) {
        Reward reward = new Reward();

        if(customerPoints >= minimumRequiredPoints) {
            Optional<Product> result = order
                    .stream()
                    .filter(p -> p.getId() == getGiftProductId())
                    .findAny();
            if(result.isPresent()) {
                reward = new Reward(
                        getMinimumRequiredPoints(),
                        result.get().getPrice()
                );
            }
        }

        return reward;
    }

    public long getGiftProductId() {
        return giftProductId;
    }

    public void setGiftProductId(long giftProductId) {
        if(giftProductId > 0) {
            this.giftProductId = giftProductId;
        } else {
            throw new IllegalArgumentException(giftProductId + " is not a valid product");
        }
    }
}
