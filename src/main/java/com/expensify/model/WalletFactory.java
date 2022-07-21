package com.expensify.model;

import com.expensify.database.IDatabase;
import com.expensify.persistenceLayer.IWalletDAOService;
import com.expensify.persistenceLayer.WalletDAOService;

public class WalletFactory implements IWalletFactory{

    public WalletFactory(){

    }
    @Override
    public IWallet createWallet(IDatabase database) {
        return new Wallet(createWalletDAOService(database));
    }

    @Override
    public IWallet createWallet() {
        return new Wallet();
    }

    @Override
    public IWalletDAOService createWalletDAOService(IDatabase database) {
        return new WalletDAOService(database);
    }

    @Override
    public IWallet createWallet(int walletId, String walletLabel, int userId, int paymentType, float amount) {
        return new Wallet(walletId, walletLabel, userId, paymentType, amount);
    }
}
