package com.expensify.controller;


import com.expensify.model.Authentication;
import com.expensify.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    
    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @GetMapping("/signup")
    private List<Authentication> get(
            @RequestParam(value = "user_id") int user_id,
            @RequestParam(value = "firstname") String firstname,
            @RequestParam(value = "lastname") String lastname,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "contact") String contact
            
    ) throws SQLException {

        return this.authenticationService.getalluserauthentication(user_id, firstname, lastname, email, password, contact);
    }
    @PostMapping("/register")
    public String processRegister(Authentication authentication) {
        AuthenticationService.BCryptPasswordEncoder passwordEncoder = new AuthenticationService.BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(authentication.getPassword());
        authentication.setPassword(encodedPassword);
        return "register_success";
    }
}
