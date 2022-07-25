package com.expensify.model;

import com.expensify.factories.NotificationFactory;
import com.expensify.persistenceLayer.IWalletDAOService;
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
    IWallet saveWallet() ;
    IWallet updateWallet() ;
    void deleteWallet(int walletId);

    IWalletDAOService getWalletDAOService();
}
