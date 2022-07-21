package com.expensify.model.factories;

import com.expensify.database.IDatabase;
import com.expensify.model.IWallet;
import com.expensify.persistenceLayer.IWalletDAOService;

public interface IWalletFactory {
        IWallet createWallet();
//        IWallet createWallet();
        IWalletDAOService createWalletDAOService(IDatabase database);
        IWallet createWallet(int walletId, String walletLabel, int userId, int paymentType, float amount);
}
