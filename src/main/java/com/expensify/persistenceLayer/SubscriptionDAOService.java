package com.expensify.persistenceLayer;
import com.expensify.database.IDatabase;
import com.expensify.model.ISubscription;
import com.expensify.model.factories.SubscriptionFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAOService implements ISubscriptionDAOService {
    private IDatabase database;

    public SubscriptionDAOService(IDatabase database) {

        this.database = database;
    }

    public List<ISubscription> getAllSubscriptionDetails(int userId) throws SQLException {
        List<ISubscription> subscriptionList = new ArrayList<>();

        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(userId);

            ResultSet resultSet = database.
                    executeProcedure("CALL get_user_subscription(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    ISubscription subscription = SubscriptionFactory.instance().createSubscription(
                            resultSet.getInt("subscription_id"),
                            resultSet.getString("subscription_name"),
                            resultSet.getInt("user_id"),
                            String.valueOf(resultSet.getDate("expiry_date"))
                    );
                    subscriptionList.add(subscription);
                }

            }
            return subscriptionList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();

        }
        return subscriptionList;
    }
    @Override
    public void addNewSubscription(int userId, String subscriptionName, String expiryDate) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(userId);
            parameterList.add(subscriptionName);
            parameterList.add(expiryDate);
            database.executeProcedure("CALL add_user_subscription(?,?,?)", parameterList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

    }

    @Override
    public void updateSubscription(int subscriptionId, String subscriptionName, String expiryDate) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(subscriptionId);
            parameterList.add(subscriptionName);
            parameterList.add(expiryDate);
            database.executeProcedure("CALL update_user_subscription(?,?,?)", parameterList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
    }


    @Override
    public void deleteSubscription(int subscriptionId) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(subscriptionId);

            database.executeProcedure("CALL delete_user_subscription(?)", parameterList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

    }

    @Override
    public ISubscription getSubscriptionById(int subscriptionId) throws SQLException {
        return null;
    }



//    public IWallet getWalletById(int walletId) throws SQLException {
//        IWalletFactory walletFactory = new WalletFactory();
//        IWallet wallet = walletFactory.createWallet();
//        try {
//            List<Object> parameterList = new ArrayList<>();
//            parameterList.add(walletId);
//
//
//            ResultSet resultSet = database.executeProcedure("CALL get_wallet_by_id(?)", parameterList);
//            if (resultSet != null) {
//                while (resultSet.next()) {
//                    wallet = walletFactory.createWallet(
//                            resultSet.getInt("wallet_id"),
//                            resultSet.getString("wallet_label"),
//                            resultSet.getInt("user_id"),
//                            resultSet.getInt("p_type"),
//                            resultSet.getFloat("amount")
//                    );
//                }
//            }
//            return wallet;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            database.closeConnection();
//        }
//        return wallet;
//    }

}
