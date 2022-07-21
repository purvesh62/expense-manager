package com.expensify.controller;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.*;
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
    private IDatabase database;

    private IWalletFactory walletFactory;
    private final PaymentCategory paymentCategory;

    public WalletController() {
        database = MySqlDatabase.instance();
        this.walletFactory = new WalletFactory();
        this.paymentCategory = new PaymentCategory();

    }

    @GetMapping(value = "/api/v1/wallet", produces = "text/html")
    private String getAllWalletDetails(@RequestParam("user_id") int userId, Model model) throws SQLException {
        List<IWallet> walletList = walletFactory.createWallet(database).getAllWalletDetails(userId);
        List<PaymentCategory> paymentCategoryList = paymentCategory.getAllPaymentCategoriesList();

        model.addAttribute("wallet_list", walletList);
        model.addAttribute("payment_categories", paymentCategoryList);
        model.addAttribute("wallet", new Wallet());
        return "wallet";
    }

    @PostMapping(value = "/api/v1/wallet")
    private String addWallet(@Valid Wallet newWallet, BindingResult result, RedirectAttributes redirAttrs) throws SQLException {
        System.out.println("Add called");
        if (result.hasErrors()) {
            return "redirect:/api/v1/wallet?user_id=1";

        }
        newWallet.setWalletDAOService(walletFactory.createWalletDAOService(database));
        newWallet.setUserId(1);
        newWallet.saveWallet();
        redirAttrs.addFlashAttribute("SUCCESS", "Wallet Added");
        return "redirect:/api/v1/wallet?user_id=1";
    }


    @GetMapping(value = "/api/v1/wallet/walletId/{walletId}")
    private String deleteWallet(@PathVariable(value = "walletId") int walletId, RedirectAttributes redirAttrs) throws SQLException {
        System.out.println(walletId);
        walletFactory.createWallet(database).deleteWallet(walletId);
        redirAttrs.addFlashAttribute("SUCCESS", "Wallet Deleted");
        return "redirect:/api/v1/wallet?user_id=1";
    }

    @PostMapping(value =  "/api/v1/updatewallet")
    private String updateWallet(@ModelAttribute("wallet") Wallet wallet, RedirectAttributes redirAttrs) throws SQLException {
//        wallet = this.wallet;
//        System.out.println("Put Mapping called");
//        System.out.println(wallet.getWalletId());
//        System.out.println(wallet.getUserId());
//        System.out.println("-------------------------------------------------");
//        System.out.println(wallet.getWalletId()+" " +wallet.getAmount()+" " +wallet.getWalletLabel());
//        System.out.println(wallet.getUserId());
        wallet.setWalletDAOService(walletFactory.createWalletDAOService(database));
        wallet.updateWallet();
        redirAttrs.addFlashAttribute("SUCCESS", "Wallet Updated");
        return "redirect:/api/v1/wallet?user_id=1";
    }

}
