package com.expensify.controller;

import com.expensify.factories.PaymentCategoryFactory;
import com.expensify.factories.WalletFactory;
import com.expensify.model.IPaymentCategory;
import com.expensify.model.IWallet;
import com.expensify.model.SessionManager;
import com.expensify.model.Wallet;
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
public class WalletController {
    private IWallet walletObj;
    private IPaymentCategory paymentCategoryObj;

    public WalletController() {
        walletObj = WalletFactory.instance().createWallet();
        paymentCategoryObj = PaymentCategoryFactory.instance().createPaymentCategory();

    }

    @GetMapping(value = "/wallet", produces = "text/html")
    private String getAllWalletDetails(Model model, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            List<IWallet> walletList = walletObj.getAllWalletDetails(userId);
            List<IPaymentCategory> paymentCategoryList = paymentCategoryObj.getAllPaymentCategories();
            model.addAttribute("wallet_list", walletList);
            model.addAttribute("payment_categories", paymentCategoryList);
            model.addAttribute("wallet", walletObj);
            model.addAttribute("name", userCache.get("name"));
            model.addAttribute("email", userCache.get("email"));
            return "wallet";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping(value = "/wallet")
    private String addWallet(Wallet newWallet, BindingResult result, RedirectAttributes redirAttrs, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            if (result.hasErrors()) {
                return "redirect:/wallet";
            }
            newWallet.setWalletDAOService(walletObj);
            newWallet.setUserId(userId);
            newWallet.saveWallet();
            redirAttrs.addFlashAttribute("SUCCESS", "Wallet Added");
            return "redirect:/wallet";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping(value = "/wallet/walletId/{walletId}")
    private String deleteWallet(@PathVariable(value = "walletId") int walletId, RedirectAttributes redirAttrs, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            walletObj.deleteWallet(walletId);
            redirAttrs.addFlashAttribute("SUCCESS", "Wallet Deleted");
            return "redirect:/wallet";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping(value = "/updatewallet")
    private String updateWallet(@ModelAttribute("wallet") Wallet wallet, RedirectAttributes redirAttrs, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            wallet.setWalletDAOService(walletObj);
            wallet.updateWallet();
            redirAttrs.addFlashAttribute("SUCCESS", "Wallet Updated");
            return "redirect:/wallet";
        } else {
            return "redirect:/";
        }
    }
}
