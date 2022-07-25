package com.expensify.model;

import com.expensify.database.IDatabase;
import com.expensify.persistenceLayer.IUserDAOService;

public interface IUserFactory {

    IUser createUser(IDatabase database);

    IUser createUser();
    IUserDAOService createUserDAOService(IDatabase database);

    IUser createUser(int userId, String firstName, String lastName, String email, String password, String contact);
}
