package com.expensify.model;

import com.expensify.persistenceLayer.PaymentCategoriesDAOService;
import com.expensify.persistenceLayer.WalletDAOService;

public interface IWalletFactory {
    Wallet makeWallet();

    WalletDAOService makeWalletDAOService();

}
