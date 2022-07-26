package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.model.IWallet;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

@SpringBootTest
public class WalletDAOServiceTest {

    private WalletDAOService service;
    @Mock
    IDatabase database;

    @Test
    public void testGetAllWalletDetails_ThrowsException() throws SQLException {
        service = new WalletDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(1);
        Mockito.when(database.executeProcedure("CALL get_user_wallet(?)", parameterList)).thenThrow(new SQLException());
        List<IWallet> wallets = service.getAllWalletDetails(1);
        Assertions.assertEquals(0, wallets.size());
    }

    @Test
    public void testGetAllWalletDetails_ReturnsResultSet() throws SQLException {
        service = new WalletDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(1);
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        when(mockResult.getInt("wallet_id")).thenReturn(123);
        when(mockResult.getInt("user_id")).thenReturn(123);
        when(mockResult.getInt("p_type")).thenReturn(123);
        when(mockResult.getFloat("amount")).thenReturn(123.0f);
        when(mockResult.getString("wallet_label")).thenReturn("123");
        when(mockResult.next()).thenReturn(true).thenReturn(false);
        Mockito.when(database.executeProcedure("CALL get_user_wallet(?)", parameterList))
                .thenReturn(mockResult);

        List<IWallet> wallets = service.getAllWalletDetails(1);
        Assertions.assertEquals(1, wallets.size());

    }

    @Test
    public void testAddNewWallet_ReturnsTrue() throws SQLException {
        service = new WalletDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(1);
        parameterList.add("walletLabel");
        parameterList.add(2);
        parameterList.add(4.0f);
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        when(database.executeProcedure("CALL add_wallet(?,?,?,?)", parameterList)).thenReturn(mockResult);
        boolean status = service.addNewWallet(1,"walletLabel",2,4.0f);
        Assertions.assertEquals(true, status);
    }

    @Test
    public void testAddNewWallet_ReturnsFalse() throws SQLException {
        service = new WalletDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(0);
        parameterList.add("walletLabel");
        parameterList.add(2);
        parameterList.add(4.0f);
        when(database.executeProcedure("CALL add_wallet(?,?,?,?)", parameterList)).thenThrow(new SQLException());
        boolean status = service.addNewWallet(0, "walletLabel", 2, 4.0f);
        Assertions.assertEquals(false, status);
    }

    @Test
    public void testUpdateWallet_ReturnsTrue() throws SQLException {
        service = new WalletDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(1);
        parameterList.add(4.0f);
        parameterList.add("walletLabel");
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        when(database.executeProcedure("CALL update_wallet(?,?,?)", parameterList)).thenReturn(mockResult);
        boolean status = service.updateWallet(1,4.0f,"walletLabel");
        Assertions.assertEquals(true,status);
    }

    @Test
    public void testUpdateWallet_ReturnsFalse() throws SQLException {
        service = new WalletDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(0);
        parameterList.add(4.0f);
        parameterList.add("walletLabel");
        when(database.executeProcedure("CALL update_wallet(?,?,?)", parameterList)).thenThrow(new SQLException());
        boolean status = service.updateWallet(0,4.0f,"walletLabel");
        Assertions.assertEquals(false,status);
    }

    @Test
    public void testDeleteWallet_ReturnsTrue() throws SQLException {
        service = new WalletDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(1);
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        when(database.executeProcedure("CALL delete_wallet(?)", parameterList)).thenReturn(mockResult);
        boolean status = service.deleteWallet(1);
        Assertions.assertEquals(true,status);
    }

    @Test
    public void testDeleteWallet_ReturnsFalse() throws SQLException {
        service = new WalletDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(0);
        when(database.executeProcedure("CALL delete_wallet(?)", parameterList)).thenThrow(new SQLException());
        boolean status = service.deleteWallet(0);
        Assertions.assertEquals(false,status);
    }
}
