package com.expensify.persistenceLayerMock;

import com.expensify.model.IUser;
import com.expensify.persistenceLayer.IUserDAOService;
import java.sql.SQLException;
import java.util.List;

public class UserDAOServiceMock implements IUserDAOService {


    @Override
    public boolean resetPassword(String email) {
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
    public String getUserPassword(String email) throws SQLException {
        return null;
    }

    @Override
    public List<IUser> saveUser(String firstName, String lastName, String email, String password, String contact) throws SQLException {
        return 1;
    }

    @Override
    public int verifyUser(String email) throws SQLException {
        return 1;
    }

}
