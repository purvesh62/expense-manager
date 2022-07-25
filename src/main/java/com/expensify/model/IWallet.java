package com.expensify.model;

import com.expensify.factories.NotificationFactory;
import org.json.simple.JSONObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

public interface IWallet {

    List <IWallet> getAllWalletDetails(int userId);
    IWallet saveWallet() throws SQLException;
    IWallet updateWallet() throws SQLException;
    void deleteWallet(int walletId) throws SQLException;
    IWallet getWalletById(int walletId) throws SQLException;

    @Configuration
    @EnableScheduling
    @ConditionalOnProperty(name = "scheduling.enabled")
    class Scheduler {

        @Scheduled(cron = "* 10 * 10 * *")
        public void sendDailyReminderToFillExpense() {
            List<INotification> notificationList = NotificationFactory.instance().createNotification().getDailyExpenseSubscribedUser();
            ListIterator<INotification> iter = notificationList.listIterator();
            while (iter.hasNext()) {
                INotification notification = (INotification) iter.next();
                notification.notifyUsers("Reminder to add expense", "Reminder");
            }
        }
    }

    class SessionManager {

        public static JSONObject getSession(HttpSession session) {
            Object cache =  session.getAttribute(session.getId());
            if (cache != null){
                return (JSONObject) cache;
            }
            return new JSONObject();
        }

        public static void setSession(HttpSession session, Object value) {
            session.setAttribute(session.getId(), value);
        }

        public static void removeSession(HttpSession session) {
            session.removeAttribute(session.getId());
        }
    }
}
