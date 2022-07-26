package com.expensify.factories;

import com.expensify.Validators.BudgetValidator;
import com.expensify.Validators.WalletValidator;
import com.expensify.database.IDatabase;
import com.expensify.model.IWallet;
import com.expensify.persistenceLayer.IWalletDAOService;

public interface IWalletFactory {
    IWallet createWallet();

    IWalletDAOService createWalletDAOService(IDatabase database);

    IWallet createWallet(int walletId, String walletLabel, int userId, int paymentType, float amount);

    WalletValidator createWalletValidator();
}
