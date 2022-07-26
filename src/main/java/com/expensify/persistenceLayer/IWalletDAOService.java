package com.expensify.persistenceLayer;

import com.expensify.model.IWallet;

import java.sql.SQLException;
import java.util.List;

public interface IWalletDAOService {
    List<IWallet> getAllWalletDetails(int userId) throws SQLException;

    boolean addNewWallet(int userId, String walletLabel, int paymentType, float amount) throws SQLException;

    boolean updateWallet(int walletId, float amount, String walletLabel) throws SQLException;

    boolean deleteWallet(int walletId) throws SQLException;
}
