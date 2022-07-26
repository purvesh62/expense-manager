package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.model.INotification;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

@SpringBootTest
public class NotificationDAOServiceTest {

    private NotificationDAOService service;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Mock
    IDatabase database;

    @Test
    public void testGetUsersWhoseSubscriptionIsExpiring_ThrowsException() throws SQLException, ParseException {
        service = new NotificationDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        Date subscriptionExpiryDate = formatter.parse(String.valueOf(yesterday));
        java.sql.Date subscriptionExpiry = new java.sql.Date(subscriptionExpiryDate.getTime());
        parameterList.add(subscriptionExpiry);
        Mockito.when(database.executeProcedure("CALL get_users_whose_subscription_expires(?)", parameterList)).thenThrow(new SQLException());
        List<INotification> allUserList = service.getUsersWhoseSubscriptionIsExpiring(String.valueOf(subscriptionExpiry));
        Assertions.assertEquals(0, allUserList.size());
    }

    @Test
    public void testGetUsersWhoseSubscriptionIsExpiring_ReturnsResultSet() throws SQLException, ParseException {
        service = new NotificationDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        Date subscriptionExpiryDate = formatter.parse(String.valueOf(tomorrow));
        java.sql.Date subscriptionExpiry = new java.sql.Date(subscriptionExpiryDate.getTime());
        parameterList.add(subscriptionExpiry);
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        when(mockResult.getString("email")).thenReturn("saifali@yahoo.com");
        when(mockResult.getString("subscription_name")).thenReturn("Netlix");
        when(mockResult.next()).thenReturn(true).thenReturn(false);
        Mockito.when(database.executeProcedure("CALL get_users_whose_subscription_expires(?)", parameterList))
                .thenReturn(mockResult);
        List<INotification> allUserList = service.getUsersWhoseSubscriptionIsExpiring(String.valueOf(subscriptionExpiry));
        Assertions.assertEquals(1, allUserList.size());
    }

}

