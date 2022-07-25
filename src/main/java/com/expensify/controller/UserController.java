package com.expensify.controller;

import com.expensify.model.SessionManager;
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
    private HttpSession session;


    @GetMapping(value = "/register", produces = "text/html")
    public String register(@ModelAttribute("user") User user, Model model, HttpSession session) {
        try {
            model.addAttribute("user", new User());
            return "register";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "login";
    }


    @PostMapping(value = "/register", consumes = "application/x-www-form-urlencoded")
    public String registerUser(@ModelAttribute("user") User user, HttpSession session) throws SQLException {
        int userId = user.registerUser();
        if (userId > 0) {
            JSONObject userCache = new JSONObject();
            userCache.put("email", user.getEmail());
            userCache.put("userId", userId);
            SessionManager.setSession(session, userCache);
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String email = user.getEmail();
            String contact = user.getContact();
            return "redirect:/login";
        }
        return "register";
    }


    @GetMapping(path = "/reset", produces = "text/html")
    public String reset(@ModelAttribute("user") User user, Model model, HttpSession session) {
        try {
            model.addAttribute("user", new User());
            return "reset";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "login";
    }

    @PostMapping(value = "/reset", consumes = "application/x-www-form-urlencoded")
    public String resetPassword(@ModelAttribute("user") User user, HttpSession session) throws SQLException {
        boolean userExist = user.findByEmail(user.getEmail());
        if (userExist) {
            return "redirect:/login";
        }
        return "redirect:/error";
    }


    @PostMapping(value = "/login", consumes = "application/x-www-form-urlencoded")
    public String authenticateUser(User user, HttpSession session) throws SQLException {
        int userId = user.authenticateUser();
        if (userId > 0) {
            JSONObject userCache = new JSONObject();
            userCache.put("email", user.getEmail());
            userCache.put("userId", userId);
            SessionManager.setSession(session, userCache);
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping(value = "/process_register", produces = "text/html")
    public String processRegister(User user) {
        String encodedPassword = user.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return "process_register";
    }

    @GetMapping(path = "/login", produces = "text/html")
    public String userExpenses(Model model, HttpSession session) {
        try {
            model.addAttribute("user", new User());
            return "login";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(path = "/logout", produces = "text/html")
    public String userLogout(HttpSession session) {
        SessionManager.removeSession(session);
        return "redirect:/";
    }
}
