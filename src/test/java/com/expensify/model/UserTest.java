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
    public void findByEmailSuccessTest() throws SQLException {
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = userDAOServiceMock.findByEmail("njaishwarya01@gmail.com");
        Assertions.assertEquals(true, result);
    }

    @Test
    public void findByEmailFailureTest() throws SQLException {
        UserDAOServiceMock userDAOServiceMock = new UserDAOServiceMock();
        boolean result = userDAOServiceMock.findByEmail("njaishwarya01gmail.com");
        Assertions.assertEquals(false, result);
    }

}
