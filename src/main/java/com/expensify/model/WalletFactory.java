package com.expensify.model;

import com.expensify.persistenceLayer.WalletDAOService;
import org.springframework.stereotype.Service;

@Service
public class WalletFactory implements IWalletFactory {
    @Override
    public Wallet createWallet() {

        return Wallet.newWallet(this.createWalletDAOService());
    }

    @Override
    public WalletDAOService createWalletDAOService() {
        return new WalletDAOService(this);
    }




}
