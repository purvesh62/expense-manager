package com.expensify.model;

import com.expensify.persistenceLayerMock.WalletDAOServiceMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest

public class WalletTest {
    @Test
    public void getAllWalletDetailsServiceSuccessTest() {
        WalletDAOServiceMock walletDAOServiceMock = new WalletDAOServiceMock();
        walletDAOServiceMock.getAllWalletMock();
        List<IWallet> allWalletDetails = walletDAOServiceMock.getAllWalletDetails(1);
        Assertions.assertEquals(2, allWalletDetails.size());
    }

    @Test
    public void getAllWalletDetailsServiceFailureTest() {
        WalletDAOServiceMock walletDAOServiceMock = new WalletDAOServiceMock();
        walletDAOServiceMock.getNullWalletMock();
        List<IWallet> allWalletDetails = walletDAOServiceMock.getAllWalletDetails(1);
        Assertions.assertEquals(null, allWalletDetails);
    }

    @Test
    public void saveWalletSuccessTest() throws SQLException {
        WalletDAOServiceMock walletDAOServiceMock = new WalletDAOServiceMock();
        boolean result = walletDAOServiceMock.addNewWallet(1, "CIBC", 1, 1200);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void saveWalletFailureTest() throws SQLException {
        WalletDAOServiceMock walletDAOServiceMock = new WalletDAOServiceMock();
        boolean result = walletDAOServiceMock.addNewWallet(0, "CIBC", 1, 1200);
        Assertions.assertEquals(false, result);
    }

    @Test
    public void updateWalletSuccessTest() throws SQLException {
        WalletDAOServiceMock walletDAOServiceMock = new WalletDAOServiceMock();
        boolean result = walletDAOServiceMock.updateWallet(1, 1200, "CIBC");
        Assertions.assertEquals(true, result);
    }

    @Test
    public void updateWalletFailureTest() throws SQLException {
        WalletDAOServiceMock walletDAOServiceMock = new WalletDAOServiceMock();
        boolean result = walletDAOServiceMock.updateWallet(0, 1200, "CIBC");
        Assertions.assertEquals(false, result);
    }

    @Test
    public void deleteWalletSuccessTest() throws SQLException {
        WalletDAOServiceMock walletDAOServiceMock = new WalletDAOServiceMock();
        boolean result = walletDAOServiceMock.deleteWallet(1);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void deleteWalletFailureTest() throws SQLException {
        WalletDAOServiceMock walletDAOServiceMock = new WalletDAOServiceMock();
        boolean result = walletDAOServiceMock.deleteWallet(0);
        Assertions.assertEquals(false, result);
    }


}
