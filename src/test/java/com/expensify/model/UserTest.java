package com.expensify.model;

import com.expensify.persistenceLayerMock.UserDAOServiceMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;


@SpringBootTest
public class UserTest {

    @Test
    public void resetPasswordSuccessTest() throws SQLException {
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = userDAOServiceMock.resetPassword("project@dal.ca");
        Assertions.assertEquals(true, result);
    }

    @Test
    public void resetPasswordFailureTest() throws SQLException {
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = userDAOServiceMock.resetPassword("project1@dal.ca");
        Assertions.assertEquals(false, result);
    }

    @Test
    public void saveUserSuccessTest()throws SQLException {
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        List<IUser> result = userDAOServiceMock.saveUser("groupproject","asdc","group7asdc@dal.ca","group7asdc","9789098078");
        Assertions.assertEquals(17, result);
    }
    @Test
    public void saveUserFailureTest()throws SQLException {
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        List<IUser> result = userDAOServiceMock.saveUser("","asdc","group7asdcdal.ca","group7asdc","9789098078");
        Assertions.assertEquals(0, result);
    }

    public void verifyUserSuccessTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        int result = userDAOServiceMock.verifyUser("project@dal.ca");
        Assertions.assertEquals(14, result);
    }

    public void verifyUserFailureTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        int result = userDAOServiceMock.verifyUser("project1@dal.ca");
        Assertions.assertEquals(0, result);
    }
    public void updatePasswordSuccessTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = userDAOServiceMock.updatePassword("project@dal.ca","asdcproject");
        Assertions.assertEquals(true, result);
    }

    public void updatePasswordFailureTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = userDAOServiceMock.updatePassword("project@dal.ca","");
        Assertions.assertEquals(false, result);
    }
    public void getUserFirstNameSuccessTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        int result = Integer.parseInt(userDAOServiceMock.getUserFirstName(1));
        Assertions.assertEquals(1, result);
    }

    public void getUserFirstNameFailureTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        int result = Integer.parseInt(userDAOServiceMock.getUserFirstName(0));
        Assertions.assertEquals(0, result);
    }
    public void getUserPasswordSuccessTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = Boolean.parseBoolean(userDAOServiceMock.getUserPassword("project@dal.ca"));
        Assertions.assertEquals(true, result);
    }

    public void getUserPasswordFailureTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = Boolean.parseBoolean(userDAOServiceMock.getUserPassword("project@dal.ca"));
        Assertions.assertEquals(false, result);
    }

}
