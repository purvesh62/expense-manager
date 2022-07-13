package com.expensify.model;

import com.expensify.persistenceLayer.AuthenticationDAO;


import java.sql.SQLException;

public class Authentication {
    private final AuthenticationDAO authenticationDAO;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String contact;

    public Authentication() {

        this.authenticationDAO = new AuthenticationDAO();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int registerUser() throws SQLException {
        return authenticationDAO.saveUser(this);


    }

    public int authenticateUser() throws SQLException {
        return authenticationDAO.verifyUser(this);
    }

    public class BCryptPasswordEncoder {
        public String encode(String password) {
            return password;
        }
    }
}

