package com.expensify.model;

public class Wallet {
    private int walletId;
    private String walletLabel;
    private int userId;
    private int paymentType;

    private float amount;
    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public String getWalletLabel() {
        return walletLabel;
    }

    public void setWalletLabel(String walletLabel) {
        this.walletLabel = walletLabel;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
