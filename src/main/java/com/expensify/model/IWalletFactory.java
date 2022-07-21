package com.expensify.model;

import com.expensify.persistenceLayer.WalletDAOService;

public interface IWalletFactory {
    Wallet createWallet();
    WalletDAOService createWalletDAOService();

}
