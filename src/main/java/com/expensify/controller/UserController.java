package com.expensify.controller;


import com.expensify.SessionManager;
import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.BudgetFactory;
import com.expensify.model.Expense;
import com.expensify.model.IBudgetFactory;
import com.expensify.model.User;
//import jdk.internal.icu.impl.Utility;
import net.bytebuddy.utility.RandomString;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import java.text.ParseException;

@Controller
public class UserController {
    @Autowired
    private JavaMailSender mailSender;
    private final User user = new User();
    private HttpSession session;


    @GetMapping(value="/register", produces = "text/html")
    public String register(@ModelAttribute("user") User user, Model model, HttpSession session) {
        try {
            model.addAttribute("user", new User());
            return "register";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "login";
    }


    @PostMapping(value="/register", consumes = "application/x-www-form-urlencoded")
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
            String contact =user.getContact();
            return "redirect:/login";
        }
        return "register";
    }

//    @PostMapping(value="/reset", produces = "text/html")
//    public String forgotPassword(@RequestBody User user, HttpSession session) throws SQLException{
//        String password = user.forgotPassword();
//        JSONObject userCache = new JSONObject();
//        userCache.put("password",user.getPassword());
//        SessionManager.setSession(session, userCache);
//        return "login";
//    }

    @GetMapping(path = "/reset", produces = "text/html")
    public String reset(@ModelAttribute("user") User user, Model model, HttpSession session) {
        try {
//            String email = request.getParameter("email");
//            String token = RandomString.make(30);
//            String resetPasswordLink = user.getSiteURL(request) + "/reset_password?token=" + token;
//            user.email(email, resetPasswordLink);
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
        return "login";
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
