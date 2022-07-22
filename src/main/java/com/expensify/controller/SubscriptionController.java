package com.expensify.controller;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.*;
import com.expensify.model.factories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
@Controller
public class SubscriptionController {
    private ISubscription subscriptionObj;

    public SubscriptionController() {
        subscriptionObj = SubscriptionFactory.instance().createSubscription();

    }

    @GetMapping(value = "/api/v1/subscription", produces = "text/html")
    private String getAllSubscriptionDetails(@RequestParam("user_id") int userId, Model model) throws SQLException {
        List<ISubscription> subscriptionList = subscriptionObj.getAllSubscriptionDetails(userId);
        model.addAttribute("subscription_list", subscriptionList);
        model.addAttribute("subscription", subscriptionObj);
        return "subscription";
    }

    @PostMapping(value = "/api/v1/subscription")
    private String addSubscription(@Valid Subscription newSubscription, BindingResult result, RedirectAttributes redirAttrs) throws SQLException {
        System.out.println("Add called");
        if (result.hasErrors()) {
            return "redirect:/api/v1/subscription?user_id=1";

        }
        newSubscription.setSubscriptionDAOService(subscriptionObj);
        newSubscription.setUserId(1);
        newSubscription.saveSubscription();
        redirAttrs.addFlashAttribute("SUCCESS", "Subscription Added");
        return "redirect:/api/v1/subscription?user_id=1";
    }

    @GetMapping(value = "/api/v1/subscription/subscription_id/{subscription_id}")
    private String deleteSubscription(@PathVariable(value = "subscription_id") int subscriptionId, RedirectAttributes redirAttrs) throws SQLException {
        subscriptionObj.deleteSubscription(subscriptionId);
        redirAttrs.addFlashAttribute("SUCCESS", "Subscription Deleted");
        return "redirect:/api/v1/subscription?user_id=1";
    }

    @PostMapping(value = "/api/v1/updatesubscription")
    private String updateSubscription(@ModelAttribute("subscription") Subscription subscription, RedirectAttributes redirAttrs) throws SQLException {
//        wallet = this.wallet;
//        System.out.println("Put Mapping called");
//        System.out.println(wallet.getWalletId());
//        System.out.println(wallet.getUserId());
//        System.out.println("-------------------------------------------------");
//        System.out.println(wallet.getWalletId()+" " +wallet.getAmount()+" " +wallet.getWalletLabel());
//        System.out.println(wallet.getUserId());
        subscription.setSubscriptionDAOService(subscriptionObj);
        subscription.updateSubscription();
        redirAttrs.addFlashAttribute("SUCCESS", "Subscription Updated");
        return "redirect:/api/v1/subscription?user_id=1";
    }
}

