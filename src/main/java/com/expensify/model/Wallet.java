package com.expensify.model;
import com.expensify.persistenceLayer.IWalletDAOService;
import com.expensify.persistenceLayer.WalletDAOService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.List;

public class Wallet implements IWallet {
    private IWalletDAOService walletDAOService;
    private int walletId;
    @NotEmpty
    @NotNull
    private String walletLabel;
    private int userId;
    @Min(value = 1)
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

    public Wallet(IWalletDAOService database){
        walletDAOService = database;
    }

    public IWalletDAOService getWalletDAOService(){
        return walletDAOService;
    }

    public void setWalletDAOService(IWalletDAOService walletDAOService){
        this.walletDAOService = walletDAOService;
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
    public List<IWallet> getAllWalletDetails(int userId){
        return walletDAOService.getAllWalletDetails(userId);
    }
  @Override
    public IWallet getWalletById(int walletId) throws SQLException {
        return walletDAOService.getWalletById(walletId);
    }
   @Override
    public IWallet saveWallet() throws SQLException {
        walletDAOService.addNewWallet(userId, walletLabel,paymentType, amount);
        return this;
    }
  @Override
    public void deleteWallet(int walletId) throws SQLException {
        walletDAOService.deleteWallet(walletId);
    }
   @Override
    public IWallet updateWallet() throws SQLException {
       walletDAOService.updateWallet(walletId, amount, walletLabel);
       return this;
    }

//    @Override
//    public int compareTo(IWallet wallet) {
//        if (this.walletId > wallet.walletId) {
//            return 1;
//        } else {
//            return -1;
//        }
//    }


}
