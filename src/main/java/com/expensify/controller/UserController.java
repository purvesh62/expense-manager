package com.expensify.controller;


import com.expensify.SessionManager;
import com.expensify.model.User;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class UserController {

    private final User user = new User();

    @GetMapping("/index")
    @ResponseBody
    public String viewHomePage() {
        return "index";
    }

    @PostMapping("/register")
    @ResponseBody
    public void registerUser(@RequestBody User user, HttpSession session) throws SQLException {
        int userId = user.registerUser();
        JSONObject userCache = new JSONObject();
        userCache.put("email", user.getEmail());
        userCache.put("userId", userId);
        SessionManager.setSession(session, userCache);
    }

    @PostMapping("/login")
    @ResponseBody
    public void authenticateUser(@RequestBody User user, HttpSession session) throws SQLException {
        int userId = user.authenticateUser();
        if (userId > 0) {
            JSONObject userCache = new JSONObject();
            userCache.put("email", user.getEmail());
            userCache.put("userId", userId);
            SessionManager.setSession(session, userCache);
        } else {
            // User already exists
        }
    }
    @PostMapping("/process_register")
    @ResponseBody
    public String processRegister(User userRepo) {
        User.BCryptPasswordEncoder passwordEncoder = new User.BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userRepo.getPassword());
        userRepo.setPassword(encodedPassword);
        return "process_register";
    }

    @GetMapping(path = "/login", produces = "text/html")
    public String userExpenses(Model model, HttpSession session) {
        try {
            return "login";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "login";
    }
}
