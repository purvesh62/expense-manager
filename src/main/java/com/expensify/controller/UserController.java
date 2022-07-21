package com.expensify.controller;


import com.expensify.SessionManager;
import com.expensify.model.User;
//import jdk.internal.icu.impl.Utility;
import net.bytebuddy.utility.RandomString;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@Controller
public class UserController {
    @Autowired
    private JavaMailSender mailSender;
    private final User user = new User();
    private HttpSession session;


    @PostMapping(value="/register",produces = "text/html")
    public String registerUser(@RequestBody User user, HttpSession session) throws SQLException {
        int userId = user.registerUser();
        JSONObject userCache = new JSONObject();
        userCache.put("firstname",user.getFirstName());
        userCache.put("lastname",user.getLastName());
        userCache.put("email", user.getEmail());
        userCache.put("password",user.getPassword());
        userCache.put("contact",user.getContact());
        SessionManager.setSession(session, userCache);
        return "login";
    }

    @PostMapping(value="/reset", produces = "text/html")
    public String forgotPassword(@RequestBody User user, HttpSession session) throws SQLException{
        String password = user.forgotPassword();
        JSONObject userCache = new JSONObject();
        userCache.put("password",user.getPassword());
        SessionManager.setSession(session, userCache);
        return "login";
    }

    @GetMapping(path = "/reset", produces = "text/html")
    public String reset(Model model, HttpSession session, HttpServletRequest request,User user) {
        try {
            String email = request.getParameter("email");
            String token = RandomString.make(30);
            String resetPasswordLink = user.getSiteURL(request) + "/reset_password?token=" + token;
            user.email(email, resetPasswordLink);
            model.addAttribute("user", new User());
            return "reset";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "login";
    }

    @PostMapping(value="/login", produces = "text/html")
    public String authenticateUser(@RequestBody User user, HttpSession session) throws SQLException {
        int userId = user.authenticateUser();
        if (userId > 0) {
            JSONObject userCache = new JSONObject();
            userCache.put("email", user.getEmail());
            userCache.put("userId", userId);
            SessionManager.setSession(session, userCache);
            return "redirect:/";
        }
        return "reset";
    }

    @PostMapping(value="/process_register", produces="text/html")
    public String processRegister(User user) {
        User.BCryptPasswordEncoder passwordEncoder = new User.BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
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
}
