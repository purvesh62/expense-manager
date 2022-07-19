package com.expensify.model;

import com.expensify.persistenceLayer.PaymentCategoriesDAOService;
import com.expensify.persistenceLayer.WalletDAOService;
import org.springframework.stereotype.Service;

@Service
public class WalletFactory implements IWalletFactory {
    @Override
    public Wallet makeWallet() {

        return Wallet.newWallet(this.makeWalletDAOService());
    }

    @Override
    public WalletDAOService makeWalletDAOService() {
        return new WalletDAOService(this);
    }




}
