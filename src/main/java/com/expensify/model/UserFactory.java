package com.expensify.model;

import com.expensify.database.IDatabase;
import com.expensify.persistenceLayer.UserDAOService;
import com.expensify.persistenceLayer.IUserDAOService;

public class UserFactory implements IUserFactory {
    public UserFactory() {
    }
    
    @Override
    public IUser createUser(IDatabase database) {
        return new User(createUserDAOService(database));
    }

    @Override
    public IUser createUser() {
        return new User();
    }

    @Override
    public IUserDAOService createUserDAOService(IDatabase database) {
        return (IUserDAOService) new UserDAOService();
    }

    @Override
    public IUser createUser(int userId, String firstName, String lastName, String email,String password,String contact) {
        return new User(userId, firstName, lastName, email, password, contact);
    }

}




