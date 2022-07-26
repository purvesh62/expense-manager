package com.expensify.model;

import com.expensify.persistenceLayer.IWalletDAOService;

import java.sql.SQLException;
import java.util.List;

public class Wallet implements IWallet {
    private IWalletDAOService walletDAOService;

    private int walletId;

    private String walletLabel;

    private int userId;

    private int paymentType;
    private float amount;

    public Wallet(int walletId, String walletLabel, int userId, int paymentType, float amount) {
        this.walletId = walletId;
        this.walletLabel = walletLabel;
        this.userId = userId;
        this.paymentType = paymentType;
        this.amount = amount;
    }

    public Wallet() {

    }

    public Wallet(IWalletDAOService database) {
        walletDAOService = database;
    }

    public IWalletDAOService getWalletDAOService() {
        return walletDAOService;
    }

    public void setWalletDAOService(IWallet wallet) {
        this.walletDAOService = wallet.getWalletDAOService();
    }

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

    @Override
    public List<IWallet> getAllWalletDetails(int userId) {
        try {
            return walletDAOService.getAllWalletDetails(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveWallet() {
        try {
            return walletDAOService.addNewWallet(userId, walletLabel, paymentType, amount);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteWallet(int walletId) {
        try {
            return walletDAOService.deleteWallet(walletId);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean updateWallet() {
        try {
            return walletDAOService.updateWallet(walletId, amount, walletLabel);
        } catch (SQLException e) {
            return false;
        }
    }
}
