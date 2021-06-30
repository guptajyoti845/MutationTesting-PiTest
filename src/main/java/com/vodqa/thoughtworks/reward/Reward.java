package com.vodqa.thoughtworks.reward;

public class Reward {
    private long redeemedPoints;
    private double discount;

    public Reward() { }

    public Reward(long pointsRedeemed, double discount) {
    	setRedeemedPoints(pointsRedeemed);
    	setDiscount(discount);
    }

    public long getRedeemedPoints() {
        return redeemedPoints;
    }

    public void setRedeemedPoints(long pointsReedemed) {
        this.redeemedPoints = pointsReedemed;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
