package com.expensify.persistenceLayer;

import com.expensify.model.IWallet;

import java.sql.SQLException;
import java.util.List;

public interface IWalletDAOService {
    List<IWallet> getAllWalletDetails(int userId);

    void addNewWallet(int userId, String walletLabel, int paymentType, float amount) throws SQLException;

    void updateWallet(int walletId, float amount, String walletLabel) throws SQLException;

    void deleteWallet(int budgetId) throws SQLException;

    IWallet getWalletById(int budgetId) throws SQLException;
}
