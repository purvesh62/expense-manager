package com.expensify.persistenceLayer;

public interface IUserDAOService {

    boolean findByEmail(String email);

    String encode(String password);
}
