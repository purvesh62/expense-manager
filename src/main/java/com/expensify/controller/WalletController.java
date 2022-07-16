package com.expensify.controller;

import com.expensify.model.PaymentCategory;
import com.expensify.model.Wallet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //   @GetMapping
//    private  List<Wallet> getAllWalletDetails(@RequestParam("user_id") int userId) throws SQLException {
//        return this.wallet.getAllWalletDetails(userId);
//    }
    @GetMapping(value = "/api/v1/wallet", produces = "text/html")
    private String getAllWalletDetails(@RequestParam("user_id") int userId, Model model) throws SQLException {
        List<Wallet> walletList = wallet.getAllWalletDetails(userId);
        List<PaymentCategory> paymentCategoryList = paymentCategory.getAllPaymentCategoriesList();
        model.addAttribute("wallet_list", walletList);
        model.addAttribute("payment_categories", paymentCategoryList);
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        model.addAttribute(wallet);
        return "walletDetails";
    }

    @PostMapping(value = "/api/v1/wallet")
    private String addWallet(Wallet newWallet) throws SQLException {
        this.wallet.saveWallet(newWallet);
        return "redirect:/api/v1/wallet?user_id="+newWallet.getUserId();
    }


    @DeleteMapping
    private void deleteWallet(@RequestParam("wallet_id") int walletId) throws SQLException {
        this.wallet.deleteWallet(walletId);
    }

    @PutMapping
    private void updateWallet(@RequestBody Wallet wallet) throws SQLException {
        this.wallet.updateWallet(wallet);
    }

}
