package com.expensify.controller;

import com.expensify.model.PaymentCategory;
import com.expensify.model.Wallet;
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
    private final Wallet wallet;
    private final PaymentCategory paymentCategory;

    public WalletController() {
        this.wallet = new Wallet();
        this.paymentCategory = new PaymentCategory();

    }

    @GetMapping(value = "/api/v1/wallet", produces = "text/html")
    private String getAllWalletDetails(@RequestParam("user_id") int userId, Model model) throws SQLException {
        List<Wallet> walletList = wallet.getAllWalletDetails(userId);
        List<PaymentCategory> paymentCategoryList = paymentCategory.getAllPaymentCategoriesList();
        model.addAttribute("wallet_list", walletList);
        model.addAttribute("payment_categories", paymentCategoryList);
        wallet.setUserId(userId);
        wallet.setWalletId(wallet.getWalletId());
        model.addAttribute("wallet",wallet);
        return "wallet";
    }

    @PostMapping(value = "/api/v1/wallet")
    private String addWallet(@Valid Wallet newWallet, BindingResult result, RedirectAttributes redirAttrs) throws SQLException {
        System.out.println("Add called");
        if (result.hasErrors()) {
            return "redirect:/api/v1/wallet?user_id=" + newWallet.getUserId();

        }
        newWallet.saveWallet(newWallet);
        redirAttrs.addFlashAttribute("SUCCESS", "Wallet Added");
        return "redirect:/api/v1/wallet?user_id=" + newWallet.getUserId();
    }


    @GetMapping(value = "/api/v1/wallet/walletId/{walletId}")
    private String deleteWallet(@PathVariable(value = "walletId") int walletId, RedirectAttributes redirAttrs) throws SQLException {
        System.out.println(walletId);
        int userId = wallet.getWalletById(walletId).getUserId();
        this.wallet.deleteWallet(walletId);
        redirAttrs.addFlashAttribute("SUCCESS", "Wallet Deleted");
        return "redirect:/api/v1/wallet?user_id=" + userId;
    }

    @PostMapping(value =  "/api/v1/updatewallet")
    private String updateWallet(@ModelAttribute("wallet") Wallet wallet) throws SQLException {
//        wallet = this.wallet;
//        System.out.println("Put Mapping called");
//        System.out.println(wallet.getWalletId());
//        System.out.println(wallet.getUserId());
//        System.out.println("-------------------------------------------------");
//        System.out.println(wallet.getWalletId()+" " +wallet.getAmount()+" " +wallet.getWalletLabel());
//        System.out.println(wallet.getUserId());
        wallet.updateWallet(wallet);
        return "redirect:/api/v1/wallet?user_id=" + wallet.getUserId();
    }

}
