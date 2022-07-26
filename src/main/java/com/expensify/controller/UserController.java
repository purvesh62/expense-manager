package com.expensify.controller;

import com.expensify.factories.BudgetFactory;
import com.expensify.factories.ExpenseFactory;
import com.expensify.factories.UserFactory;
import com.expensify.model.IUser;
import com.expensify.model.SessionManager;
import com.expensify.model.User;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class UserController {

    private final IUser userObj;

    public UserController() {
        this.userObj = UserFactory.instance().createUser();
    }

    @GetMapping(value = "/register", produces = "text/html")
    public String register(@ModelAttribute("user") User user, Model model, HttpSession session) {
        try {
            model.addAttribute("user", UserFactory.instance().createUser());
            return "register";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "login";
    }


    @PostMapping(value = "/register", consumes = "application/x-www-form-urlencoded")
    public String registerUser(@ModelAttribute("user") User user, HttpSession session, RedirectAttributes redirectAttributes) throws SQLException {
        user.setUserDAOService(userObj);
        String msg = UserFactory.instance().createUserValidator().validate(user);
        if(msg == null) {
            int userId = user.registerUser();
            if (userId > 0) {
                JSONObject userCache = new JSONObject();
                userCache.put("email", user.getEmail());
                userCache.put("userId", userId);
                userCache.put("name", user.getFirstName());
                SessionManager.setSession(session, userCache);
                return "redirect:/";
            }
        }
        else {
            redirectAttributes.addFlashAttribute("errorMessage", msg);
            return "register";
        }
        return "error";
    }


    @GetMapping(path = "/reset", produces = "text/html")
    public String reset(@ModelAttribute("user") User user, Model model, HttpSession session) {
        try {
            model.addAttribute("user", UserFactory.instance().createUser());
            return "reset";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "login";
    }

    @PostMapping(value = "/reset", consumes = "application/x-www-form-urlencoded")
    public String resetPassword(@ModelAttribute("user") User user, HttpSession session) throws SQLException {
        user.setUserDAOService(userObj);
        boolean userExist = user.resetPassword(user.getEmail());
        if (userExist) {
            return "redirect:/login";
        }
        return "error";
    }

    @PostMapping(value = "/login", consumes = "application/x-www-form-urlencoded")
    public String authenticateUser(User user, HttpSession session) throws SQLException {
        user.setUserDAOService(userObj);
        int userId = user.authenticateUser();
        if (userId > 0) {
            String name = user.getUserFirstName(userId);
            JSONObject userCache = new JSONObject();
            userCache.put("email", user.getEmail());
            userCache.put("userId", userId);
            userCache.put("name", name);
            SessionManager.setSession(session, userCache);
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping(value = "/process_register")
    public String processRegister(User user) throws SQLException {
        user.setUserDAOService(userObj);
        user.registerUser();
        String encodedPassword = String.valueOf(user.isPasswordAuthenticated());
        user.setPassword(encodedPassword);
        return "process_register";
    }

    @GetMapping(path = "/login", produces = "text/html")
    public String userExpenses(Model model, HttpSession session) {
        try {
            model.addAttribute("user", UserFactory.instance().createUser());
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
