package com.expensify.model;

import com.expensify.persistenceLayer.IWalletDAOService;

import java.util.List;

public interface IWallet {

    List<IWallet> getAllWalletDetails(int userId);

    IWallet saveWallet();

    IWallet updateWallet();

    void deleteWallet(int walletId);

    IWalletDAOService getWalletDAOService();
}
