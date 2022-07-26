package com.expensify.persistenceLayerMock;

import com.expensify.persistenceLayer.IUserDAOService;
import java.sql.SQLException;

public class UserDAOServiceMock implements IUserDAOService {


    @Override
    public boolean checkIfEmailExists(String email) {
        return true;
    }



    @Override
    public boolean updatePassword(String email, String generatedPassword) {
        return true;
    }

    @Override
    public String getUserFirstName(int userId) {
        return null;
    }

    @Override
    public int saveUser(String firstName, String lastName, String email, String password, String contact) throws SQLException {
        return 1;
    }

    @Override
    public String encryptPassword(String password) {
        return String.valueOf(true);
    }

    @Override
    public int verifyUser(String firstName, String lastName, String email, String password, String contact) throws SQLException {
        return 1;
    }

}