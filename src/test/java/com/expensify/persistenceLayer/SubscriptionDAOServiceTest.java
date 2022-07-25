package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.model.ISubscription;
import com.expensify.model.IWallet;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.quartz.SchedulerException;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SubscriptionDAOServiceTest {

    private SubscriptionDAOService service;
    @Mock
    IDatabase database;

    @Test
    public void testGetAllSubscriptionDetails_ThrowsException() throws SQLException, SchedulerException {
        service = new SubscriptionDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(1);
        when(database.executeProcedure("CALL get_user_subscription(?)", parameterList)).thenThrow(new SQLException());
        List<ISubscription> subscriptionList = service.getAllSubscriptionDetails(1);
        Assertions.assertEquals(0, subscriptionList.size());
    }

    @Test
    public void testGetAllWalletDetails_ReturnsResultSet() throws SQLException, SchedulerException {
        service = new SubscriptionDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(1);
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        when(mockResult.getInt("subscription_id")).thenReturn(123);
        when(mockResult.getString("subscription_name")).thenReturn("Netflix");
        when(mockResult.getInt("user_id")).thenReturn(123);
        when(mockResult.getDate("expiry_date")).thenReturn(Date.valueOf("2022-04-04"));
        when(mockResult.next()).thenReturn(true).thenReturn(false);
        Mockito.when(database.executeProcedure("CALL get_user_subscription(?)", parameterList))
                .thenReturn(mockResult);
        List<ISubscription> subscriptionList = service.getAllSubscriptionDetails(1);
        Assertions.assertEquals(1, subscriptionList.size());

    }

    @Test
    public void testAddNewSubscription_returnsTrue() throws SchedulerException, SQLException {
        service = new SubscriptionDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(1);
        parameterList.add("subscriptionName");
        parameterList.add("2023-04-04");
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        Mockito.when(database.executeProcedure("CALL add_user_subscription(?,?,?)", parameterList)).thenReturn(mockResult);
        boolean status = service.addNewSubscription(1,"subscriptionName", "2023-04-04");
        Assertions.assertEquals(true, status);
    }
    @Test
    public void testAddNewSubscription_returnsFalse() throws SchedulerException, SQLException {
        service = new SubscriptionDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(1);
        parameterList.add("subscriptionName");
        parameterList.add("2023-04-04");
        Mockito.when(database.executeProcedure("CALL add_user_subscription(?,?,?)", parameterList)).thenThrow(new SQLException());
        boolean status = service.addNewSubscription(1,"subscriptionName", "2023-04-04");
        Assertions.assertEquals(false, status);
    }

    @Test
    public void testAddNewSubscription_returnsResultSet() throws SchedulerException, SQLException {
        service = new SubscriptionDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(1);
        parameterList.add("subscriptionName");
        parameterList.add("2023-04-04");
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        Mockito.when(database.executeProcedure("CALL add_user_subscription(?,?,?)", parameterList)).thenReturn(mockResult);
        parameterList.remove("subscriptionName");
        parameterList.remove("2023-04-04");
        Mockito.when(database.executeProcedure("CALL get_all_user_details(?)", parameterList)).thenReturn(mockResult);
        boolean status = service.addNewSubscription(1, "subscriptionName", "2023-04-04");
        Assertions.assertEquals(true, status);

    }
}
