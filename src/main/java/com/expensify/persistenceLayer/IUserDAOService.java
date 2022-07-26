package com.expensify.persistenceLayer;

import java.sql.SQLException;

public interface IUserDAOService {

    int saveUser(String firstName, String lastName, String email, String password, String contact) throws SQLException;
    int verifyUser( String email) throws SQLException;
    boolean resetPassword(String email);
    boolean updatePassword(String email, String password);
    String getUserFirstName(int userId);
    String getUserPassword(String email) throws SQLException;
}
