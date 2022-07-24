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
                    IWalletFactory walletFactory = new WalletFactory();
                    IWallet wallet = walletFactory.createWallet(
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
        } finally {
            try {
                database.closeConnection();
            } catch (SQLException e) {
                return walletList;
            }

        }
        return walletList;
    }
    public IWallet getWalletById(int walletId) throws SQLException {
        IWalletFactory walletFactory = new WalletFactory();
        IWallet wallet = walletFactory.createWallet();
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(walletId);


            ResultSet resultSet = database.executeProcedure("CALL get_wallet_by_id(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    wallet = walletFactory.createWallet(
                        resultSet.getInt("wallet_id"),
                        resultSet.getString("wallet_label"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("p_type"),
                        resultSet.getFloat("amount")
                    );
                }
            }
            return wallet;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return wallet;
    }

    public void deleteWallet(int walletId) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(walletId);

            database.executeProcedure("CALL delete_wallet(?)", parameterList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
    }

    public void addNewWallet(int userId, String walletLabel, int paymentType, float amount) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(userId);
            parameterList.add(walletLabel);
            parameterList.add(paymentType);
            parameterList.add(amount);
            database.executeProcedure("CALL add_wallet(?,?,?,?)", parameterList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

    }

    public void updateWallet(int walletId, float amount, String walletLabel) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(walletId);
            parameterList.add(amount);
            parameterList.add(walletLabel);
            database.executeProcedure("CALL update_wallet(?,?,?)", parameterList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
    }


}
