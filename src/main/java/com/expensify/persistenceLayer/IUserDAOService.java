package com.expensify.persistenceLayer;

import com.expensify.model.User;

import java.sql.SQLException;

public interface IUserDAOService {


    int saveUser(String firstName, String lastName, String email, String password, String contact) throws SQLException;


    String encode(String password);

    int verifyUser(String firstName, String lastName, String email, String password, String contact) throws SQLException;


    boolean checkIfEmailExists(String email);

    boolean updatePassword(String email, String password);

    String getUserFirstName(int userId);
}
