package com.expensify.controller;

import com.expensify.factories.UserConfigurationFactory;
import com.expensify.model.IUserConfiguration;
import com.expensify.model.SessionManager;
import com.expensify.model.UserConfiguration;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserConfigurationController {

    private IUserConfiguration userConfigurationObj;

    public UserConfigurationController() {
        userConfigurationObj = UserConfigurationFactory.instance().createUserConfiguration();
    }

    @GetMapping(value = "/configurations", produces = "text/html")
    public String userConfigurations(Model model, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            UserConfiguration userConfiguration = (UserConfiguration) userConfigurationObj.getUserConfiguration((Integer) userCache.get("userId"));
            model.addAttribute("userConfiguration", userConfiguration);
            model.addAttribute("name", userCache.get("name"));
            model.addAttribute("email", userCache.get("email"));
            return "user_configurations";
        }
        return "redirect:/login";
    }

    @PostMapping(value = "/configurations", produces = "text/html")
    public String userConfigurations(@ModelAttribute("userConfiguration") UserConfiguration userConfiguration, Model model, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            userConfiguration.setUserConfigurationDAOService(userConfigurationObj);
            userConfiguration.setUserConfiguration(userId);
            return "redirect:/configurations";
        }
        return "redirect:/login";
    }
}
