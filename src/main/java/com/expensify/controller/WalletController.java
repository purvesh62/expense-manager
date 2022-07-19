package com.expensify.controller;
import com.expensify.model.Wallet;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;
@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {
    private final Wallet wallet;

    public WalletController() {
        this.wallet = new Wallet();

    }
    @GetMapping
    private List<Wallet> getAllWalletDetails(@RequestParam("user_id") int userId) throws SQLException {
        return this.wallet.getAllWalletDetails(userId);
    }
    @PostMapping
    private void addWallet(@RequestBody Wallet newWallet) throws SQLException {
        this.wallet.saveWallet(newWallet);
    }

    @DeleteMapping
    private void deleteWallet(@RequestParam("wallet_id") int walletId) throws SQLException {
        this.wallet.deleteWallet(walletId);
    }
    @PutMapping
    private void updateWallet(@RequestBody Wallet wallet) throws SQLException{
        this.wallet.updateWallet(wallet);
    }

}
