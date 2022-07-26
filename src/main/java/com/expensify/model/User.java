package com.expensify.model;

import com.expensify.persistenceLayer.IUserDAOService;

import java.sql.SQLException;
import java.util.UUID;

public class User implements IUser {
    private IUserDAOService userDAOService;
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String contact;
    public User() {
    }
    public User(IUserDAOService database) {
        userDAOService = database;
    }
    public void setUserDAOService(IUser user) {
        this.userDAOService = user.getUserDAOService();
    }

    public User(int userId, String firstName, String lastName, String email, String password, String contact) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contact = contact;}
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
    @Override
    public int registerUser() throws SQLException {
        if (userDAOService.verifyUser(email) <= 0) {
            return userDAOService.saveUser(firstName, lastName, email, password, contact);
        }
        return 0;
    }
    @Override
    public int authenticateUser() throws SQLException {
        int userId = userDAOService.verifyUser(email);
        if (userId > 0 && isPasswordAuthenticated()) {
            return userId;
        }
        return 0;
    }
    @Override
    public IUserDAOService getUserDAOService() {
        return this.userDAOService;
    }
    @Override
    public boolean resetPassword(String email) {
        boolean userExist = userDAOService.resetPassword(this.email);
        if (userExist) {
            String generatedPassword = UUID.randomUUID().toString().substring(0, 8);
            if (userDAOService.updatePassword(this.email, generatedPassword)) {
                SMTPEmailService.instance(this.email, "Your new password is " + generatedPassword, "Expensify reset password").sendEmail();
                return true;
            }
        }
        return false;
    }
    public boolean isPasswordAuthenticated() throws SQLException {
        if (password == null) {
            return false;
        }
        String userPassword = userDAOService.getUserPassword(email);
        return userPassword.equals(password);
    }
    public String getUserFirstName(int userId) {
        return this.userDAOService.getUserFirstName(userId);
    }
}

