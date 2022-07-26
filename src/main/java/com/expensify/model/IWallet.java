package com.expensify.model;

import com.expensify.persistenceLayer.IWalletDAOService;

import java.util.List;

public interface IWallet {

    List<IWallet> getAllWalletDetails(int userId);

    boolean saveWallet();

    boolean updateWallet();

    boolean deleteWallet(int walletId);

    IWalletDAOService getWalletDAOService();
}
