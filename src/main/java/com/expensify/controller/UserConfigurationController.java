package com.expensify.controller;

import com.expensify.model.SessionManager;
import com.expensify.model.UserConfiguration;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserConfigurationController {

    @GetMapping(value = "/configurations", produces = "text/html")
    public String userConfigurations(Model model, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            model.addAttribute("userConfiguration", "");
            return "user_configurations";
        }
        return "redirect:/login";
    }

    @PutMapping(value = "/configurations", produces = "text/html")
    public String userConfigurations(@ModelAttribute("userConfiguration") UserConfiguration userConfiguration, Model model, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            model.addAttribute("userConfiguration", "");
            return "user_configurations";
        }
        return "redirect:/login";
    }
}
