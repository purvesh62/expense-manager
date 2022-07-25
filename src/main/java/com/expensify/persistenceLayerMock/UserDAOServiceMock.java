package com.expensify.persistenceLayerMock;

import com.expensify.persistenceLayer.IUserDAOService;

public class UserDAOServiceMock implements IUserDAOService {


    @Override
    public boolean findByEmail(String email) {
        return true;
    }

    @Override
    public String encode(String password) {
        return String.valueOf(true);
    }
}
