package com.expensify.persistenceLayerMock;

import com.expensify.persistenceLayer.IUserDAOService;
import java.sql.SQLException;

public class UserDAOServiceMock implements IUserDAOService {


    @Override
    public boolean checkIfEmailExists(String email) {
        return true;
    }

    @Override
    public int saveUser(String firstName, String lastName, String email, String password, String contact) throws SQLException {
        return 1;
    }

    @Override
    public String encode(String password) {
        return String.valueOf(true);
    }

    @Override
    public int verifyUser(String firstName, String lastName, String email, String password, String contact) throws SQLException {
        return 1;
    }

}
