package com.expensify.model;

import java.sql.SQLException;
import java.util.List;

public interface IWallet {

    List <IWallet> getAllWalletDetails(int userId) throws SQLException;
    IWallet saveWallet() throws SQLException;
    IWallet updateWallet() throws SQLException;
    void deleteWallet(int walletId) throws SQLException;
    IWallet getWalletById(int walletId) throws SQLException;
}
