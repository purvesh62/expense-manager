package com.expensify.controller;
import com.expensify.model.Wallet;
import com.expensify.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;
@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {
    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }
    @GetMapping
    private List<Wallet> getAllWalletDetails(@RequestParam("user_id") int userId) throws SQLException {
        return this.walletService.getAllWalletDetails(userId);
    }
    @PostMapping
    private void addWallet(@RequestBody Wallet newWallet) throws SQLException {
        this.walletService.saveWallet(newWallet);
    }

    @DeleteMapping
    private void deleteWallet(@RequestParam("wallet_id") int walletId) throws SQLException {
        this.walletService.deleteWallet(walletId);
    }

}
