package com.expensify.model;

public interface IUser {


    boolean findByEmail(String email);

    String encode(String password);
}
