package com.expensify.persistenceLayerMock;

import com.expensify.persistenceLayer.IUserDAOService;
import java.sql.SQLException;

public class UserDAOServiceMock implements IUserDAOService {


    @Override
    public boolean resetPassword(String email) {
        if(email == ""){
            return false;
        }
        return true;
    }

    @Override
    public boolean updatePassword(String email, String generatedPassword) {
        if(email == "" || generatedPassword == ""){
            return false;
        }
        return true;
    }

    @Override
    public String getUserFirstName(int userId) {
        return null;
    }

    @Override
    public String getUserPassword(String email) throws SQLException {
        if( email == "")
        {return String.valueOf(false);
        }
        return String.valueOf(true);
    }
    @Override
    public int saveUser(String firstName, String lastName, String email, String password, String contact) throws SQLException {
        if(firstName == "" || lastName == "" || email == "" || password == "" || contact == ""){
            return 0;
        }
        return 1;
    }
    @Override
    public int verifyUser(String email) throws SQLException {
        if (email == "") {
            return 0;
        }
        return 1;
    }

}
