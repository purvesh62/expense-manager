package com.expensify.model;

import com.expensify.SessionManager;
import com.expensify.persistenceLayer.UserDAOService;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class User {
    private UserDAOService authenticationDAO;
    private int userId;
    private String firstName;
    private String lastName;
    private String email;

    private String password;
    private String contact;

    //private String resetPasswordToken;

    public User() {
        authenticationDAO = new UserDAOService();
    }

    public User(int userId, String firstName, String lastName, String email, String password, String contact) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contact = contact;
        //this.resetPasswordToken = resetPasswordToken;
        authenticationDAO = new UserDAOService();
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
    public String forgotPassword() throws SQLException{
        return authenticationDAO.updatePassword(this);
    }

    public String getSiteURL(HttpServletRequest request){
        return authenticationDAO.getSiteURL((HttpServletRequest) this);
    }

    public void email(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        authenticationDAO.sendEmail(recipientEmail, link);
    }
/*
    public String findByEmail(String email){
        return authenticationDAO.findByEmail(this);
    }
    public String findByResetPasswordToken(String token){
        return authenticationDAO.findByResetPasswordToken(this);
    }
*/

    public static class BCryptPasswordEncoder {
        public String encode(String password) {
            return password;
        }
    }
}

