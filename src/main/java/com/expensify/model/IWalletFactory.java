package com.expensify.model;

import com.expensify.database.IDatabase;
import com.expensify.persistenceLayer.IWalletDAOService;

public interface IWalletFactory {
        IWallet createWallet(IDatabase database);
        IWallet createWallet();
        IWalletDAOService createWalletDAOService(IDatabase database);
        IWallet createWallet(int walletId, String walletLabel, int userId, int paymentType, float amount);
}
