package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.model.IUser;
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


@RunWith(MockitoJUnitRunner.class)

@SpringBootTest
public class UserDAOServiceTest {

    private UserDAOService service;
    @Mock
    IDatabase database;

    @Test
    public void testSaveUsers_returnsTrue() throws SQLException {
        service = new UserDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add("groupproject");
        parameterList.add("asdc");
        parameterList.add("group7asdc@dal.ca");
        parameterList.add("group7asdc");
        parameterList.add("9789098078");
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        Mockito.when(database.executeProcedure("CALL register_user(?, ?, ?, ?, ?)", parameterList)).thenReturn(mockResult);
        List<IUser> users = service.saveUser("groupproject","asdc","group7asdc@dal.ca","group7asdc","9789098078");
        Assertions.assertEquals(1,users.size());
    }

    @Test
    public void testSaveUsers_returnsFalse() throws SQLException {
        service = new UserDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add("");
        parameterList.add("asdc");
        parameterList.add("group7asdcdal.ca");
        parameterList.add("group7asdc");
        parameterList.add("9789098078");
        Mockito.when(database.executeProcedure("CALL register_user(?, ?, ?, ?, ?)", parameterList)).thenThrow(new SQLException());
        List<IUser> users= service.saveUser("","asdc","group7asdcdal.ca","group7asdc","9789098078");
        Assertions.assertEquals(0,users.size());
    }
    @Test
    public void testresetPassword_returnsTrue() throws SQLException {
        service = new UserDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add("project@dal.ca");
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        Mockito.when(database.executeProcedure("CALL check_user_exist(?)", parameterList)).thenReturn(mockResult);
        boolean status = service.resetPassword("project@dal.ca");
        Assertions.assertEquals(true, status);
    }
    @Test
    public void testresetPassword_returnsFalse() throws SQLException {
        service = new UserDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add("project1@dal.ca");
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        Mockito.when(database.executeProcedure("CALL check_user_exist(?)", parameterList)).thenThrow(new SQLException());
        boolean status = service.resetPassword("project1@dal.ca");
        Assertions.assertEquals(false, status);
    }
    @Test
    public void testverifyUser_returnsTrue() throws SQLException {
        service = new UserDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add("project@dal.ca");
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        Mockito.when(database.executeProcedure("CALL check_user_exist(?)", parameterList)).thenReturn(mockResult);
        int status = service.verifyUser("project@dal.ca");
        Assertions.assertEquals(1, status);
    }
    @Test
    public void testverifyUser_returnsFalse() throws SQLException {
        service = new UserDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add("project1@dal.ca");
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        Mockito.when(database.executeProcedure("CALL check_user_exist(?)", parameterList)).thenThrow(new SQLException());
        int status = service.verifyUser("project1@dal.ca");
        Assertions.assertEquals(0, status);
    }
    @Test
    public void testupdatePassword_returnsTrue() throws SQLException {
        service = new UserDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add("project@dal.ca");
        parameterList.add("asdcproject");
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        Mockito.when(database.executeProcedure("CALL update_user_password(?,?)", parameterList)).thenReturn(mockResult);
        boolean status = service.updatePassword("project@dal.ca","asdcproject");
        Assertions.assertEquals(true, status);
    }
    @Test
    public void testupdatePassword_returnsFalse() throws SQLException {
        service = new UserDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add("project@dal.ca");
        parameterList.add("");
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        Mockito.when(database.executeProcedure("CALL update_user_password(?,?)", parameterList)).thenThrow(new SQLException());
        boolean status = service.updatePassword("project@dal.ca","");
        Assertions.assertEquals(true, status);
    }
    @Test
    public void testgetUserFirstName_returnsTrue() throws SQLException {
        service = new UserDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(1);
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        Mockito.when(database.executeProcedure("CALL get_user_firstname(?)", parameterList)).thenReturn(mockResult);
        boolean status = Boolean.parseBoolean(service.getUserFirstName(1));
        Assertions.assertEquals(true, status);
    }
    @Test
    public void testgetUserFirstName_returnsFalse() throws SQLException {
        service = new UserDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(0);
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        Mockito.when(database.executeProcedure("CALL get_user_firstname(?)", parameterList)).thenThrow(new SQLException());
        boolean status = Boolean.parseBoolean(service.getUserFirstName(0));
        Assertions.assertEquals(false,status);
    }
    @Test
    public void testgetUserPassword_returnsTrue() throws SQLException {
        service = new UserDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add("project@dal.ca");
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        Mockito.when(database.executeProcedure("CALL get_user_credential(?)", parameterList)).thenReturn(mockResult);
        boolean status = Boolean.parseBoolean(service.getUserPassword("project@dal.ca"));
        Assertions.assertEquals(true, status);
    }
    @Test
    public void testgetUserPassword_returnsFalse() throws SQLException {
        service = new UserDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        parameterList.add("project@dal.ca");
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        Mockito.when(database.executeProcedure("CALL get_user_credential(?)", parameterList)).thenThrow(new SQLException());
        boolean status = Boolean.parseBoolean(service.getUserPassword("project@dal.ca"));
        Assertions.assertEquals(false, status);
    }

}
