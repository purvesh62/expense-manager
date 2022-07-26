package com.expensify.model;

import com.expensify.persistenceLayer.IExpenseDOAService;
import com.expensify.persistenceLayer.IUserDAOService;

import java.sql.SQLException;

public interface IUser {

    int registerUser() throws SQLException;

    int authenticateUser() throws SQLException;

    IUserDAOService getUserDAOService();

    String encode(String password);

    boolean checkIfEmailExists(String email);
}
