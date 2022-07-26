package com.expensify.factories;

import com.expensify.Validators.UserValidator;
import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.IUser;
import com.expensify.model.User;
import com.expensify.persistenceLayer.UserDAOService;
import com.expensify.persistenceLayer.IUserDAOService;

public class UserFactory implements IUserFactory {

    private static UserFactory userFactory;
    public UserFactory() {
    }
    
    @Override
    public IUser createUser(IDatabase database) {
        return new User(createUserDAOService(database));
    }

    public static UserFactory instance() {
        if (userFactory == null) {
            userFactory = new UserFactory();
        }
        return userFactory;
    }

    @Override
    public IUser createUser() {
        IDatabase database = MySqlDatabase.instance();
        return new User(createUserDAOService(database));
    }

    @Override
    public IUserDAOService createUserDAOService(IDatabase database) {
        return new UserDAOService(database);
    }

    @Override
    public IUser createUser(int userId, String firstName, String lastName, String email,String password,String contact) {
        return new User(userId, firstName, lastName, email, password, contact);
    }
    @Override
    public UserValidator createUserValidator() {
        return new UserValidator();
    }
}




