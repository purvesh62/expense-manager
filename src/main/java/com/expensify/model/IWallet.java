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
}
