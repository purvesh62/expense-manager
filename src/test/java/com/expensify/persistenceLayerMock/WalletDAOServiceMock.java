package com.expensify.persistenceLayerMock;

import com.expensify.factories.WalletFactory;
import com.expensify.model.IWallet;
import com.expensify.persistenceLayer.IWalletDAOService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WalletDAOServiceMock implements IWalletDAOService {

    List<IWallet> walletMockList = new ArrayList<>();

    public void getAllWalletMock() {
        IWallet walletMock1 = WalletFactory.instance().createWallet();
        IWallet walletMock2 = WalletFactory.instance().createWallet();
        walletMockList.add(walletMock1);
        walletMockList.add(walletMock2);
    }

    public void getNullWalletMock() {
        walletMockList = null;
    }

    @Override
    public List<IWallet> getAllWalletDetails(int userId) {
        return walletMockList;
    }

    @Override
    public boolean addNewWallet(int userId, String walletLabel, int paymentType, float amount) throws SQLException {
        if (userId == 0) {
            return false;
        }
        return true;

    }

    @Override
    public boolean updateWallet(int walletId, float amount, String walletLabel) throws SQLException {
        if (walletId == 0) {
            return false;
        }
        return true;

    }

    @Override
    public boolean deleteWallet(int walletId) throws SQLException {
        if (walletId == 1) {
            return true;
        }
        return false;
    }

}
