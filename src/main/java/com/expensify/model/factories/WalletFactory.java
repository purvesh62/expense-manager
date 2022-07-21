package com.expensify.model.factories;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.IWallet;
import com.expensify.model.Wallet;
import com.expensify.model.factories.IWalletFactory;
import com.expensify.persistenceLayer.IWalletDAOService;
import com.expensify.persistenceLayer.WalletDAOService;

public class WalletFactory implements IWalletFactory {

    private static WalletFactory walletFactory;

    public WalletFactory(){

    }
    public static WalletFactory instance() {
        if (walletFactory == null) {
            walletFactory = new WalletFactory();
        }
        return walletFactory;
    }
    @Override
    public IWallet createWallet() {
        IDatabase database = MySqlDatabase.instance();
        return new Wallet(createWalletDAOService(database));
    }

//    @Override
//    public IWallet createWallet() {
//        return new Wallet();
//    }

    @Override
    public IWalletDAOService createWalletDAOService(IDatabase database) {
        return new WalletDAOService(database);
    }

    @Override
    public IWallet createWallet(int walletId, String walletLabel, int userId, int paymentType, float amount) {
        return new Wallet(walletId, walletLabel, userId, paymentType, amount);
    }
}
