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
    public void checkIfEmailExistsTest() throws SQLException {
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = userDAOServiceMock.checkIfEmailExists("njaishwarya01@gmail.com");
        Assertions.assertEquals(true, result);
    }

    @Test
    public void checkIfEmailExistsFailureTest() throws SQLException {
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = userDAOServiceMock.checkIfEmailExists("njaishwarya01gmail.com");
        Assertions.assertEquals(false, result);
    }

    @Test
    public void saveUserSuccessTest()throws SQLException {
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        int result = userDAOServiceMock.saveUser("Aishwarya","Natarajan","group7@domain.com","group7","0987654321");
        Assertions.assertEquals(17, result);
    }
    @Test
    public void saveUserFailureTest()throws SQLException {
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        int result = userDAOServiceMock.saveUser("Aishwarya","N","aishwarya@.com","aishw","0987654321");
        Assertions.assertEquals(0, result);
    }

    public void verifyUserSuccessTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        int result = userDAOServiceMock.verifyUser("Aishwarya","N","aishwarya@domain.com","aishwa","0987654321");
        Assertions.assertEquals(14, result);
    }

    public void verifyUserFailureTest() throws SQLException{
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        int result = userDAOServiceMock.verifyUser("Aishwarya","N","aish@domain.com","aishwa","0987654321");
        Assertions.assertEquals(0, result);
    }

}
