package com.expensify.controller;


import com.expensify.SessionManager;
import com.expensify.model.Authentication;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class AuthenticationController {

    private final Authentication authentication = new Authentication();

    @PostMapping("/register")
    @ResponseBody
    public void registerUser(@RequestBody Authentication authentication, HttpSession session) throws SQLException {
        int userId = authentication.registerUser();
        JSONObject userCache = new JSONObject();
        userCache.put("email", authentication.getEmail());
        userCache.put("userId", userId);
        SessionManager.setSession(session, userCache);
    }

    @PostMapping("/login")
    @ResponseBody
    public void authenticateUser(@RequestBody Authentication authentication, HttpSession session) throws SQLException {
        int userId = authentication.authenticateUser();
        if (userId > 0) {
            JSONObject userCache = new JSONObject();
            userCache.put("email", authentication.getEmail());
            userCache.put("userId", userId);
            SessionManager.setSession(session, userCache);
        } else {
            // User already exists
        }
    }
}
