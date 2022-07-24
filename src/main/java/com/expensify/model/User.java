package com.expensify.model;

import com.expensify.persistenceLayer.IUserDAOService;
import com.expensify.persistenceLayer.UserDAOService;

import java.sql.SQLException;
import java.util.UUID;

public class User implements IUser {
    private IUserDAOService userDAOService;
    private UserDAOService authenticationDAO;

    private int userId;
    private String firstName;
    private String lastName;
    private String email;

    private String password;
    private String contact;



    public User() {
        authenticationDAO = new UserDAOService();
    }

    public User(IUserDAOService database) {
        userDAOService = database;
    }
    public IUserDAOService getBudgetDAOService() {
        return userDAOService;
    }

    public void setUserDAOService(IUserDAOService budgetDAOService) {
        this.userDAOService = userDAOService;
    }

    public User(int userId, String firstName, String lastName, String email, String password, String contact) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contact = contact;
        authenticationDAO = new UserDAOService();
    }

    public UserDAOService getAuthenticationDAO() {
        return authenticationDAO;
    }

    public void setAuthenticationDAO(UserDAOService authenticationDAO) {
        this.authenticationDAO = authenticationDAO;
    }

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

    public int registerUser() throws SQLException {
        return authenticationDAO.saveUser(this);
    }

    public int authenticateUser() throws SQLException {
        return authenticationDAO.verifyUser(this);
    }

   @Override
   public boolean findByEmail(String email) {
        boolean userExist = authenticationDAO.findByEmail(this.email);
        if(userExist) {
            String generatedPassword = UUID.randomUUID().toString().substring(0,20);
            if (authenticationDAO.updatePassword(this.email,generatedPassword)){
                SMTPEmailService.instance(this.email, "Your new password is " + generatedPassword, "Expensify reset password").sendEmail();
                return true;
            }
        }
        return false;

    }

    @Override
        public String encode(String password) {
        return this.password;
    }

}

