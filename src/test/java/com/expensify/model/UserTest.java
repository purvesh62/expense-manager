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
    public void saveUserSuccessTest()throws SQLException {
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        int result = userDAOServiceMock.saveUser("groupproject","asdc","group7asdc@dal.ca","group7asdc","9789098078");
        Assertions.assertEquals(1, result);
    }
    @Test
    public void saveUserFailureTest()throws SQLException {
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        int result = userDAOServiceMock.saveUser("","asdc","group7asdcdal.ca","group7asdc","9789098078");
        Assertions.assertEquals(0, result);
    }

    @Test
    public void getUserPasswordSuccessTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = Boolean.parseBoolean(userDAOServiceMock.getUserPassword("project@dal.ca"));
        Assertions.assertEquals(true, result);
    }
    @Test
    public void getUserPasswordFailureTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = Boolean.parseBoolean(userDAOServiceMock.getUserPassword(""));
        Assertions.assertEquals(false, result);
    }
    @Test
    public void verifyUserFailureTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        int result = userDAOServiceMock.verifyUser("");
        Assertions.assertEquals(0, result);
    }
    @Test
    public void updatePasswordSuccessTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = userDAOServiceMock.updatePassword("project@dal.ca","asdcproject");
        Assertions.assertEquals(true, result);
    }
    @Test
    public void resetPasswordSuccessTest() throws SQLException {
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = userDAOServiceMock.resetPassword("project@dal.ca");
        Assertions.assertEquals(true, result);
    }

    @Test
    public void resetPasswordFailureTest() throws SQLException {
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = userDAOServiceMock.resetPassword("");
        Assertions.assertEquals(false, result);
    }

    @Test
    public void verifyUserSuccessTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        int result = userDAOServiceMock.verifyUser("project@dal.ca");
        Assertions.assertEquals(1, result);
    }

    @Test
    public void updatePasswordFailureTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = userDAOServiceMock.updatePassword("project@dal.ca","");
        Assertions.assertEquals(false, result);
    }


}
