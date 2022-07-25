package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.model.*;
import com.expensify.model.factories.IWalletFactory;
import com.expensify.model.factories.WalletFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class WalletDAOService implements IWalletDAOService {
    private IDatabase database;

    public WalletDAOService(IDatabase database) {

        this.database = database;
    }

    public List<IWallet> getAllWalletDetails(int userId) {
        List<IWallet> walletList = new ArrayList<>();

        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(userId);

            ResultSet resultSet = database.
                    executeProcedure("CALL get_user_wallet(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    IWallet wallet = WalletFactory.instance().createWallet(
                            resultSet.getInt("wallet_id"),
                            resultSet.getString("wallet_label"),
                            resultSet.getInt("user_id"),
                            resultSet.getInt("p_type"),
                            resultSet.getFloat("amount")
                    );
                    walletList.add(wallet);
                }

            }
            return walletList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return walletList;
    }

    public boolean addNewWallet(int userId, String walletLabel, int paymentType, float amount) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(userId);
            parameterList.add(walletLabel);
            parameterList.add(paymentType);
            parameterList.add(amount);
            try (ResultSet resultSet = database.executeProcedure("CALL add_wallet(?,?,?,?)", parameterList)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return false;

    }

    public boolean deleteWallet(int walletId) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(walletId);
            try (ResultSet resultSet = database.executeProcedure("CALL delete_wallet(?)", parameterList)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return false;
    }


    public boolean updateWallet(int walletId, float amount, String walletLabel) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(walletId);
            parameterList.add(amount);
            parameterList.add(walletLabel);
            try (ResultSet resultSet = database.executeProcedure("CALL update_wallet(?,?,?)", parameterList)) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return false;
    }


}
