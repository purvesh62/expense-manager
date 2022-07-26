package com.expensify.controller;

import com.expensify.factories.SubscriptionFactory;
import com.expensify.model.ISubscription;
import com.expensify.model.SessionManager;
import com.expensify.model.Subscription;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SubscriptionController {
    private final ISubscription subscriptionObj;

    public SubscriptionController() {
        subscriptionObj = SubscriptionFactory.instance().createSubscription();
    }

    @GetMapping(value = "/subscription", produces = "text/html")
    private String getAllSubscriptionDetails(Model model, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            List<ISubscription> subscriptionList = subscriptionObj.getAllSubscriptionDetails(userId);
            model.addAttribute("subscription_list", subscriptionList);
            model.addAttribute("subscription", subscriptionObj);
            model.addAttribute("name", userCache.get("name"));
            model.addAttribute("email", userCache.get("email"));
            return "subscription";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping(value = "/subscription")
    private String addSubscription(Subscription newSubscription, BindingResult result, RedirectAttributes redirAttrs, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            if (result.hasErrors()) {
                return "redirect:/subscription";

            }
            newSubscription.setSubscriptionDAOService(subscriptionObj);
            newSubscription.setUserId(userId);
            newSubscription.saveSubscription();
            redirAttrs.addFlashAttribute("SUCCESS", "Subscription Added");
            return "redirect:/subscription";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping(value = "/subscription/subscription_id/{subscription_id}")
    private String deleteSubscription(@PathVariable(value = "subscription_id") int subscriptionId, RedirectAttributes redirAttrs, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            subscriptionObj.deleteSubscription(subscriptionId);
            redirAttrs.addFlashAttribute("SUCCESS", "Subscription Deleted");
            return "redirect:/subscription";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping(value = "/updatesubscription")
    private String updateSubscription(@ModelAttribute("subscription") Subscription subscription, RedirectAttributes redirAttrs, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            subscription.setSubscriptionDAOService(subscriptionObj);
            subscription.updateSubscription();
            redirAttrs.addFlashAttribute("SUCCESS", "Subscription Updated");
            return "redirect:/subscription";
        } else {
            return "redirect:/";
        }
    }
}

