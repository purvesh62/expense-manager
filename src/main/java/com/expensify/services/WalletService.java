package com.expensify.services;
import com.expensify.model.Wallet;
import com.expensify.persistenceLayer.WalletDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;
@Service
public class WalletService {
    private final WalletDAOService walletDAOService;

    @Autowired
    public WalletService(WalletDAOService walletDAOService) {
        this.walletDAOService = walletDAOService;
    }

    public List<Wallet> getAllWalletDetails(int userId) throws SQLException {
        return walletDAOService.getAllWalletDetails(userId);
    }

    public void deleteWallet(int walletId) throws SQLException {
        walletDAOService.deleteWallet(walletId);
    }

    public void saveWallet(Wallet newWallet) throws SQLException {
        walletDAOService.addNewWallet(newWallet);
    }

}
