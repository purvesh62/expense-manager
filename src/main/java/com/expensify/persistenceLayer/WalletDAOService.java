package com.expensify.persistenceLayer;

import com.expensify.database.Database;
import com.expensify.database.IDatabase;
import com.expensify.model.Wallet;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WalletDAOService {
    private final IDatabase database;
    public WalletDAOService() {
        this.database = Database.getInstance();
    }

    public List<Wallet> getAllWalletDetails(int userId) throws SQLException {
        List<Wallet> walletList = new ArrayList<>();
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(userId);

            ResultSet resultSet = database.executeProcedure("CALL get_user_wallet(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Wallet wallet = new Wallet();
                    wallet.setWalletId(resultSet.getInt("wallet_id"));
                    wallet.setWalletLabel(resultSet.getString("wallet_label"));
                    wallet.setUserId(resultSet.getInt("user_id"));
                    wallet.setPaymentType(resultSet.getInt("p_type"));
                    wallet.setAmount(resultSet.getFloat("amount"));
                    walletList.add(wallet);
                }
            }
            return walletList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();

        }
        return walletList;
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

    public void addNewWallet(Wallet newWallet) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(newWallet.getUserId());
            parameterList.add(newWallet.getWalletLabel());
            parameterList.add(newWallet.getPaymentType());
            parameterList.add(newWallet.getAmount());
            database.executeProcedure("CALL add_wallet(?,?,?,?)", parameterList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

    }

    public void updateWallet(Wallet newWallet) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(newWallet.getWalletId());
            parameterList.add(newWallet.getAmount());
            parameterList.add(newWallet.getWalletLabel());
            database.executeProcedure("CALL update_wallet(?,?,?)", parameterList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
    }


}
