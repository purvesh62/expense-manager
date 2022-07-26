package com.expensify.persistenceLayer;

import com.expensify.model.IUser;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAOService {


    List<IUser> saveUser(String firstName, String lastName, String email, String password, String contact) throws SQLException;


    int verifyUser( String email) throws SQLException;


    boolean resetPassword(String email);

    boolean updatePassword(String email, String password);

    String getUserFirstName(int userId);

    String getUserPassword(String email) throws SQLException;
}
