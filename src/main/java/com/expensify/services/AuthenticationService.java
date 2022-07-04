package com.expensify.services;

import com.expensify.model.Authentication;
import com.expensify.persistenceLayer.AuthenticationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AuthenticationService {

    private final AuthenticationDAO authenticationDAO;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public AuthenticationService(AuthenticationDAO authenticationDAO) {
        this.authenticationDAO = authenticationDAO;
    }

    public List<Authentication> getalluserauthentication(int user_id, String firstname, String lastname, String email, String password, String contact) throws SQLException {
        return authenticationDAO.getAllUserLogins( user_id,  firstname,  lastname,  email,  password,  contact);
    }

    public static class BCryptPasswordEncoder {
        public String encode(String password) {
            return password;
        }
    }
}
