package com.expensify.model;
import com.expensify.persistenceLayer.WalletDAOService;
import java.sql.SQLException;
import java.util.List;

public class Wallet implements Comparable<Wallet> {
    private WalletDAOService walletDAOService;
    private int walletId;

    private String walletLabel;
    private int userId;

    private int paymentType;

    private float amount;

    public WalletDAOService getWalletDAOService() {
        return walletDAOService;
    }

    public void setWalletDAOService(WalletDAOService walletDAOService) {
        this.walletDAOService = walletDAOService;
    }

    private Wallet(WalletDAOService walletDAOService) {
        this.walletDAOService = walletDAOService;
    }

    public static Wallet newWallet(WalletDAOService walletDAOService) {
        return new Wallet(walletDAOService);
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

    public List<Wallet> getAllWalletDetails(int userId) throws SQLException {
        return walletDAOService.getAllWalletDetails(userId);
    }

    public Wallet getWalletById(int walletId) throws SQLException {
        return walletDAOService.getWalletById(walletId);
    }

    public void saveWallet(Wallet newWallet) throws SQLException {
        walletDAOService.addNewWallet(newWallet);
    }

    public void deleteWallet() throws SQLException {
        walletDAOService.deleteWallet(this.walletId);
    }

    public void updateWallet(Wallet wallet) throws SQLException {
        walletDAOService.updateWallet(wallet);
    }

    @Override
    public int compareTo(Wallet wallet) {
        if (this.walletId > wallet.walletId) {
            return 1;
        } else {
            return -1;
        }
    }
}
