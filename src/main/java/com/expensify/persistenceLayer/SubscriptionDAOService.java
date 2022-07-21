//package com.expensify.persistenceLayer;
//
//import com.expensify.database.Database;
//import com.expensify.database.IDatabase;
//import com.expensify.model.ISubscription;
//import com.expensify.model.ISubscriptionFactory;
//import com.expensify.model.Subscription;
//import com.expensify.model.SubscriptionFactory;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SubscriptionDAOService implements ISubscriptionDAOService{
//    private final IDatabase database;
//
//    public SubscriptionDAOService() {
//        this.database = Database.instance();
//    }
//
//    @Override
//    public List<ISubscription> dailyDailyExpenseSubscribedUsers() {
//        List<ISubscription> userSubscribedList = new ArrayList<>();
//        try {
//            List<Object> parameterList = new ArrayList<>();
//
//            try (ResultSet resultSet = database.executeProcedure("CALL get_daily_expense_notification_subscribed_users()", parameterList)) {
//                if (resultSet != null) {
//                    while (resultSet.next()) {
//                        ISubscription subscription = new SubscriptionFactory().createSubscription(
//                                resultSet.getInt("id"),
//                                resultSet.getInt("user_id"),
//                                resultSet.getString("email"),
//                                resultSet.getInt("s_id"),
//                                resultSet.getInt("status")
//                        );
//                        userSubscribedList.add(subscription);
//                    }
//                }
//            }
//            return userSubscribedList;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                database.closeConnection();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return userSubscribedList;
//    }
//
//    @Override
//    public ISubscription getBudgetLimitExceedSubscribedUsers(int userId) {
//        ISubscriptionFactory subscriptionFactory = new SubscriptionFactory();
//        ISubscription subscription = subscriptionFactory.createSubscription();
//        try {
//            List<Object> parameterList = new ArrayList<>();
//            parameterList.add(userId);
//
//            try (ResultSet resultSet = database.executeProcedure("CALL get_budget_limit_exceed_notification(?)", parameterList)) {
//                if (resultSet != null) {
//                    while (resultSet.next()) {
//                        subscription = subscriptionFactory.createSubscription(
//                                resultSet.getInt("id"),
//                                resultSet.getInt("user_id"),
//                                resultSet.getString("email"),
//                                resultSet.getInt("s_id"),
//                                resultSet.getInt("status")
//                        );
//                    }
//                }
//            }
//            return subscription;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                database.closeConnection();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return subscription;
//    }
//}
