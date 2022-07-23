package com.expensify.controller;

import com.expensify.SessionManager;
import com.expensify.model.IPaymentCategory;
import com.expensify.model.IWallet;
import com.expensify.model.PaymentCategory;
import com.expensify.model.Wallet;
import com.expensify.model.factories.PaymentCategoryFactory;
import com.expensify.model.factories.SubscriptionFactory;
import com.expensify.model.factories.WalletFactory;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@Controller
public class WalletController {
    private IWallet walletObj;
    private IPaymentCategory paymentCategoryObj;


    public WalletController() {
        walletObj = WalletFactory.instance().createWallet();
        paymentCategoryObj = PaymentCategoryFactory.instance().createPaymentCategory();

    }

    @GetMapping(value = "/api/v1/wallet", produces = "text/html")
    private String getAllWalletDetails(Model model, HttpSession session) throws SQLException {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            List<IWallet> walletList = walletObj.getAllWalletDetails(userId);
            List<IPaymentCategory> paymentCategoryList = paymentCategoryObj.getAllPaymentCategories();
            model.addAttribute("wallet_list", walletList);
            model.addAttribute("payment_categories", paymentCategoryList);
            model.addAttribute("wallet", walletObj);
            return "wallet";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping(value = "/api/v1/wallet")
    private String addWallet(Wallet newWallet, BindingResult result, RedirectAttributes redirAttrs, HttpSession session) throws SQLException {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            if (result.hasErrors()) {
                return "redirect:/api/v1/wallet? " + userId;

            }
            newWallet.setWalletDAOService(walletObj);
            newWallet.setUserId(1);
            newWallet.saveWallet();
            redirAttrs.addFlashAttribute("SUCCESS", "Wallet Added");
            return "redirect:/api/v1/wallet? " + userId;
        } else {
            return "redirect:/";
        }
    }


    @GetMapping(value = "/api/v1/wallet/walletId/{walletId}")
    private String deleteWallet(@PathVariable(value = "walletId") int walletId, RedirectAttributes redirAttrs, HttpSession session) throws SQLException {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            walletObj.deleteWallet(walletId);
            redirAttrs.addFlashAttribute("SUCCESS", "Wallet Deleted");
            return "redirect:/api/v1/wallet? " + userId;
        } else {
            return "redirect:/";
        }

    }

    @PostMapping(value = "/api/v1/updatewallet")
    private String updateWallet(@ModelAttribute("wallet") Wallet wallet, RedirectAttributes redirAttrs, HttpSession session) throws SQLException {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            wallet.setWalletDAOService(walletObj);
            wallet.updateWallet();
            redirAttrs.addFlashAttribute("SUCCESS", "Wallet Updated");
            return "redirect:/api/v1/wallet? " + userId;
        } else {
            return "redirect:/";
        }
    }

}
