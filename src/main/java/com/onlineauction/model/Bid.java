package com.onlineauction.model;

import java.util.Date;

public class Bid {
    private int id;
    private int productId;
    private int userId;
    private double bidAmount;
    private Date bidTime;

    public Bid() {}

    public Bid(int id, int productId, int userId, double bidAmount, Date bidTime) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.bidAmount = bidAmount;
        this.bidTime = bidTime;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getBidAmount() { return bidAmount; }
    public void setBidAmount(double bidAmount) { this.bidAmount = bidAmount; }

    public Date getBidTime() { return bidTime; }
    public void setBidTime(Date bidTime) { this.bidTime = bidTime; }
}

