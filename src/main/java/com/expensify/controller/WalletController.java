package com.expensify.controller;

import com.expensify.model.*;
import com.expensify.model.factories.SubscriptionFactory;
import com.expensify.model.factories.WalletFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@Controller
public class WalletController {
    private IWallet walletObj;
    private IPaymentCategory paymentCategoryObj;


    public WalletController() {
       walletObj = WalletFactory.instance().createWallet();
       paymentCategoryObj = SubscriptionFactory.PaymentCategoryFactory.instance().createPaymentCategory();

    }

    @GetMapping(value = "/api/v1/wallet", produces = "text/html")
    private String getAllWalletDetails(@RequestParam("user_id") int userId, Model model) throws SQLException {
        List<IWallet> walletList = walletObj.getAllWalletDetails(userId);
        List<IPaymentCategory> paymentCategoryList = paymentCategoryObj.getAllPaymentCategories();

        model.addAttribute("wallet_list", walletList);
        model.addAttribute("payment_categories", paymentCategoryList);
        model.addAttribute("wallet", walletObj);
        return "wallet";
    }

    @PostMapping(value = "/api/v1/wallet")
    private String addWallet(@Valid Wallet newWallet, BindingResult result, RedirectAttributes redirAttrs) throws SQLException {
        System.out.println("Add called");
        if (result.hasErrors()) {
            return "redirect:/api/v1/wallet?user_id=1";

        }
        newWallet.setWalletDAOService(walletObj);
        newWallet.setUserId(1);
        newWallet.saveWallet();
        redirAttrs.addFlashAttribute("SUCCESS", "Wallet Added");
        return "redirect:/api/v1/wallet?user_id=1";
    }


    @GetMapping(value = "/api/v1/wallet/walletId/{walletId}")
    private String deleteWallet(@PathVariable(value = "walletId") int walletId, RedirectAttributes redirAttrs) throws SQLException {
        walletObj.deleteWallet(walletId);
        redirAttrs.addFlashAttribute("SUCCESS", "Wallet Deleted");
        return "redirect:/api/v1/wallet?user_id=1";
    }

    @PostMapping(value = "/api/v1/updatewallet")
    private String updateWallet(@ModelAttribute("wallet") Wallet wallet, RedirectAttributes redirAttrs) throws SQLException {
//        wallet = this.wallet;
//        System.out.println("Put Mapping called");
//        System.out.println(wallet.getWalletId());
//        System.out.println(wallet.getUserId());
//        System.out.println("-------------------------------------------------");
//        System.out.println(wallet.getWalletId()+" " +wallet.getAmount()+" " +wallet.getWalletLabel());
//        System.out.println(wallet.getUserId());
        wallet.setWalletDAOService(walletObj);
        wallet.updateWallet();
        redirAttrs.addFlashAttribute("SUCCESS", "Wallet Updated");
        return "redirect:/api/v1/wallet?user_id=1";
    }

}
